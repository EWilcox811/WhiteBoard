package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;

import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT;

//TODO create functionality for the replies activity

public class ReplyToCommentActivity extends AppCompatActivity implements ICommentBoardOperations
{
    private TextView topLevelCommentTextView;
    private Intent intent;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_to_comment);

        bindControls();
        populateTopLevelComment();
    }

    private void populateTopLevelComment()
    {
        topLevelCommentTextView.setText(message);
    }

    private void bindControls()
    {
        intent = getIntent();
        message = intent.getStringExtra(EXTRA_PARENT_COMMENT);
        topLevelCommentTextView = findViewById(R.id.topLevelCommentTextView);
    }

    /*
    This  list should be the list of replies attached to the top level comment that will be
    retrieved from the database using the top level comment or parent comment's ID
     */
    @Override
    public List<CommentData> onGetComments()
    {
        return null;
    }

    @Override
    public void onItemSelected(int position)
    {

    }

    @Override
    public void onTextViewClicked(int position)
    {

    }

    @Override
    public void onReplyClicked(int position)
    {

    }

    @Override
    public void onUpVoteClicked(int position)
    {

    }

    /*
    This should work similarly to the onItemSelected from the NavDrawerActivity because each reply
    to a comment can itself have replies to it.  Therefore, the clickListeners implemented in the
    NavDrawerActivity will also be implemented here.
     */
}
