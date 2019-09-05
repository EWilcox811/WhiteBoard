package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;

import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT;

//TODO create functionality for the replies activity

public class ReplyToCommentActivity extends AppCompatActivity
{
    public static final String EXTRA_COMMENT_REPLY = "comment";
    public static final String EXTRA_IS_ANONYMOUS = "isAnonymous";

    private TextView topLevelCommentTextView;
    private Intent intent;
    private String message;
    private FloatingActionButton confirmCommentFAB;
    private EditText commentEditText;
    private AppCompatCheckBox isAnonymousCB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_to_comment);
        System.out.println("Reply to comment activity");
        bindControls();
        returnIntent();
        populateTopLevelComment();
    }

    private void populateTopLevelComment()
    {
        topLevelCommentTextView.setText(message);
    }

    private void returnIntent() {
        final Intent intentReturn = new Intent();
        confirmCommentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentReturn.putExtra(EXTRA_COMMENT_REPLY, commentEditText.getText().toString());
                intentReturn.putExtra(EXTRA_IS_ANONYMOUS, isAnonymousCB.isChecked());
                setResult(Activity.RESULT_OK, intentReturn);
                finish();
            }
        });

    }

    private void bindControls()
    {
        intent = getIntent();
        message = intent.getStringExtra(EXTRA_PARENT_COMMENT);
        topLevelCommentTextView = findViewById(R.id.topLevelCommentTextView);
        confirmCommentFAB = findViewById(R.id.confirmCommentFAB);
        commentEditText = findViewById(R.id.newCommentET);
        isAnonymousCB = findViewById(R.id.anonymityCheckBox);
    }


}
