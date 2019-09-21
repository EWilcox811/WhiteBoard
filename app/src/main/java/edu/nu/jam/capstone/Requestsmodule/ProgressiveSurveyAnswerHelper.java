package edu.nu.jam.capstone.Requestsmodule;

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
import java.util.ArrayList;
import java.util.HashMap;

public class ProgressiveSurveyAnswerHelper extends AsyncTask<Void, Void, String> {
    String response = "";
    String sessionid = "";
    ArrayList<HashMap<String, String>> useranswers;
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
     * @param useranswers pass the array of answers of the user.
     */
    public ProgressiveSurveyAnswerHelper(AsyncResponder delegate, Context context, String sessionid, ArrayList<HashMap<String, String>> useranswers){
        this.delegate = delegate;
        this.context = context;
        this.sessionid = sessionid;
        this.useranswers = useranswers;
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
    private void sendProgressiveAnswers(String questionid, String confidence) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/sessions/" + "1" + "/updateQuestion/" + questionid);
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
            jsonParam.put("response", confidence);

            // POST to the backend.
            wr.writeBytes(jsonParam.toString());

            // Flush and Close the DataOutputStream.
            wr.flush();
            wr.close();

            System.out.println(jsonParam.toString());


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
        } catch (JSONException e) {
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
            for(int i = 0; i < useranswers.size();i++) {
                String questionid = useranswers.get(i).get("id");
                String confidence = useranswers.get(i).get("confidence");
                sendProgressiveAnswers(questionid, confidence);
            }

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