package edu.nu.jam.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT;

//TODO create functionality for the replies activity

public class ReplyToCommentActivity extends AppCompatActivity
{
    public static final String EXTRA_COMMENT_REPLY = "comment";
    public static final String EXTRA_IS_ANONYMOUS = "isAnonymous";
    public static final String EXTRA_PARENT_COMMENT_ID = "repliesList";
    public static final String EXTRA_PARENT_COMMENT = "parentComment";


    private TextView topLevelCommentTextView;
    private Intent intent;
    private String message;
    private FloatingActionButton confirmCommentFAB;
    private EditText commentEditText;
    private AppCompatCheckBox isAnonymousCB;
    private String parentCommentId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_to_comment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                intentReturn.putExtra(EXTRA_PARENT_COMMENT_ID, parentCommentId);
                intentReturn.putExtra(EXTRA_PARENT_COMMENT, message);

//                intentReturn.putExtra();
                setResult(Activity.RESULT_OK, intentReturn);
                finish();
            }
        });

    }

    private void bindControls()
    {
        intent = getIntent();
        message = intent.getStringExtra(EXTRA_PARENT_COMMENT);
        parentCommentId = intent.getStringExtra(EXTRA_PARENT_COMMENT_ID);
        topLevelCommentTextView = findViewById(R.id.topLevelCommentTextView);
        confirmCommentFAB = findViewById(R.id.confirmCommentFAB);
        commentEditText = findViewById(R.id.newCommentET);
        isAnonymousCB = findViewById(R.id.anonymityCheckBox);
    }

    /**
     * Used for the toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
