package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
{
	static final String EXTRA_MESSAGE = "extraString";
	private Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bindControlsAndData();
	}

	private void bindControlsAndData() {
		loginButton = findViewById(R.id.loginBtn);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startNewActivity();
			}
		});
	}

	private void startNewActivity()
	{
		Intent intent = new Intent(this, NavDrawerActivity.class);
		String message = "Toast test";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
