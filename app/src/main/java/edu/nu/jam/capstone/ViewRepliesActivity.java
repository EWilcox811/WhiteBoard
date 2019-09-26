package edu.nu.jam.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nu.jam.capstone.Adapters.CommentBoardAdapter;
import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;
import edu.nu.jam.capstone.Requestsmodule.AsyncResponder;
import edu.nu.jam.capstone.Requestsmodule.CommentRepliesListHelper;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;
import edu.nu.jam.capstone.Requestsmodule.ReplyToCommentHelper;
import edu.nu.jam.capstone.Requestsmodule.SessionListHelper;

import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT;
import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT_ID;
import static edu.nu.jam.capstone.NewCommentActivity.EXTRA_IS_ANONYMOUS;
import static edu.nu.jam.capstone.NewCommentActivity.EXTRA_NEW_COMMENT;

public class ViewRepliesActivity extends AppCompatActivity
implements ICommentBoardOperations
{
    private TextView parentCommentTV;
    private FloatingActionButton newReplyFAB;
    private RecyclerView replyStream;
    List<CommentData> repliesList = new ArrayList<>();
    private Intent intent;
    private String sessionid;
    private ArrayList<HashMap<String, String>> commentReplyListFromBackend;
    private DatabaseHelper dbHelper = new DatabaseHelper();
    private String parentCommentId = "";
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindControls();

        parentCommentTV.setText(intent.getStringExtra(EXTRA_PARENT_COMMENT));
        parentCommentId = intent.getStringExtra(EXTRA_PARENT_COMMENT_ID);

        if (dbHelper.GetSessionIdFromSharedPreferences(this).isEmpty())
        {
            getSessionId();
        } else
        {
            sessionid = dbHelper.getSessionId();
            getCommentList();
        }

        replyStream.setHasFixedSize(false);
        replyStream.setLayoutManager(new LinearLayoutManager(this));
        replyStream.setAdapter(new CommentBoardAdapter(this));
        replyStream.getAdapter().notifyDataSetChanged();
        // TODO:
        swipeRefreshLayout = findViewById(R.id.repliesSwipeRefreshComments);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getCommentList();
            }
        });

    }

    private void bindControls()
    {
        replyStream = findViewById(R.id.viewRepliesRecyclerView);
        parentCommentTV = findViewById(R.id.replies_parentComment);
        newReplyFAB = findViewById(R.id.addNewReplyFAB);
        newReplyFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    String comment = data.getStringExtra(EXTRA_NEW_COMMENT);
                    String parentid = data.getStringExtra(EXTRA_PARENT_COMMENT_ID);
                    String parentcomment = data.getStringExtra(EXTRA_PARENT_COMMENT);
                    if (comment.isEmpty()) {
                        break;
                    }
                    Boolean isAnonymous = data.getExtras().getBoolean(EXTRA_IS_ANONYMOUS);
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String username = dbHelper.GetUsernameFromSharedPreferences(ViewRepliesActivity.this);


                    String userID = dbHelper.GetUserIdFromSharedPreferences(ViewRepliesActivity.this);
                    String sessionID = dbHelper.GetSessionIdFromSharedPreferences(ViewRepliesActivity.this);
                    new ReplyToCommentHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {
                            getCommentList();
                        }
                    }, ViewRepliesActivity.this, comment, isAnonymous, userID, parentid).execute();
                    Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
                    intent.putExtra(EXTRA_PARENT_COMMENT, parentcomment);
                    intent.putExtra(EXTRA_PARENT_COMMENT_ID, parentid);
                    startActivity(intent);
                }
                break;
        }
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

    @Override
    public List<CommentData> onGetComments()
    {
        return repliesList;
    }

    @Override
    public void onItemSelected(int position)
    {

    }

    @Override
    public void onTextViewClicked(int position)
    {
        if(repliesList.get(position).getNumberOfReplies() == 0)
            return;
        Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, repliesList.get(position).getContent());
        intent.putExtra(EXTRA_PARENT_COMMENT_ID, repliesList.get(position).getCommentid());
//        intent.putExtra();
        startActivity(intent);
    }

    @Override
    public void onReplyClicked(int position)
    {
        Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, repliesList.get(position).getContent());
        intent.putExtra(EXTRA_PARENT_COMMENT_ID, repliesList.get(position).getCommentid());
        startActivityForResult(intent, 1);
    }

    @Override
    public void onUpVoteClicked(int position)
    {
        repliesList.get(position).setUpvotes(repliesList.get(position).getUpvotes() + 1);
        replyStream.getAdapter().notifyDataSetChanged();//should update the upvote count
    }

    public void getSessionId()
    {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        String userid = dbHelper.GetUserIdFromSharedPreferences(ViewRepliesActivity.this);
        new SessionListHelper(new AsyncResponder()
        {
            @Override
            public void processFinish(String output)
            {
                dbHelper.onGetSessionListCompleted(output);
                String freshSessionId = dbHelper.getSessionId();
                dbHelper.SaveSessionIdToSharedPreferences(ViewRepliesActivity.this, freshSessionId);
                sessionid = freshSessionId;
                getCommentList();
            }
        }, ViewRepliesActivity.this, userid).execute();
    }

    public void getCommentList()
    {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        new CommentRepliesListHelper(new AsyncResponder()
        {
            @Override
            public void processFinish(String output)
            {
                repliesList.clear();
                dbHelper.onGetCommentReplyListCompleted(output);
                commentReplyListFromBackend = dbHelper.getCommentReplyList();
                for (int i = 0; i < commentReplyListFromBackend.size(); i++) {
                    String comment = commentReplyListFromBackend.get(i).get("message");
                    String username = commentReplyListFromBackend.get(i).get("username");
                    //int upVotes = Integer.parseInt(commentListFromBackend.get(i).get("upvotes"));
                    int upVotes = 0;
                    int numberOfReplies = Integer.parseInt(commentReplyListFromBackend.get(i).get("numofreplies"));
                    if(commentReplyListFromBackend.get(i).get("isanonymous").contains("1")) {
                        username = "Anonymous";
                    }
                    if(commentReplyListFromBackend.get(i).get("username") == dbHelper.GetUsernameFromSharedPreferences(ViewRepliesActivity.this)
                            && commentReplyListFromBackend.get(i).get("isanonymous").contains("1")){
                        username = commentReplyListFromBackend.get(i).get("username") + " (Anonymous to Others)";
                    }
                    String commentid = commentReplyListFromBackend.get(i).get("commentid");
                    String parentid = commentReplyListFromBackend.get(i).get("parentid");


                    CommentData commentCard = new CommentData(comment, 0, upVotes, numberOfReplies, null, username, commentid, parentid);
                    repliesList.add(commentCard);
                }

                replyStream.getAdapter().notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, ViewRepliesActivity.this, parentCommentId).execute();



    }

    public ArrayList<HashMap<String, String>> getCommentReplyListFromBackend() {
        return commentReplyListFromBackend;
    }
}
