package edu.nu.jam.capstone;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;
import java.util.List;
import edu.nu.jam.capstone.Adapters.SurveySelectionAdapter;
import edu.nu.jam.capstone.Interfaces.ISurveySelectionOperations;

public class SurveySelectionActivity extends AppCompatActivity implements ISurveySelectionOperations
{
	private List<String> surveyLabelList;
	private RecyclerView surveySelectionRecyclerView;
	private Button goToSurveyButton;
	private int currentIndex;
	private CardView archiveView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		surveyLabelList = new LinkedList<>();
		populateRecyclerViewWeeklyLabels();
		setContentView(R.layout.activity_survey_selection);
		bindDataAndControls();
		configureRecyclerView();
		registerHandlers();
	}
	private void populateRecyclerViewWeeklyLabels()
	{
		// TODO:  figure out which week its in via the API or token, attach it to the week value here.
		int weekNumber = 2;
		if (weekNumber == 1)
		{
			surveyLabelList.add("Initial Survey");
			surveyLabelList.add("Week 1 Survey");
		}
		if (weekNumber == 2)
		{
			surveyLabelList.add("Initial Survey");
			surveyLabelList.add("Week 1 Survey");
			surveyLabelList.add("Week 2 Survey");
		}
		if (weekNumber == 3)
		{
			surveyLabelList.add("Initial Survey");
			surveyLabelList.add("Week 1 Survey");
			surveyLabelList.add("Week 2 Survey");
			surveyLabelList.add("Week 3 Survey");
		}
		if (weekNumber == 4)
		{
			surveyLabelList.add("Initial Survey");
			surveyLabelList.add("Week 1 Survey");
			surveyLabelList.add("Week 2 Survey");
			surveyLabelList.add("Week 3 Survey");
			surveyLabelList.add("Week 4 Survey");
		}
	}
	private void bindDataAndControls()
	{
		surveySelectionRecyclerView = findViewById(R.id.surveySelectionRecyclerView);
		goToSurveyButton = findViewById(R.id.goToSurveyBtn);
	}
	private void configureRecyclerView()
	{
		surveySelectionRecyclerView.setHasFixedSize(true);
		surveySelectionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		surveySelectionRecyclerView.setAdapter(new SurveySelectionAdapter(this));
	}
	private void registerHandlers()
	{
		goToSurveyButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				switch (currentIndex)
				{
					// Initial Survey selected:
					case 0:
						startSurveyActivity(0);
						break;
					// Progressive Survey (Week 1) selected:
					case 1:
						startSurveyActivity(1);
						break;
					// Progressive Survey (Week 2) selected:
					case 2:
						startSurveyActivity(2);
						break;
					// Progressive Survey (Week 3) selected:
					case 3:
						startSurveyActivity(3);
						break;
					// Progressive Survey (Week 4) selected:
					case 4:
						startSurveyActivity(4);
						break;
				}
			}
		});
	}
	@Override
	public void onItemSelected(int position, View view)
	{
		CardView activeView;
		currentIndex = position;
		if ((position >= 0) && (position < surveyLabelList.size()))
		{
			activeView = (CardView) view;
			if (archiveView == null && activeView != archiveView)
			{
				activeView.setCardBackgroundColor(Color.argb(100, 5, 64, 242));
				archiveView = activeView;
			}
			else if (archiveView != null && activeView != archiveView)
			{
				activeView.setCardBackgroundColor(Color.argb(100, 5, 64, 242));
				archiveView.setCardBackgroundColor(Color.WHITE);
				archiveView = activeView;
			}
			else if (archiveView == null)
			{
				archiveView = activeView;
			}
		}
	}

	@Override
	public List<String> onGetSurveys()
	{
		return surveyLabelList;
	}
	private void startSurveyActivity(int selection)
	{
		Intent intent = null;
		if (selection == 0)
		{
			intent = new Intent(this, InitialSurveyActivity.class);
		}
		else if (selection == 1)
		{
			intent = new Intent(this, ProgressiveSurveyActivity.class);
			intent.putExtra("surveyWeek", 1);
		}
		else if (selection == 2)
		{
			intent = new Intent(this, ProgressiveSurveyActivity.class);
			intent.putExtra("surveyWeek", 2);
		}
		else if (selection == 3)
		{
			intent = new Intent(this, ProgressiveSurveyActivity.class);
			intent.putExtra("surveyWeek", 3);
		}
		else if (selection == 4)
		{
			intent = new Intent(this, ProgressiveSurveyActivity.class);
			intent.putExtra("surveyWeek", 4);
		}
		startActivity(intent);
	}
}