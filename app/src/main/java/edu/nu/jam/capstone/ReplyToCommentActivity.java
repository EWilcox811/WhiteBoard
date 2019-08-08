package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;

//TODO create functionality for the replies activity

public class ReplyToCommentActivity extends AppCompatActivity implements ICommentBoardOperations {
    private TextView topLevelCommentTextView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        bindControls();
        populateTopLevelComment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_to_comment);
    }

    private void populateTopLevelComment() {
        topLevelCommentTextView.setText(intent.getStringExtra("topLevelComment"));
    }

    private void bindControls()
    {
        intent = getIntent();
        topLevelCommentTextView = findViewById(R.id.topLevelCommentTextView);
    }

    /*
    This  list should be the list of replies attached to the top level comment that will be
    retrieved from the database using the top level comment or parent comment's ID
     */
    @Override
    public List<CommentData> onGetComments() {
        return null;
    }

    /*
    This should work similarly to the onItemSelected from the NavDrawerActivity because each reply
    to a comment can itself have replies to it.  Therefore, the clickListeners implemented in the
    NavDrawerActivity will also be implemented here.
     */
    @Override
    public void onItemSelected(int position, CardView view) {

    }
}
