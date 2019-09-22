package edu.nu.jam.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.nu.jam.capstone.Requestsmodule.AsyncResponder;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;
import edu.nu.jam.capstone.Requestsmodule.PasswordResetFirstStepHelper;

import static edu.nu.jam.capstone.MainActivity.EXTRA_USERNAME;

public class PasswordRecoveryActivity extends AppCompatActivity
{
    public static final String EXTRA_EMAIL = "email";

    private DatabaseHelper dbHelper;

    private Button submitButton;
    private EditText userNameEditText, emailEditText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        bindControls();
        userNameEditText.setText(intent.getStringExtra(EXTRA_USERNAME));
    }

    private void bindControls()
    {
        dbHelper = new DatabaseHelper();
        userNameEditText = findViewById(R.id.userNameEditView);
        emailEditText = findViewById(R.id.emailEditView);
        submitButton = findViewById(R.id.submitBtn);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                requestTempPassword();
            }
        });
    }

    private void requestTempPassword()
    {
        if (userNameEditText != null && !userNameEditText.getText().toString().isEmpty() || emailEditText != null && !emailEditText.getText().toString().isEmpty())
        {
            /**
             * TODO Validate userNameEditText or emailEditText with backend
             * if either is valid, backend will send an email to user with a temporary password
             * and application should take the user to the PasswordResetActivity
             *
             * the following should be done if validated.
             */
            dbHelper.SavePasswordResetFlagToSharedPreferences(this, "1");
            new PasswordResetFirstStepHelper(new AsyncResponder() {
                @Override
                public void processFinish(String output) {
                    dbHelper.onPasswordResetFirstStepFinished(output);
                    String userid = dbHelper.getUserId();
                    Intent intent = new Intent(PasswordRecoveryActivity.this, PasswordResetActivity.class);
                    if (!userNameEditText.getText().toString().isEmpty())
                        intent.putExtra(EXTRA_USERNAME, userNameEditText.getText().toString());
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            }, PasswordRecoveryActivity.this, userNameEditText.getText().toString(), emailEditText.getText().toString()).execute();


        } else
            Toast.makeText(this, "FIELDS ARE EMPTY", Toast.LENGTH_LONG).show();
    }
}