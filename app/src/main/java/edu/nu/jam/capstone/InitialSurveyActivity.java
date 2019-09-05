package edu.nu.jam.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import edu.nu.jam.capstone.Adapters.InitialSurveyAdapter_Professor;
import edu.nu.jam.capstone.Adapters.InitialSurveyAdapter_Student;
import edu.nu.jam.capstone.Data.InitialSurveyData;
import edu.nu.jam.capstone.Interfaces.IInitialSurveyAdapterOperations;

public class InitialSurveyActivity extends AppCompatActivity implements IInitialSurveyAdapterOperations
{
	private List<InitialSurveyData> initialSurveyDataList = new LinkedList<>();
	private RecyclerView recyclerView;
	private int currentIndex;

	private final String responseDescriptionTag = "Class Response";
	private enum UserType {PROFESSOR, STUDENT};
	UserType userType;

	// TODO:  identify whether professor or student type and assign to Enum value.

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_survey);

		// TODO:  delete this dummy data after we're connected to the backend:
		initialSurveyDataList.add( new InitialSurveyData(
				"\"When I learn I read books, articles, and handouts\":",
				responseDescriptionTag,
				0));
		initialSurveyDataList.add( new InitialSurveyData(
				"\"When I learn I use examples and applications\":",
				responseDescriptionTag,
				0));
		initialSurveyDataList.add( new InitialSurveyData(
				"\"When I learn I see patterns in things\":",
				responseDescriptionTag,
				0));
		initialSurveyDataList.add( new InitialSurveyData(
				"\"When I learn I like to talk things through\":",
				responseDescriptionTag,
				0));

		bindControls();
		Intent intent = getIntent();
		// check to see if the Intent's passed extra is professor or student:
		if (intent.getStringExtra("userType").toLowerCase().startsWith("p"))
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
	}

	private void bindControls()
	{
		recyclerView = findViewById(R.id.initialSurveyRecyclerView);
	}

	private void configureRecyclerView()
	{
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		switch(userType) {
			case PROFESSOR:
				recyclerView.setAdapter(new InitialSurveyAdapter_Professor(this));
				break;
			case STUDENT:
				recyclerView.setAdapter(new InitialSurveyAdapter_Student(this));
				break;
		}
	}

	@Override
	public List<InitialSurveyData> onGetInitialSurveyQuestions()
	{
		return initialSurveyDataList;
	}

	@Override
	public void onItemSelected(int position, View view)
	{
		// previously card coloring...undefined in this class.
	}
}