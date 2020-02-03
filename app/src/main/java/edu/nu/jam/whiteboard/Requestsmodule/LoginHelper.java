package edu.nu.jam.whiteboard.Requestsmodule;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginHelper extends AsyncTask<Void, Void, String> {
    String response = "";
    String userName = "";
    String hashWord = "";
    Context context;
    Long totalBytes;
    ProgressDialog pd;
    public AsyncResponder delegate;

    /**
     * This class extends AsyncTask and will run a 'POST' request for the
     * login token
     *
     * @param delegate The response delegate (see above example)
     * @param context Context of the activity to be passed.
     * @param userName pass the username of the user.
     * @param hashWord the "hash" word of the user.
     */
    public LoginHelper(AsyncResponder delegate, Context context, String userName, String hashWord){
        this.delegate = delegate;
        this.context = context;
        this.userName = userName;
        this.hashWord = hashWord;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        // Adds a progress bar for the retrieval time.
        pd = new ProgressDialog(context);
        pd.setMessage("Please Wait");
        pd.setCancelable(false);
        pd.show();
    }

    /**
     * Gets the JSON info for the user token from the backend database.
     * @throws IOException
     */
    private void getLogin() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/users/signin");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            // Set headers for login
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            // Connect to the backend.
            connection.connect();

            // DataOutput stream to send JSON data
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            // Create the JSON object to POST
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("username", userName);
            jsonParam.put("password", hashWord);
            // POST to the backend.
            wr.writeBytes(jsonParam.toString());

            int code = connection.getResponseCode();
            if(code != 200)
            {
                response = null;
                return;
            }

            // Flush and Close the DataOutputStream.
            wr.flush();
            wr.close();

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
        } catch (JSONException e){
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

    /**
     * The task that will complete in Asynchronously.
     * @param params dummy argument (Useful in 'POST')
     * @return
     */
    @Override
    protected String doInBackground(Void...params) {
        try {
            getLogin();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // This counts how many bytes were downloaded
        // Used for getting the total size of the bytes transferred.
        return "";
    }

    /**
     *
     * @param result the JSON data from the Async task.
     */
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Calls the processFinish method from the ClassListResponse interface
        // This is overridden when executing the task.
        if(response == null) {
            delegate.processFinish(null);
        }
        else {
            delegate.processFinish(response);
        }
        // Remove the progress bar.
        if(pd.isShowing()){
            pd.dismiss();
        }
    }
}
