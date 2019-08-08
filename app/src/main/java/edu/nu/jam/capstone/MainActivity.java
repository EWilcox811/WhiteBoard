package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.nu.jam.capstone.Requestsmodule.*;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper dbHelper;
    private EditText username;
    private EditText password;
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bindControlsAndData();
	}

	private void bindControlsAndData() {
	    username = findViewById(R.id.userNameEditView);
	    password = findViewById(R.id.emailEditView);
		loginButton = findViewById(R.id.loginBtn);
		loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                new LoginHelper(new AsyncResponder(){
                    @Override
                    public void processFinish(String output){
                        dbHelper = new DatabaseHelper();
                        dbHelper.onLoginFinished(output);
                        String token = dbHelper.getLoginToken();
                        System.out.println(token);
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

