package edu.nu.jam.capstone.Requestsmodule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterOutputStream;

import javax.net.ssl.HttpsURLConnection;

public class DatabaseHelper {

    ArrayList<HashMap<String,String>> classList  = new ArrayList<HashMap<String,String>>();
    ArrayList<HashMap<String, String>> commentList = new ArrayList<>();
    ArrayList<HashMap<String, String>> resultList = new ArrayList<>();
    String loginToken;
    String userid;
    String usertype;
    String sessionid;



    /**
     * Builds the ArrayList of classes with the HashMap of strings
     * @param result the JSON string from the ClassListHelper Async task.
     */
    public  void onClassListHelperCompleted(String result)
    {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(result);
            // Grab the '_embedded' value.
            JSONObject _embedded = jsonObj.getJSONObject("_embedded");
            // Grab the array of classees from '_embedded' using the classes key.
            JSONArray classes = _embedded.getJSONArray("classes");
            // Iterate through the JSON classes array
            for (int i = 0; i<classes.length();i++) {
                // Set up objects for the HashMap
                JSONObject c = classes.getJSONObject(i);
                // Grab the value of the subject key.
                String subject = c.getString("subject");
                // Grab the value of the classNumber key.
                String classNumber = c.getString("classNumber");
                // The classLink value is nested, so we need to grab it from within '_links', 'klass', then the string from the 'href' key.
                String classLink = c.getJSONObject("_links").getJSONObject("klass").getString("href");
                // The sessionList value is nested, so we need to grab it from within '_links', 'sessionList', then the string from the 'href' key.
                String sessionList = c.getJSONObject("_links").getJSONObject("sessionList").getString("href");

                // Make the HashMap for the values.
                HashMap<String, String> klass = new HashMap<>();
                klass.put("subject", subject);
                klass.put("classNumber", classNumber);
                klass.put("classLink", classLink);
                klass.put("sessionList", sessionList);

                // Add the HashMap to the ArrayList.
                classList.add(klass);
            }


        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }

    }

    /**
     * HashMap:
     * subject, classNumber, classLink, sessionList
     *
     * @return returns the ArrayList<HashMap<String, String> for the list of classes.
     */
    public ArrayList<HashMap<String,String>> getClassList()
    {
        // Returns the classList. Don't use if you haven't ran the onClassListHelperCompleted first.
        return classList;
    }

    public void onGetSessionListCompleted(String result) {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(result);
            // Set the Session ID variable
            sessionid = jsonObj.getString("id");
        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }
    }

    public String getSessionId()
    {
        return sessionid;
    }

    public void onGetCommentListCompleted(String result) {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(result);
            // Grab the '_embedded' value.
            JSONObject _embedded = jsonObj.getJSONObject("_embedded");
            // Grab the array of comments from '_embedded' using the comments key.
            JSONArray comments = _embedded.getJSONArray("comments");
            // Iterate through the JSON comments array
            for (int i = 0; i<comments.length();i++) {
                // Set up objects for the HashMap
                JSONObject c = comments.getJSONObject(i);
                // Grab the value of the subject key.
                String message = c.getString("message");
                // Grab the value of the parentid key.
                String parentid = c.getString("parentId");
                // Grab the value of the number of replies key.
                String numofreplies = c.getString("numOfRelies");
                // Grab the value of the username key.
                String username = c.getString("userName");
                // Grab the value of the isAnonymous key.
                String isAnonymous = c.getString("isAnonymous");
                // Grab the value of the date created key.
                String datecreated = c.getString("dateCreated");
                // Grab the value of the upvotes key
                String upvotes = "1";

                // Make the HashMap for the values.
                HashMap<String, String> comment = new HashMap<>();
                comment.put("message", message);
                comment.put("parentid", parentid);
                comment.put("numofreplies", numofreplies);
                comment.put("username", username);
                comment.put("isanonymous", isAnonymous);
                comment.put("datecreated", datecreated);
                comment.put("upvotes", upvotes);

                // Add the HashMap to the ArrayList.
                commentList.add(comment);
            }


        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }
    }

    /**
     * HashMap:
     * message, parentid, numofreplies, username, isanonymous, datecreated
     *
     * @return returns the ArrayList<HashMap<String, String> for the list of classes.
     */
    public ArrayList<HashMap<String, String>> getCommentList() {
        return commentList;
    }

    public void onGetWeeklyELOResultsFinished(String jsonString)
    {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(jsonString);
            // Grab the '_embedded' value.
            // Grab the array of comments from '_embedded' using the comments key.
            JSONArray results = jsonObj.getJSONArray(jsonString);
            // Iterate through the JSON comments array
            for (int i = 0; i<results.length();i++) {
                // Set up objects for the HashMap
                JSONObject c = results.getJSONObject(i);
                // Grab the value of the id key.
                String id = c.getString("id");
                // Grab the value of the question key.
                String question = c.getString("question");
                // Grab the value of the number of average key.
                String average = c.getString("average");
                // Grab the value of the totalResponses key.
                String totalresponses = c.getString("totalResponses");

                // Make the HashMap for the values.
                HashMap<String, String> result = new HashMap<>();
                result.put("id", id);
                result.put("question", question);
                result.put("average", average);
                result.put("totalresponses", totalresponses);

                // Add the HashMap to the ArrayList.
                resultList.add(result);
            }

        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }
    }

    /**
     * HashMap:
     * id, question, average, totalresponse
     *
     * @return returns the ArrayList<HashMap<String, String> for the list of classes.
     */
    public ArrayList<HashMap<String, String>> getResultList() {
        return resultList;
    }



    public void onLoginFinished(String result)
    {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(result);
            // Grab the 'jwtToken' value.
            loginToken = jsonObj.getString("jwtToken");
            userid = jsonObj.getString("userId");
            usertype = jsonObj.getString("userType");

        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }
    }

    public String getLoginToken()
    {
        return loginToken;
    }

    public String getUserId() { return userid; }

    public String getUserType() {return usertype;}

    public void SaveLoginTokenToSharedPreferences(Context context) {
        System.out.println("Saving Login Token to Shared Preferences");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("com.whiteboard.key", getLoginToken());
        editor.commit();
    }

    public String GetLoginTokenFromSharedPreferences(Context context) {
        System.out.println("Loading Login Token From Shared Preferences");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPref.getString("com.whiteboard.key", "");
        System.out.println("Shared Preferences Token: " + token);
        return token;
    }

    public void SaveUserInfoToSharedPreferences(Context context, String username, String hashword, boolean rememberMe) {
        System.out.println("Saving User Info to Shared Preferences");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("com.whiteboard.username", username);
        editor.putString("com.whiteboard.hashword", hashword);
        editor.putBoolean("com.whiteboard.rememberMe", rememberMe);
        editor.commit();
    }

    public List<String> GetUserInfoFromSharedPreferences(Context context) {
        System.out.println("Loading User Info From Shared Preferences");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String username = sharedPref.getString("com.whiteboard.username", "");
        String hashword = sharedPref.getString("com.whiteboard.hashword", "");
        List<String> userInfo = new ArrayList<>();
        userInfo.add(username);
        userInfo.add(hashword);
        System.out.println("Shared Preferences Username: " + username);
        return userInfo;
    }

    public boolean GetRememberMeFromSharedPreferences(Context context) {
        System.out.println("Loading Remember Me Boolean From Shared Preferences");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean rememberme = sharedPref.getBoolean("com.whiteboard.rememberMe", false);
        return rememberme;
    }

    public void SaveUserIdToSharedPreferences(Context context, String userId) {
        System.out.println("Saving User Id to Shared Preferences: " + userid);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("com.whiteboard.userid", userId);
        editor.commit();
    }

    public String GetUserIdFromSharedPreferences(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        userid = sharedPref.getString("com.whiteboard.userid", "");
        System.out.println("Loading User Id From Shared Preferences: " + userid);
        return userid;
    }

    public void SaveUserTypeToSharedPreferences(Context context, String usertype) {
        System.out.println("Saving User Type to Shared Preferences: " + userid);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("com.whiteboard.usertype", usertype);
        editor.commit();
    }

        public String GetUserTypeFromSharedPreferences(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        usertype = sharedPref.getString("com.whiteboard.usertype", "");
        System.out.println("Loading User Type From Shared Preferences: " + usertype);
        return usertype;
    }

    public void SaveSessionIdToSharedPreferences(Context context, String sessionid) {
        System.out.println("Saving Session Id to Shared Preferences: " + sessionid);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("com.whiteboard.sessionid", sessionid);
        editor.commit();
    }

    public String GetSessionIdFromSharedPreferences(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sessionid = sharedPref.getString("com.whiteboard.sessionid", "");
        System.out.println("Loading Session Id From Shared Preferences: " + sessionid);
        return sessionid;
    }
}
