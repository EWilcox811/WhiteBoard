package edu.nu.jam.capstone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordRecoveryActivity extends AppCompatActivity
{
    private Button submitButton;
    private EditText userNameEditText, emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        bindControls();
        requestTempPassword();
    }

    private void bindControls()
    {
        userNameEditText =  findViewById(R.id.userNameEditView);
        emailEditText = findViewById(R.id.emailEditView);
        submitButton = findViewById(R.id.submitBtn);
    }

    private void requestTempPassword()
    {
        if(!userNameEditText.getText().toString().isEmpty() || !emailEditText.getText().toString().isEmpty())
        {
            /**
             * TODO Validate userNameEditText or emailEditText with backend
             * if valid, backend will send an email to user with a temporary password
             * and application should take the user to the PasswordResetActivity
             */
        }
    }
}