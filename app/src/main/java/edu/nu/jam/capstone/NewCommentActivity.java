package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class NewCommentActivity extends AppCompatActivity {

    private FloatingActionButton confirmCommentFAB;
    private EditText commentEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        bindControls();
        returnIntent();
    }

    private void returnIntent() {
        final Intent intentReturn = new Intent();
        confirmCommentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentReturn.putExtra("comment", commentEditText.getText().toString());
                setResult(Activity.RESULT_OK, intentReturn);
                finish();
            }
        });

    }

    private void bindControls() {
        confirmCommentFAB = findViewById(R.id.confirmCommentFAB);
        commentEditText = findViewById(R.id.newCommentET);
    }
}
