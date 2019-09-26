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

//TEST

public class InitialSurveyResultsUpdateHelper extends AsyncTask<Void, Void, String> {
    String response = "";
    Context context;
    Long totalBytes;
    String userId;
    Double ConfidenceOne;
    Double ConfidenceTwo;
    Double ConfidenceThree;
    Double ConfidenceFour;
    ProgressDialog pd;
    public AsyncResponder delegate;

    /**
     * This class extends AsyncTask and will run a 'POST' request updating the learning style questinos.
     * An example can be found in AddCommentHelper.java
     *
     * @param delegate The response delegate (see above example)
     * @param context Context of the activity to be passed
     * @param userId User ID, needed for setting the data in the backend
     */
    public InitialSurveyResultsUpdateHelper(AsyncResponder delegate, Context context, String userId,
                                            Double ConfidenceOne, Double ConfidenceTwo, Double ConfidenceThree, Double ConfidenceFour ){
        this.delegate = delegate;
        this.context = context;
        this.userId = userId;
        this.ConfidenceOne = ConfidenceOne;
        this.ConfidenceTwo = ConfidenceTwo;
        this.ConfidenceThree = ConfidenceThree;
        this.ConfidenceFour = ConfidenceFour;
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
     * Gets the JSON file of comments from the backend database.
     * @throws IOException
     */
    private void updateQuestionOne() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/users/" + userId + "/updateLearningStyle/1");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("updatedValue", this.ConfidenceOne.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Connect to the backend.
            connection.connect();




            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
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

    private void updateQuestionTwo() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/users/" + userId + "/updateLearningStyle/2");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("updatedValue", this.ConfidenceTwo.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Connect to the backend.
            connection.connect();




            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
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

    private void updateQuestionThree() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/users/" + userId + "/updateLearningStyle/3");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("updatedValue", this.ConfidenceThree.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Connect to the backend.
            connection.connect();




            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
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

    private void updateQuestionFour() throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // URL of the backend.
            URL url = new URL("http://104.248.0.248/users/" + userId + "/updateLearningStyle/4");
            // Establish the connection with the backend.
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("updatedValue", this.ConfidenceFour.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Connect to the backend.
            connection.connect();




            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
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
            updateQuestionOne();
            updateQuestionTwo();
            updateQuestionThree();
            updateQuestionFour();

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
