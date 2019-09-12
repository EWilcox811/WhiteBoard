package edu.nu.jam.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.nu.jam.capstone.Adapters.CommentBoardAdapter;
import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;

import static edu.nu.jam.capstone.NavDrawerActivity.EXTRA_PARENT_COMMENT;

public class ViewRepliesActivity extends AppCompatActivity
implements ICommentBoardOperations
{
    private TextView parentCommentTV;
    private FloatingActionButton newReplyFAB;
    private RecyclerView replyStream;
    List<CommentData> repliesList = new ArrayList<>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindControls();

        parentCommentTV.setText(intent.getStringExtra(EXTRA_PARENT_COMMENT));

        replyStream.setHasFixedSize(false);
        replyStream.setLayoutManager(new LinearLayoutManager(this));
        replyStream.setAdapter(new CommentBoardAdapter(this));
        replyStream.getAdapter().notifyDataSetChanged();

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
        Toast.makeText(getApplicationContext(), "Comment Text View Clicked", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, repliesList.get(position).getContent());
//        intent.putExtra();
        startActivity(intent);
    }

    @Override
    public void onReplyClicked(int position)
    {
        Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, repliesList.get(position).getContent());
        startActivityForResult(intent, 2);
    }

    @Override
    public void onUpVoteClicked(int position)
    {
        repliesList.get(position).setUpvotes(repliesList.get(position).getUpvotes() + 1);
        replyStream.getAdapter().notifyDataSetChanged();//should update the upvote count
    }
}
