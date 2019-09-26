package edu.nu.jam.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.nu.jam.capstone.Requestsmodule.AsyncResponder;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;
import edu.nu.jam.capstone.Requestsmodule.PasswordResetSecondStepHelper;

import static edu.nu.jam.capstone.MainActivity.EXTRA_USERNAME;
import static edu.nu.jam.capstone.MainActivity.EXTRA_USER_TYPE;

public class PasswordResetActivity extends AppCompatActivity
{
    private Button submitButton;
    private EditText userNameEditText, tempPassEditText, newPassEditText_1, newPassEditText_2;
    private Intent intent;
    private String username;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        bindControls();
        resetPassword();
    }

    private void resetPassword()
    {
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!newPassEditText_1.getText().toString().isEmpty() && !newPassEditText_2.getText().toString().isEmpty())
                {
                    if (newPassEditText_1.getText().toString().equals(newPassEditText_2.getText().toString()))
                    {
                        final DatabaseHelper dbhelper = new DatabaseHelper();
                        new PasswordResetSecondStepHelper(new AsyncResponder() {
                            @Override
                            public void processFinish(String output) {
                                Intent intent = new Intent(PasswordResetActivity.this, MainActivity.class);
                                dbhelper.SavePasswordResetFlagToSharedPreferences(PasswordResetActivity.this, "0");
                                startActivity(intent);
                            }
                        }, PasswordResetActivity.this, userid, tempPassEditText.getText().toString(), newPassEditText_1.getText().toString()).execute();
                        /**
                         * TODO send username, temp password and newPassEditText_1 to the backend for validation
                         * if valid send to login screen
                         * if invalid display Toast what was wrong and allow for another attempt.
                         * ERROR POSSIBILITIES FROM BACKEND.
                         * INVALID USER ID
                         * INVALID TEMPORARY PASSWORD
                         */
                    }
                }
            }
        });
    }

    private void bindControls()
    {
        userNameEditText = findViewById(R.id.userEditText);
        tempPassEditText = findViewById(R.id.tempPasswordEditText);
        newPassEditText_1 = findViewById(R.id.newPasswordEditView);
        newPassEditText_2 = findViewById(R.id.confirmNewPasswordEditView);
        submitButton = findViewById(R.id.submitBtn);
        username = intent.getStringExtra(EXTRA_USERNAME);
        userid = intent.getStringExtra("userid");
    }
}