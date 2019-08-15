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
    String loginToken;

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

    public void onLoginFinished(String result)
    {
        try {
            // Convert the JSON string into a parsable JSON object.
            JSONObject jsonObj = new JSONObject(result);
            // Grab the 'jwtToken' value.
            loginToken = jsonObj.getString("jwtToken");



        } catch (JSONException e) {
            // This is in case Mike fucked up.
            e.printStackTrace();
        }
    }

    public String getLoginToken()
    {
        return loginToken;
    }

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
}
