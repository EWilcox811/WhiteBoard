package edu.nu.jam.capstone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nu.jam.capstone.Adapters.CommentBoardAdapter;
import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;
import edu.nu.jam.capstone.Requestsmodule.AddCommentHelper;
import edu.nu.jam.capstone.Requestsmodule.AsyncResponder;
import edu.nu.jam.capstone.Requestsmodule.CommentListHelper;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;
import edu.nu.jam.capstone.Requestsmodule.ReplyToCommentHelper;
import edu.nu.jam.capstone.Requestsmodule.SessionListHelper;

import static edu.nu.jam.capstone.MainActivity.EXTRA_USER_TYPE;
import static edu.nu.jam.capstone.NewCommentActivity.EXTRA_IS_ANONYMOUS;
import static edu.nu.jam.capstone.NewCommentActivity.EXTRA_NEW_COMMENT;
import static edu.nu.jam.capstone.ReplyToCommentActivity.EXTRA_PARENT_COMMENT_ID;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ICommentBoardOperations
{
    //Intent extras
    public static final String EXTRA_PARENT_COMMENT_ID = "repliesList";
    public static final String EXTRA_PARENT_COMMENT = "parentComment";
    public static final String EXTRA_WEEK_NUMBER = "weekNumber";


    //TODO make sure that there is only the necessary objects
    private FloatingActionButton newCommentFAB;
    private FloatingActionButton newCommentReplyFAB;
    private TextView repliesCount, upVotesCount, commentTextView;
    private CommentData commentCard;
    private List<CommentData> subComments = new ArrayList<>();
    List<CommentData> topLevelList = new ArrayList<>();
    NavigationView navigationView;
    private Menu professorNavView;
    private RecyclerView commentStream;
    private String sessionid;
    private String userType;
    private int cardPosition;
    private CommentBoardAdapter adapter;
    private DatabaseHelper dbHelper = new DatabaseHelper();

    private ArrayList<HashMap<String, String>> commentListFromBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intent = getIntent();
        userType = intent.getStringExtra(EXTRA_USER_TYPE);
        super.onCreate(savedInstanceState);
        if (dbHelper.GetSessionIdFromSharedPreferences(this).isEmpty())
        {
            getSessionId();
        } else
        {
            sessionid = dbHelper.getSessionId();
            getCommentList();
        }


        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        bindControls();
        setNavDrawer();

        commentStream.setHasFixedSize(false);
        commentStream.setLayoutManager(new LinearLayoutManager(this));
        commentStream.setAdapter(adapter);
        commentStream.getAdapter().notifyDataSetChanged();

    }

    private void setNavDrawer()
    {
        if (dbHelper.GetUserTypeFromSharedPreferences(this).startsWith("p") ||
                dbHelper.GetUserTypeFromSharedPreferences(this).startsWith("P"))
        {
            professorNavView.findItem(R.id.nav_professorButtonGroup).setVisible(true);
        }

    }

    private void bindControls()
    {
        adapter = new CommentBoardAdapter(this);
        newCommentFAB = findViewById(R.id.newCommentFAB);
        newCommentFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), NewCommentActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        commentStream = findViewById(R.id.commentStreamRecycler);
        navigationView = findViewById(R.id.nav_drawer);
        professorNavView = navigationView.getMenu();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case (R.id.action_settings):
                //TODO takes them to their profile setting
                break;
            case (R.id.action_logout):
                //TODO logs them out and sends them back to login screen
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case (R.id.nav_howILearn):
                //TODO send to initial survey activity
                Intent initialSurveyIntent = new Intent(this, InitialSurveyActivity.class);
                startActivity(initialSurveyIntent);
                break;
            case (R.id.nav_progressiveSurvey):
                //TODO send to progressive survey activity
                Intent progressiveSurveyIntent = new Intent(this, ProgressiveSurveyActivity.class);
                progressiveSurveyIntent.putExtra(EXTRA_WEEK_NUMBER, 2);
                startActivity(progressiveSurveyIntent);
                break;
            case (R.id.nav_logout):
                //TODO logout and send back to login screen
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                editor.clear();
                editor.commit();
                Intent logoutIntent = new Intent(this, MainActivity.class);
                startActivity(logoutIntent);
                break;
            case (R.id.nav_newComment):
                Intent newCommentIntent = new Intent(this, NewCommentActivity.class);
                startActivityForResult(newCommentIntent, 1);
                break;
            case (R.id.nav_surveyResults):
                //TODO send to survey selection activity
                Intent surveySelectionIntent = new Intent(this, SurveySelectionActivity.class);
                startActivity(surveySelectionIntent);
                break;
            case (R.id.nav_sessionSelection):
                //TODO send to session selection activity
                Intent sessionSelectionIntent = new Intent(this, SessionSelectionActivity.class);
                startActivity(sessionSelectionIntent);
                break;
            case (R.id.nav_majorityConcern):
                //Bonus Item
                //TODO send to majority concern activity
                Intent majorityConcernIntent = new Intent(this, MajorityConcernActivity.class);
                startActivity(majorityConcernIntent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case 1:
                if (resultCode == Activity.RESULT_OK)
                {
                    String comment = data.getStringExtra(EXTRA_NEW_COMMENT);
                    Boolean isAnonymous = data.getExtras().getBoolean(EXTRA_IS_ANONYMOUS);
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String username = dbHelper.GetSUsernameFromSharedPreferences(NavDrawerActivity.this);
                    CommentData commentCard = new CommentData(comment, 0, 0, 0, subComments, username);
                    topLevelList.add(commentCard);


                    String userID = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
                    String sessionID = dbHelper.GetSessionIdFromSharedPreferences(NavDrawerActivity.this);
                    new AddCommentHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {
                            getCommentList();
                        }
                    }, NavDrawerActivity.this, sessionID, comment, isAnonymous, userID).execute();
                    commentStream.getAdapter().notifyDataSetChanged();
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK)
                {
                    /*
                    String comment = data.getStringExtra(EXTRA_NEW_COMMENT);
                    Boolean isAnonymous = data.getExtras().getBoolean(EXTRA_IS_ANONYMOUS);
                    String parentCommentId = data.getStringExtra(EXTRA_PARENT_COMMENT_ID);
                    CommentData commentCard = new CommentData(comment, 0, 0, 0, subComments);
                    topLevelList.add(commentCard);

                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String userID = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
                    String sessionID = dbHelper.GetSessionIdFromSharedPreferences(NavDrawerActivity.this);
                    new ReplyToCommentHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {
                            getCommentList();
                        }
                    }, NavDrawerActivity.this, comment, isAnonymous, userID, parentCommentId).execute();
                    commentStream.getAdapter().notifyDataSetChanged();
                    */
                }
                break;
        }
    }

    @Override
    public List<CommentData> onGetComments()
    {
        return topLevelList;
    }

    @Override
    public void onItemSelected(int position)
    {
        cardPosition = position;
        Toast.makeText(this, "You clicked card " + position, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTextViewClicked(int position)
    {
        Toast.makeText(getApplicationContext(), "Comment Text View Clicked", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, topLevelList.get(cardPosition).getContent());
//        intent.putExtra()
    }

    @Override
    public void onReplyClicked(int position)
    {
        Toast.makeText(getApplicationContext(), "Reply image clicked", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, topLevelList.get(cardPosition).getContent());
        startActivityForResult(intent, 2);
    }

    @Override
    public void onUpVoteClicked(int position)
    {
        topLevelList.get(position).setUpvotes(topLevelList.get(position).getUpvotes() + 1);
    }

    public void getSessionId()
    {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        String userid = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
        new SessionListHelper(new AsyncResponder()
        {
            @Override
            public void processFinish(String output)
            {
                dbHelper.onGetSessionListCompleted(output);
                String freshSessionId = dbHelper.getSessionId();
                dbHelper.SaveSessionIdToSharedPreferences(NavDrawerActivity.this, freshSessionId);
                sessionid = freshSessionId;
                getCommentList();
            }
        }, NavDrawerActivity.this, userid).execute();
    }

    public void getCommentList()
    {
        final DatabaseHelper dbHelper = new DatabaseHelper();
        new CommentListHelper(new AsyncResponder()
        {
            @Override
            public void processFinish(String output)
            {
                topLevelList.clear();
                dbHelper.onGetCommentListCompleted(output);
                commentListFromBackend = dbHelper.getCommentList();
                System.out.println(commentListFromBackend);
                for (int i = 0; i < commentListFromBackend.size(); i++) {
                    String comment = commentListFromBackend.get(i).get("message");
                    String username = commentListFromBackend.get(i).get("username");
                    //int upVotes = Integer.parseInt(commentListFromBackend.get(i).get("upvotes"));
                    int upVotes = 0;
                    int numberOfReplies = Integer.parseInt(commentListFromBackend.get(i).get("numofreplies"));
                    if(commentListFromBackend.get(i).get("isanonymous").contains("1")) {
                        username = "Anonymous";
                    }
                    if(commentListFromBackend.get(i).get("username") == dbHelper.GetSUsernameFromSharedPreferences(NavDrawerActivity.this)
                    && commentListFromBackend.get(i).get("isanonymous").contains("1")){
                        username = commentListFromBackend.get(i).get("username") + " (Anonymous to Others)";
                    }


                    CommentData commentCard = new CommentData(comment, 0, upVotes, numberOfReplies, subComments, username);
                    topLevelList.add(commentCard);
                }

                commentStream.getAdapter().notifyDataSetChanged();
            }
        }, NavDrawerActivity.this, sessionid).execute();



    }

    public ArrayList<HashMap<String, String>> getCommentListFromBackend() {
        return commentListFromBackend;
    }
}
