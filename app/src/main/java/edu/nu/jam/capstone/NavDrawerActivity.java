package edu.nu.jam.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nu.jam.capstone.Adapters.CommentBoardAdapter;
import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;
import edu.nu.jam.capstone.Requestsmodule.AsyncResponder;
import edu.nu.jam.capstone.Requestsmodule.CommentListHelper;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;
import edu.nu.jam.capstone.Requestsmodule.SessionListHelper;

public class NavDrawerActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, ICommentBoardOperations
{

    //TODO make sure that there is only the necessary objects
    private FloatingActionButton newCommentFAB, confirmCommentFAB, replyToCommentFAB;
    private ImageView repliesImageView, upVotesImageView;
    private TextView repliesCount, upVotesCount, commentTextView;
    private CommentData commentCard;
    private List<CommentData> subComments = new ArrayList<>();
    List<CommentData> topLevelList = new ArrayList<>();
    private RecyclerView commentStream;
    private String sessionid;
    private ArrayList<HashMap<String,String>> commentListFromBackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper();
        if (dbHelper.GetSessionIdFromSharedPreferences(this).isEmpty()) {
            getSessionId();
        }
        else {
            sessionid = dbHelper.getSessionId();
        }

        getCommmentList();


        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        bindControls();

        commentStream.setHasFixedSize(false);
        commentStream.setLayoutManager(new LinearLayoutManager(this));
        commentStream.setAdapter(new CommentBoardAdapter(this));
        commentStream.getAdapter().notifyDataSetChanged();

    }

    private void bindControls() {
        commentTextView = findViewById(R.id.commentCardTextView);
        newCommentFAB = findViewById(R.id.newCommentFAB);
        newCommentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), NewCommentActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        commentStream = findViewById(R.id.commentStreamRecycler);
        repliesImageView = findViewById(R.id.replyToComment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case(R.id.action_settings):
                //TODO takes them to their profile setting
                break;
            case(R.id.action_logout):
                //TODO logs them out and sends them back to login screen
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){
            case(R.id.nav_progressiveSurvey):
                //TODO send to progressive survey activity
                break;
            case(R.id.nav_logout):
                //TODO logout and send back to login screen
                break;
            case(R.id.nav_newComment):
                Intent intent = new Intent(this, NewCommentActivity.class);
                startActivityForResult(intent, 1);
                break;
            // Handle the camera action
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode == Activity.RESULT_OK)
                {
                    String comment = data.getStringExtra("comment");
                    CommentData commentCard = new CommentData(comment, 0,0,0, subComments);
                    topLevelList.add(commentCard);
                    commentStream.getAdapter().notifyDataSetChanged();
                    repliesImageView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);

                            intent.putExtra("topLevelComment", commentTextView.getText().toString());
                            startActivityForResult(intent, 2);
                        }
                    });
                }
                break;
            case 2:
                if(resultCode == Activity.RESULT_OK)
                {

                }
        }
    }

    @Override
    public List<CommentData> onGetComments() {
        return topLevelList;
    }

    @Override
    public void onItemSelected(int position, CardView cardView)
    {


    }

    public void getSessionId() {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        String userid = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
        new SessionListHelper(new AsyncResponder(){
            @Override
            public void processFinish(String output){
                dbHelper.onGetSessionListCompleted(output);
                String freshSessionId = dbHelper.getSessionId();
                dbHelper.SaveSessionIdToSharedPreferences(NavDrawerActivity.this, freshSessionId);
                sessionid = freshSessionId;

            }
        },NavDrawerActivity.this, userid).execute();
    }

    public void getCommmentList() {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        new CommentListHelper(new AsyncResponder() {
            @Override
            public void processFinish(String output) {
                dbHelper.onGetCommentListCompleted(output);
                commentListFromBackend = dbHelper.getCommentList();
            }
        }, NavDrawerActivity.this, sessionid).execute();
    }
}
