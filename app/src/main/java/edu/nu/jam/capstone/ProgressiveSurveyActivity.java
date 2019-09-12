package edu.nu.jam.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

import edu.nu.jam.capstone.Adapters.ProgressiveSurveyAdapter_Professor;
import edu.nu.jam.capstone.Adapters.ProgressiveSurveyAdapter_Student;
import edu.nu.jam.capstone.Data.ProgressiveSurveyData;
import edu.nu.jam.capstone.Interfaces.IProgressiveSurveyAdapterOperations;
import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;

import static edu.nu.jam.capstone.MainActivity.EXTRA_USER_TYPE;

public class ProgressiveSurveyActivity extends AppCompatActivity implements IProgressiveSurveyAdapterOperations
{
    //Intent extras
    public static final String EXTRA_PROGRESSIVE_CON_1 = "progressiveConfidence_1";
    public static final String EXTRA_PROGRESSIVE_CON_2 = "progressiveConfidence_2";
    public static final String EXTRA_PROGRESSIVE_CON_3 = "progressiveConfidence_3";
    public static final String EXTRA_PROGRESSIVE_CON_4 = "progressiveConfidence_4";

    private List<ProgressiveSurveyData> progressiveSurveyDataList = new LinkedList<>();
    private RecyclerView recyclerView;
    private int currentIndex;
    private FloatingActionButton submitSurveyFAB;

    private DatabaseHelper databaseHelper = new DatabaseHelper();

    private final String responseDescriptionTag = "Class Response";
    private enum UserType {PROFESSOR, STUDENT};
    UserType userType;

    // TODO:  identify whether professor or student type and assign to Enum value.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressive_survey);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO:  delete this dummy data after we're connected to the backend:
        progressiveSurveyDataList.add( new ProgressiveSurveyData(
                "Explain issues related to variable binding, scope, and parameter passing.",
                0,
                0));
        progressiveSurveyDataList.add( new ProgressiveSurveyData(
                "Describe major programming language paradigms (procedural, object-oriented, functional, logic, concurrent).",
                0,
                0));
        progressiveSurveyDataList.add( new ProgressiveSurveyData(
                "Discuss factors in programming language theory such as context free grammars and parse trees.",
                0,
                0));
        progressiveSurveyDataList.add( new ProgressiveSurveyData(
                "Demonstrate working knowledge of compiler design in modern programming languages.",
                0,
                0));

        bindControls();
        // check to see if the Intent's passed extra is professor or student:
        if (databaseHelper.GetUserTypeFromSharedPreferences(this).toLowerCase().startsWith("p"))
        {
            // professor-type logic:
            userType = UserType.PROFESSOR;
            configureRecyclerView();
        }
        else
        {
            // student-type logic:
            userType = UserType.STUDENT;
            configureRecyclerView();
        }

        submitSurvey();

    }

    private void submitSurvey()
    {
        final Intent intentReturn = new Intent();
        submitSurveyFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intentReturn.putExtra(EXTRA_PROGRESSIVE_CON_1, progressiveSurveyDataList.get(0).getStudentConfidencePercentage());
                intentReturn.putExtra(EXTRA_PROGRESSIVE_CON_2, progressiveSurveyDataList.get(1).getStudentConfidencePercentage());
                intentReturn.putExtra(EXTRA_PROGRESSIVE_CON_3, progressiveSurveyDataList.get(2).getStudentConfidencePercentage());
                intentReturn.putExtra(EXTRA_PROGRESSIVE_CON_4, progressiveSurveyDataList.get(3).getStudentConfidencePercentage());
                setResult(Activity.RESULT_OK, intentReturn);
                finish();
            }
        });
    }

    private void bindControls()
    {
        recyclerView = findViewById(R.id.progressiveSurveyRecyclerView);
        submitSurveyFAB = findViewById(R.id.submitSurveyFAB);
    }

    private void configureRecyclerView()
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        switch(userType) {
            case PROFESSOR:
                recyclerView.setAdapter(new ProgressiveSurveyAdapter_Professor(this));
                break;
            case STUDENT:
                recyclerView.setAdapter(new ProgressiveSurveyAdapter_Student(this));
                break;
        }
    }

    @Override
    public List<ProgressiveSurveyData> onGetLearningObjectives()
    {
        return progressiveSurveyDataList;
    }

    @Override
    public void onItemSelected(int position, View view)
    {
        // previously card coloring...undefined in this class.
    }

    private void resetEloValueInAdapter()
    {
        if (userType == UserType.PROFESSOR)
            ProgressiveSurveyAdapter_Professor.eloNumber = 1;
        else
            ProgressiveSurveyAdapter_Student.eloNumber = 1;
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
            resetEloValueInAdapter();
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
