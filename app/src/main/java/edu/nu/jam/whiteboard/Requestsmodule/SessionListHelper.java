package edu.nu.jam.whiteboard.Requestsmodule;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SessionListHelper extends AsyncTask<Void, Void, String> {
    //http://104.248.0.248/persons/901/currentSession

    String response = "";
    Context context;
    String userid = "";
    Long totalBytes;
    ProgressDialog pd;
    public AsyncResponder delegate;

    public SessionListHelper(AsyncResponder delegate, Context context, String userid){
        this.delegate = delegate;
        this.context = context;
        this.userid = userid;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        // Adds a progress bar for the retrieval time.
        pd = new ProgressDialog(context);
        pd.setMessage("Please Wait");
        pd.setCancelable(false);
        pd.show();
    }

    void getCurrentSession() throws IOException{
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/persons/" + userid + "/currentSession");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            // Connect to the backend.
            connection.connect();

            // Get the stream of data from the connection.
            InputStream stream = connection.getInputStream();

            // Read the data from the InputStream.
            reader = new BufferedReader(new InputStreamReader(stream));

            // Create the string with a StringBuffer to maintain JSON formatting.
            StringBuffer buffer = new StringBuffer();
            // Create the empty string that will be overwritten with data from the BufferedReader.
            String line = "";

            // Loop over the lines in the BufferedReader, exits when it hits EOF
            while((line = reader.readLine()) != null) {
                // Add line to the StringBuffer.
                buffer.append(line+"\n");
                // Debug logging
                Log.d("Response: ", "> " + line);
            }

            // Set the private String 'response' to the StringBuffer value.
            response = buffer.toString();
        } catch (MalformedURLException e) {
            // Debugging catch
            e.printStackTrace();
        } finally {
            // Disconnect from the server
            if (connection != null) {
                connection.disconnect();
                // Close the reader
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected String doInBackground(Void...params) {
        try {
            getCurrentSession();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // This counts how many bytes were downloaded
        // Used for getting the total size of the bytes transferred.
        final byte[] result = response.getBytes();
        // Can run a debug log on this to see sizes later.
        totalBytes = Long.valueOf(result.length);
        return response;
    }

    /**
     *
     * @param result the JSON data from the Async task.
     */
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Calls the processFinish method from the ClassListResponse interface
        // This is overridden when executing the task.
        delegate.processFinish(response);
        // Remove the progress bar.
        if(pd.isShowing()){
            pd.dismiss();
        }

    }
}
