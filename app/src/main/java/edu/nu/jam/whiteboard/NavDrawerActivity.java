package edu.nu.jam.whiteboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.nu.jam.whiteboard.Adapters.CommentBoardAdapter;
import edu.nu.jam.whiteboard.Data.CommentData;
import edu.nu.jam.whiteboard.Interfaces.ICommentBoardOperations;
import edu.nu.jam.whiteboard.Requestsmodule.AddCommentHelper;
import edu.nu.jam.whiteboard.Requestsmodule.AsyncResponder;
import edu.nu.jam.whiteboard.Requestsmodule.CommentListHelper;
import edu.nu.jam.whiteboard.Requestsmodule.DatabaseHelper;
import edu.nu.jam.whiteboard.Requestsmodule.InitialSurveyResultsUpdateHelper;
import edu.nu.jam.whiteboard.Requestsmodule.ProgressiveSurveyAnswerHelper;
import edu.nu.jam.whiteboard.Requestsmodule.ReplyToCommentHelper;
import edu.nu.jam.whiteboard.Requestsmodule.SessionListHelper;

import static edu.nu.jam.whiteboard.InitialSurveyActivity.EXTRA_CONFIDENCE_1;
import static edu.nu.jam.whiteboard.InitialSurveyActivity.EXTRA_CONFIDENCE_2;
import static edu.nu.jam.whiteboard.InitialSurveyActivity.EXTRA_CONFIDENCE_3;
import static edu.nu.jam.whiteboard.InitialSurveyActivity.EXTRA_CONFIDENCE_4;
import static edu.nu.jam.whiteboard.MainActivity.EXTRA_USER_TYPE;
import static edu.nu.jam.whiteboard.NewCommentActivity.EXTRA_IS_ANONYMOUS;
import static edu.nu.jam.whiteboard.NewCommentActivity.EXTRA_NEW_COMMENT;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ICommentBoardOperations
{
    //Intent extras
    public static final String EXTRA_PARENT_COMMENT_ID = "repliesList";
    public static final String EXTRA_PARENT_COMMENT = "parentComment";
    public static final String EXTRA_WEEK_NUMBER = "weekNumber";

    private FloatingActionButton newCommentFAB;
    private FloatingActionButton newCommentReplyFAB;
    private TextView repliesCount, upVotesCount, commentTextView;
    private CommentData commentCard;
    private SwipeRefreshLayout refresh;
    private List<CommentData> subComments = new ArrayList<>();
    List<CommentData> topLevelList = new ArrayList<>();

    private NavigationView navigationView;

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
        navigationView = findViewById(R.id.nav_drawer);
        //next three lines set the userName on the navDrawer
        View headerView = navigationView.getHeaderView(0);
        //TextView userName = headerView.findViewById(R.id.nav_userNameTextView);
        //userName.setText(dbHelper.GetUsernameFromSharedPreferences(this));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        bindControls();
        setUsernameInNavDrawer(headerView);
        setNavDrawer();

        commentStream.setHasFixedSize(false);
        commentStream.setLayoutManager(new LinearLayoutManager(this));
        commentStream.setAdapter(adapter);
        commentStream.getAdapter().notifyDataSetChanged();
        refresh = findViewById(R.id.swipeRefreshComments);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                getCommentList();
            }
        });

    }
    private void setUsernameInNavDrawer(View navDrawer)
    {
        TextView userName = navDrawer.findViewById(R.id.nav_userNameTextView);
        userName.setText(dbHelper.GetUsernameFromSharedPreferences(this));
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
                break;
            case (R.id.action_logout):
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                editor.clear();
                editor.commit();
                Intent logoutIntent = new Intent(this, MainActivity.class);
                startActivity(logoutIntent);
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
                Intent initialSurveyIntent = new Intent(this, InitialSurveyActivity.class);
                startActivityForResult(initialSurveyIntent, 3);
                break;
            case (R.id.nav_progressiveSurvey):
                Intent progressiveSurveyIntent = new Intent(this, ProgressiveSurveyActivity.class);
                progressiveSurveyIntent.putExtra(EXTRA_WEEK_NUMBER, 2);
                startActivityForResult(progressiveSurveyIntent,4);
                break;
            case (R.id.nav_logout):
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
                Intent surveySelectionIntent = new Intent(this, SurveySelectionActivity.class);
                startActivity(surveySelectionIntent);
                break;
            case (R.id.nav_sessionSelection):
                Intent sessionSelectionIntent = new Intent(this, SessionSelectionActivity.class);
                startActivity(sessionSelectionIntent);
                break;
            case (R.id.nav_majorityConcern):
                //Bonus Item
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
                    if(comment.isEmpty()) {
                        break;
                    }
                    Boolean isAnonymous = data.getExtras().getBoolean(EXTRA_IS_ANONYMOUS);
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String username = dbHelper.GetUsernameFromSharedPreferences(NavDrawerActivity.this);


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
                    String comment = data.getStringExtra(EXTRA_NEW_COMMENT);
                    String parentid = data.getStringExtra(EXTRA_PARENT_COMMENT_ID);
                    String parentcomment = data.getStringExtra(EXTRA_PARENT_COMMENT);
                    if (comment.isEmpty()) {
                        break;
                    }
                    Boolean isAnonymous = data.getExtras().getBoolean(EXTRA_IS_ANONYMOUS);
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String username = dbHelper.GetUsernameFromSharedPreferences(NavDrawerActivity.this);


                    String userID = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
                    String sessionID = dbHelper.GetSessionIdFromSharedPreferences(NavDrawerActivity.this);
                    new ReplyToCommentHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {
                            getCommentList();
                        }
                    }, NavDrawerActivity.this, comment, isAnonymous, userID, parentid).execute();
                    Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
                    intent.putExtra(EXTRA_PARENT_COMMENT, parentcomment);
                    intent.putExtra(EXTRA_PARENT_COMMENT_ID, parentid);
                    startActivity(intent);
                }
                break;
            case 3:
                //Initial survey results push to backend
                if (resultCode == Activity.RESULT_OK)
                {
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String userid = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
                    Double ConfidenceOne = data.getDoubleExtra(EXTRA_CONFIDENCE_1, 0);
                    Double ConfidenceTwo = data.getDoubleExtra(EXTRA_CONFIDENCE_2, 0);
                    Double ConfidenceThree = data.getDoubleExtra(EXTRA_CONFIDENCE_3, 0);
                    Double ConfidenceFour = data.getDoubleExtra(EXTRA_CONFIDENCE_4, 0);

                    new InitialSurveyResultsUpdateHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {

                        }
                    }, NavDrawerActivity.this, userid, ConfidenceOne, ConfidenceTwo, ConfidenceThree, ConfidenceFour).execute();

                }
                break;
            case 4:

                if (resultCode == Activity.RESULT_OK)
                {
                    DatabaseHelper dbHelper = new DatabaseHelper();
                    String userid = dbHelper.GetUserIdFromSharedPreferences(NavDrawerActivity.this);
                    ArrayList<HashMap<String,String>> WeeklyELOList = new ArrayList<>();
                    int numberOfQuestions = data.getIntExtra("NumberOfQuestions", 0);
                    for(int i = 0; i < numberOfQuestions; i++) {
                        String questionid = data.getStringExtra("QuestionID_" + i);
                        String confidence = data.getStringExtra("Confidence_" + i);

                        HashMap<String, String> question = new HashMap<>();
                        question.put("id", questionid);
                        question.put("confidence", confidence);
                        WeeklyELOList.add(question);
                    }
                    String sessionid = dbHelper.GetSessionIdFromSharedPreferences(NavDrawerActivity.this);

                    new ProgressiveSurveyAnswerHelper(new AsyncResponder() {
                        @Override
                        public void processFinish(String output) {

                        }
                    }, NavDrawerActivity.this, sessionid, WeeklyELOList).execute();

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
    }

    @Override
    public void onTextViewClicked(int position)
    {
        if(topLevelList.get(position).getNumberOfReplies() == 0)
            return;
        Intent intent = new Intent(getApplicationContext(), ViewRepliesActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, topLevelList.get(position).getContent());
        intent.putExtra(EXTRA_PARENT_COMMENT_ID, topLevelList.get(position).getCommentid());
        startActivity(intent);
    }

    @Override
    public void onReplyClicked(int position)
    {
        Intent intent = new Intent(getApplicationContext(), ReplyToCommentActivity.class);
        intent.putExtra(EXTRA_PARENT_COMMENT, topLevelList.get(position).getContent());
        intent.putExtra(EXTRA_PARENT_COMMENT_ID, topLevelList.get(position).getCommentid());
        startActivityForResult(intent, 2);
    }

    @Override
    public void onUpVoteClicked(int position)
    {
        topLevelList.get(position).setUpvotes(topLevelList.get(position).getUpvotes() + 1);
        commentStream.getAdapter().notifyDataSetChanged();//should update the upvote count
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
                for (int i = 0; i < commentListFromBackend.size(); i++) {
                    String comment = commentListFromBackend.get(i).get("message");
                    String username = commentListFromBackend.get(i).get("username");
                    //int upVotes = Integer.parseInt(commentListFromBackend.get(i).get("upvotes"));
                    int upVotes = 0;
                    int numberOfReplies = Integer.parseInt(commentListFromBackend.get(i).get("numofreplies"));
                    if(commentListFromBackend.get(i).get("isanonymous").contains("1")) {
                        username = "Anonymous";
                    }
                    if(commentListFromBackend.get(i).get("username") == dbHelper.GetUsernameFromSharedPreferences(NavDrawerActivity.this)
                    && commentListFromBackend.get(i).get("isanonymous").contains("1")){
                        username = commentListFromBackend.get(i).get("username") + " (Anonymous to Others)";
                    }
                    String commentid = commentListFromBackend.get(i).get("commentid");
                    String parentid = commentListFromBackend.get(i).get("parentid");


                    CommentData commentCard = new CommentData(comment, 0, upVotes, numberOfReplies, subComments, username, commentid, parentid);
                    topLevelList.add(commentCard);
                }

                commentStream.getAdapter().notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        }, NavDrawerActivity.this, sessionid).execute();


    }

    public ArrayList<HashMap<String, String>> getCommentListFromBackend() {
        return commentListFromBackend;
    }
}
