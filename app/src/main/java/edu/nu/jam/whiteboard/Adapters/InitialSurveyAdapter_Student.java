package edu.nu.jam.whiteboard.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.whiteboard.Data.InitialSurveyData;
import edu.nu.jam.whiteboard.Interfaces.IInitialSurveyAdapterOperations;
import edu.nu.jam.whiteboard.R;

public class InitialSurveyAdapter_Student extends RecyclerView.Adapter<InitialSurveyAdapter_Student.ViewHolder>
{
	private IInitialSurveyAdapterOperations initialSurveyOperationsContext;

	public InitialSurveyAdapter_Student(Context context)
	{
		if (!(context instanceof IInitialSurveyAdapterOperations))
			throw new ClassCastException("IInitialSurveyAdapterOperations cast exception.");

		initialSurveyOperationsContext = (IInitialSurveyAdapterOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView initialSurveyQuestionTextView;
		private SeekBar initialSurveyResponseSeekBar;
		private TextView initialSurveyResponsePercentageTextView;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			initialSurveyQuestionTextView = itemView.findViewById(R.id.surveyQuestionTextView);
			initialSurveyResponseSeekBar = itemView.findViewById(R.id.initialSurveyResponseSeekBar);
			initialSurveyResponsePercentageTextView = itemView.findViewById(R.id.initialSurveyResponsePercentageTextView);
		}

		private void registerHandlers()
		{
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{

				}
			});



		}

		private void displayInitialSurveyData(InitialSurveyData initialSurveyData)
		{
			initialSurveyQuestionTextView.setText(initialSurveyData.getQuestion());
			initialSurveyResponsePercentageTextView.setText(Double.toString(initialSurveyData.getResponsePercentage()) + " %");
		}
	}

	@NonNull
	@Override
	public InitialSurveyAdapter_Student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_initial_survey_student, parentViewGroup, false);
		return new InitialSurveyAdapter_Student.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final @NonNull InitialSurveyAdapter_Student.ViewHolder holder, final int position)
	{
		List<InitialSurveyData> initialSurveyDataList = initialSurveyOperationsContext.onGetInitialSurveyQuestions();
		InitialSurveyData initialSurveyData = initialSurveyDataList.get(position);
		holder.displayInitialSurveyData(initialSurveyData);
		holder.itemView.setTag(position);
		holder.initialSurveyResponseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				holder.initialSurveyResponsePercentageTextView.setText(Integer.toString(progress) + ".0 %");
				initialSurveyOperationsContext.setStudentResponsePercentage(position, progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
				// Stub...
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				// Stub...
			}
		});
	}

	@Override
	public int getItemCount()
	{
		if (initialSurveyOperationsContext.onGetInitialSurveyQuestions() != null)
			return initialSurveyOperationsContext.onGetInitialSurveyQuestions().size();
		return -1;
	}
}
