package edu.nu.jam.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import edu.nu.jam.capstone.Adapters.InitialSurveyAdapter_Professor;
import edu.nu.jam.capstone.Adapters.InitialSurveyAdapter_Student;
import edu.nu.jam.capstone.Adapters.ProgressiveSurveyAdapter_Professor;
import edu.nu.jam.capstone.Adapters.SessionSelectionAdapter;
import edu.nu.jam.capstone.Adapters.SurveySelectionAdapter;
import edu.nu.jam.capstone.Data.InitialSurveyData;
import edu.nu.jam.capstone.Data.ProgressiveSurveyData;
import edu.nu.jam.capstone.Data.SessionData;
import edu.nu.jam.capstone.Interfaces.IInitialSurveyAdapterOperations;
import edu.nu.jam.capstone.Interfaces.IProgressiveSurveyAdapterOperations;
import edu.nu.jam.capstone.Interfaces.ISessionSelectionOperations;
import edu.nu.jam.capstone.Interfaces.ISurveySelectionOperations;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_recovery);

		bindControlsAndData();
		configureRecyclerView();
	}

	private void bindControlsAndData()
	{
	}

	private void configureRecyclerView()
	{

	}

}

////TODO: (SURVEY SELECTION)
//
//public class MainActivity extends AppCompatActivity implements ISurveySelectionOperations
//{
//
//	private List<String> surveyDataList;
//	private RecyclerView surveyDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_survey_selection);
//
//		surveyDataList = new LinkedList<>();
//
//		surveyDataList.add("Initial Course Survey");
//		surveyDataList.add("Week 1 Progressive Survey");
//		surveyDataList.add("Week 2 Progressive Survey");
//		surveyDataList.add("Week 3 Progressive Survey");
//		surveyDataList.add("Week 4 Progressive Survey");
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		surveyDataRecyclerView = findViewById(R.id.surveySelectionRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		surveyDataRecyclerView.setHasFixedSize(true);
//		surveyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		surveyDataRecyclerView.setAdapter(new SurveySelectionAdapter(this));
//	}
//
//	@Override
//	public List<String> onGetSurveys()
//	{
//		if (surveyDataList != null)
//			return surveyDataList;
//		return null;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}

//// TODO: (SESSION SELECTION)
//
//public class MainActivity extends AppCompatActivity implements ISessionSelectionOperations
//{
//
//	private List<SessionData> sessionDataList;
//	private RecyclerView sessionDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_session_selection);
//
//		sessionDataList = new LinkedList<>();
//
//		sessionDataList.add(new SessionData("CSC-445", "Wireless Application Development", "June 2019"));
//		sessionDataList.add(new SessionData("CSC-400", "OS Theory and Design", "January 2019"));
//		sessionDataList.add(new SessionData("CSC-300", "Object Oriented Design", "May 2018"));
//		sessionDataList.add(new SessionData("CSC-208", "Calculus for Computer Science I", "November 2017"));
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		sessionDataRecyclerView = findViewById(R.id.sessionSelectionRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		sessionDataRecyclerView.setHasFixedSize(true);
//		sessionDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		sessionDataRecyclerView.setAdapter(new SessionSelectionAdapter(this));
//	}
//
//	@Override
//	public List<SessionData> onGetSessions()
//	{
//		return sessionDataList;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}

//// TODO: (PROGRESSIVE SURVEY - PROFESSOR)
//
//public class MainActivity extends AppCompatActivity implements IProgressiveSurveyAdapterOperations
//{
//
//	private List<ProgressiveSurveyData> progressiveSurveyDataList;
//	private RecyclerView progressiveSurveyDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_progressive_survey);
//
//		progressiveSurveyDataList = new LinkedList<>();
//
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 50.0, -1));
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 40.0, -1));
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 99.0, -1));
//
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		progressiveSurveyDataRecyclerView = findViewById(R.id.progressiveSurveyRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		progressiveSurveyDataRecyclerView.setHasFixedSize(true);
//		progressiveSurveyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		progressiveSurveyDataRecyclerView.setAdapter(new ProgressiveSurveyAdapter_Professor(this));
//	}
//
//	@Override
//	public List<ProgressiveSurveyData> onGetLearningObjectives()
//	{
//		return progressiveSurveyDataList;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}

//// TODO: (PROGRESSIVE SURVEY - PROFESSOR)
//
//public class MainActivity extends AppCompatActivity implements IProgressiveSurveyAdapterOperations
//{
//
//	private List<ProgressiveSurveyData> progressiveSurveyDataList;
//	private RecyclerView progressiveSurveyDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_progressive_survey);
//
//		progressiveSurveyDataList = new LinkedList<>();
//
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 50.0, -1));
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 40.0, -1));
//		progressiveSurveyDataList.add(new ProgressiveSurveyData("Learning Obj 1", 99.0, -1));
//
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		progressiveSurveyDataRecyclerView = findViewById(R.id.progressiveSurveyRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		progressiveSurveyDataRecyclerView.setHasFixedSize(true);
//		progressiveSurveyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		progressiveSurveyDataRecyclerView.setAdapter(new ProgressiveSurveyAdapter_Professor(this));
//	}
//
//	@Override
//	public List<ProgressiveSurveyData> onGetLearningObjectives()
//	{
//		return progressiveSurveyDataList;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}


//// TODO: (INITIAL SURVEY - STUDENT)
//
//public class MainActivity extends AppCompatActivity implements IInitialSurveyAdapterOperations
//{
//
//	private List<InitialSurveyData> initialSurveyDataList;
//	private RecyclerView initialSurveyDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_initial_survey);
//
//		initialSurveyDataList = new LinkedList<>();
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"1)  What learning style do you normally have while in class?",
//						"ACTIVE",
//						"\"I learn by asking questions and interacting.\"",
//						"PASSIVE",
//						"\"I learn by listening to others and observing.\"",
//						"MIXED",
//						"\"I learn by a combination of both of the above participation styles.\"",
//						40.5,
//						10.2,
//						49.3
//				));
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"2)  How much experience do you have in the industry for which you intent to work?",
//						"NONE",
//						"\"I have zero experience in the industry that I want to work in.\"",
//						"SOME",
//						"\"I have some experience in my industry, but need to increase my knowledge before returning.\"",
//						"A LOT",
//						"\"I have a large degree of industry experience, but wish to further my professional status.\"",
//						66,
//						30,
//						4
//				));
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"3)  What is your age (range)?",
//						"18 - 25 YEARS OLD",
//						"",
//						"26 - 35 YEARS OLD",
//						"",
//						"36+ YEARS OLD",
//						"",
//						45.5,
//						35,
//						19.5
//				));
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		initialSurveyDataRecyclerView = findViewById(R.id.initialSurveyRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		initialSurveyDataRecyclerView.setHasFixedSize(true);
//		initialSurveyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		initialSurveyDataRecyclerView.setAdapter(new InitialSurveyAdapter_Student(this));
//	}
//
//	@Override
//	public List<InitialSurveyData> onGetInitialSurveyQuestions()
//	{
//		return initialSurveyDataList;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}

//// TODO: (INITIAL SURVEY - PROFESSOR)
//
//public class MainActivity extends AppCompatActivity implements IInitialSurveyAdapterOperations
//{
//
//	private List<InitialSurveyData> initialSurveyDataList;
//	private RecyclerView initialSurveyDataRecyclerView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_initial_survey);
//
//		initialSurveyDataList = new LinkedList<>();
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"1)  What learning style do you normally have while in class?",
//						"ACTIVE",
//						"\"I learn by asking questions and interacting.\"",
//						"PASSIVE",
//						"\"I learn by listening to others and observing.\"",
//						"MIXED",
//						"\"I learn by a combination of both of the above participation styles.\"",
//						40.5,
//						10.2,
//						49.3
//				));
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"2)  How much experience do you have in the industry for which you intent to work?",
//						"NONE",
//						"\"I have zero experience in the industry that I want to work in.\"",
//						"SOME",
//						"\"I have some experience in my industry, but need to increase my knowledge before returning.\"",
//						"A LOT",
//						"\"I have a large degree of industry experience, but wish to further my professional status.\"",
//						66,
//						30,
//						4
//				));
//		initialSurveyDataList.add(
//				new InitialSurveyData(
//						"3)  What is your age (range)?",
//						"18 - 25 YEARS OLD",
//						"",
//						"26 - 35 YEARS OLD",
//						"",
//						"36+ YEARS OLD",
//						"",
//						45.5,
//						35,
//						19.5
//				));
//
//		bindControlsAndData();
//		configureRecyclerView();
//	}
//
//	private void bindControlsAndData()
//	{
//		initialSurveyDataRecyclerView = findViewById(R.id.initialSurveyRecyclerView);
//	}
//
//	private void configureRecyclerView()
//	{
//		initialSurveyDataRecyclerView.setHasFixedSize(true);
//		initialSurveyDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//		initialSurveyDataRecyclerView.setAdapter(new InitialSurveyAdapter_Professor(this));
//	}
//
//	@Override
//	public List<InitialSurveyData> onGetInitialSurveyQuestions()
//	{
//		return initialSurveyDataList;
//	}
//
//	@Override
//	public void onItemSelected(int position, View view)
//	{
//
//	}
//}

//// TODO: (EVAN'S MAIN)
//
//public class MainActivity extends AppCompatActivity
//{
//	static final String EXTRA_MESSAGE = "extraString";
//	private Button loginButton;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		//setContentView(R.layout.activity_login);
//		setContentView(R.layout.activity_login);
//		bindControlsAndData();
//	}
//
//	private void bindControlsAndData() {
//		loginButton = findViewById(R.id.loginBtn);
//		loginButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				startNewActivity();
//			}
//		});
//	}
//
//	private void startNewActivity()
//	{
//		Intent intent = new Intent(this, NavDrawerActivity.class);
//		String message = "Toast test";
//		intent.putExtra(EXTRA_MESSAGE, message);
//		startActivity(intent);
//	}
//}
