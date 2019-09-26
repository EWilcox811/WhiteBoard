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

    private String emailAddress, userName;

    private DatabaseHelper dbHelper;

    private Button submitButton;
    private EditText userNameEditText;
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
        if (userNameEditText != null && !userNameEditText.getText().toString().isEmpty())
        {
            /**
             * if either is valid, backend will send an email to user with a temporary password
             * and application should take the user to the PasswordResetActivity
             *
             * the following should be done if validated.
             */
            if(userNameEditText.getText().toString().contains("@"))
            {
                userName = "";
                emailAddress = userNameEditText.getText().toString();
            }
            else
            {
                userName = userNameEditText.getText().toString();
                emailAddress = "";
            }
            dbHelper.SavePasswordResetFlagToSharedPreferences(this, "1");
            new PasswordResetFirstStepHelper(new AsyncResponder() {
                @Override
                public void processFinish(String output) {
                    dbHelper.onPasswordResetFirstStepFinished(output);
                    String userid = dbHelper.getUserId();
                    Intent intent = new Intent(PasswordRecoveryActivity.this, PasswordResetActivity.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            }, PasswordRecoveryActivity.this, userName, emailAddress).execute();


        } else
            Toast.makeText(this, "FIELDS ARE EMPTY", Toast.LENGTH_LONG).show();
    }
}