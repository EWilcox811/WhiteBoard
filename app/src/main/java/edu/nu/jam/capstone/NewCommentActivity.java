package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewCommentActivity extends AppCompatActivity {
    //Intent extra
    public static final String EXTRA_NEW_COMMENT = "comment";

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
                intentReturn.putExtra(EXTRA_NEW_COMMENT, commentEditText.getText().toString());
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
