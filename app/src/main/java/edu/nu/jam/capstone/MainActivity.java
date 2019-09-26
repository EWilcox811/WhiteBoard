package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.nu.jam.capstone.Requestsmodule.*;

public class MainActivity extends AppCompatActivity
{

	public static final String EXTRA_USER_TYPE = "userType";
	public static final String EXTRA_USERNAME = "userName";

	private DatabaseHelper dbHelper;
    private EditText username;
    private EditText password;
	private Button loginButton;
	private CheckBox RememberMe;
	private String loginToken;

	private TextView forgotPasswordTextView, incorrectCredentialsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper();
		if(!dbHelper.GetPasswordResetFlagFromSharedPreferences(this).equals("1"))
        {
            setContentView(R.layout.activity_login);
            getSharedPreferencesData();
        }
		else
        {
            Intent intent = new Intent(this, PasswordResetActivity.class);
            startActivity(intent);
        }

	}

	private void clearSharedPreferences() {
		// FOR TESTING/DEBUGGING PURPOSES ONLY
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.clear();
		editor.commit();
	}

	private void getSharedPreferencesData() {
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
	    password = findViewById(R.id.passwordEditView);
		loginButton = findViewById(R.id.loginBtn);
		RememberMe = findViewById(R.id.rememberMeCheckBox);
		incorrectCredentialsTextView = findViewById(R.id.incorrectCredentialsTextView);
		loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				final String usernameText = username.getText().toString();
				final String passwordText = password.getText().toString();
				new LoginHelper(new AsyncResponder() {
					@Override
					public void processFinish(String output) {
						incorrectCredentialsTextView.setText("");
						if(output == null) {
							incorrectCredentialsTextView.setText("Incorrect username or password");
							return;
						}
						/**
						 * TODO check for correct credentials
						 * if credentials are incorrect, set incorrectCredentialsTextView to
						 * incorrectCredentialsTextView.setText(R.string.credentials_incorrect);
						 */
						dbHelper = new DatabaseHelper();
						if (RememberMe.isChecked()) {
							dbHelper.SaveUserInfoToSharedPreferences(MainActivity.this, usernameText, passwordText, true);
						}
						dbHelper.onLoginFinished(output);
						String token = dbHelper.getLoginToken();
						dbHelper.SaveLoginTokenToSharedPreferences(MainActivity.this);
						System.out.println(token);
						String userid = dbHelper.getUserId();
						dbHelper.SaveUserIdToSharedPreferences(MainActivity.this, userid);
						String userType = dbHelper.getUserType();
						dbHelper.SaveUserTypeToSharedPreferences(MainActivity.this, userType);
						dbHelper.SaveUsernameToSharedPreferences(MainActivity.this, usernameText);
						startNewActivity();
					}

				}, MainActivity.this, usernameText, passwordText).execute();
			}
        });
		forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
		forgotPasswordTextView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(MainActivity.this, PasswordRecoveryActivity.class);
				if(null != username.getText().toString())
					intent.putExtra(EXTRA_USERNAME, username.getText().toString());
				startActivity(intent);
			}
		});
	}

	private void startNewActivity()
	{
		String userType = dbHelper.GetUserTypeFromSharedPreferences(MainActivity.this);
		Intent intent = new Intent(this, NavDrawerActivity.class);
		intent.putExtra(EXTRA_USER_TYPE, userType);
		startActivity(intent);
	}
}

