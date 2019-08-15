package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import edu.nu.jam.capstone.Requestsmodule.*;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper dbHelper;
    private EditText username;
    private EditText password;
	private Button loginButton;
	private CheckBox RememberMe;
	private String loginToken;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//clearSharedPreferences();

		getSharedPreferencesData();

	}

	private void clearSharedPreferences() {
		// FOR TESTING/DEBUGGING PURPOSES ONLY
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.clear();
		editor.commit();
	}

	private void getSharedPreferencesData() {
		dbHelper = new DatabaseHelper();
		loginToken = dbHelper.GetLoginTokenFromSharedPreferences(this);
		List<String> userInfo = new ArrayList<>();
		userInfo = dbHelper.GetUserInfoFromSharedPreferences(this);
		boolean rememberMe = dbHelper.GetRememberMeFromSharedPreferences(this);
		if(loginToken.isEmpty() || userInfo.get(0).isEmpty() || !rememberMe) {
			System.out.println("Login Token or User Data Not Found");
			bindControlsAndData();
		}
		else {
			startNewActivity();
		}
	}

	private void bindControlsAndData() {
	    username = findViewById(R.id.userNameEditView);
	    password = findViewById(R.id.emailEditView);
		loginButton = findViewById(R.id.loginBtn);
		RememberMe = findViewById(R.id.rememberMeCheckBox);
		loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameText = username.getText().toString();
                final String passwordText = password.getText().toString();
                new LoginHelper(new AsyncResponder(){
                    @Override
                    public void processFinish(String output){
                        dbHelper = new DatabaseHelper();
                        if(RememberMe.isChecked()) {
							dbHelper.SaveUserInfoToSharedPreferences(MainActivity.this, usernameText, passwordText, true);
						}
                        dbHelper.onLoginFinished(output);
                        String token = dbHelper.getLoginToken();
                        dbHelper.SaveLoginTokenToSharedPreferences(MainActivity.this);
                        System.out.println(token);
                        startNewActivity();
                    }
                }, MainActivity.this, usernameText, passwordText).execute();
            }
        });
	}

	private void startNewActivity()
	{
		Intent intent = new Intent(this, NavDrawerActivity.class);
		startActivity(intent);
	}
}

