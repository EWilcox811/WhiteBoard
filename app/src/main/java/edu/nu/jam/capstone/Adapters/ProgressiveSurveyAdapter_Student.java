package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Data.ProgressiveSurveyData;
import edu.nu.jam.capstone.Interfaces.IProgressiveSurveyAdapterOperations;
import edu.nu.jam.capstone.R;

public class ProgressiveSurveyAdapter_Student extends RecyclerView.Adapter<ProgressiveSurveyAdapter_Student.ViewHolder>
{
	private IProgressiveSurveyAdapterOperations progressiveSurveyOperationsContext;
	public static int eloNumber = 1;  // static var used to enumerate the list of ELOs.
	private SeekBar progressiveSurveyResponseSeekBar;

	public ProgressiveSurveyAdapter_Student(Context context)
	{
		if (!(context instanceof IProgressiveSurveyAdapterOperations))
			throw new ClassCastException("IProgressiveSurveyAdapterOperations cast exception.");

		progressiveSurveyOperationsContext = (IProgressiveSurveyAdapterOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView learningObjectiveNumber;
		private TextView progressiveSurveyQuestionTextView;

		private TextView progressiveSurveyResponsePercentageTextView;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			learningObjectiveNumber = itemView.findViewById(R.id.eloNumber);
			progressiveSurveyQuestionTextView = itemView.findViewById(R.id.progressiveSurveyQuestionTextView);
			progressiveSurveyResponseSeekBar = itemView.findViewById(R.id.progressiveSurveyResponseSeekBar);
			progressiveSurveyResponsePercentageTextView = itemView.findViewById(R.id.progressiveSurveyResponsePercentageTextView);
		}

		private void registerHandlers()
		{
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
//					int index = (int) view.getTag();
//					View fetchedViewOrigin = (View) phoneNumberTextView.getParent();
//					View fetchedViewIntermediate = (View) fetchedViewOrigin.getParent();
//					View fetchedViewTarget = (View) fetchedViewIntermediate.getParent();
//					rolodexOperationsContext.onItemSelected(index, fetchedViewTarget);
				}
			});

//			progressiveSurveyResponseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//			{
//				@Override
//				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//				{
//
//				}
//
//				@Override
//				public void onStartTrackingTouch(SeekBar seekBar)
//				{
//					// Stub...
//				}
//
//				@Override
//				public void onStopTrackingTouch(SeekBar seekBar)
//				{
//					// Stub...
//				}
//			});
		}

		private void displayProgressiveSurveyData(ProgressiveSurveyData progressiveSurveyData)
		{
			learningObjectiveNumber.setText(Integer.toString(eloNumber) + ":");
			progressiveSurveyQuestionTextView.setText(progressiveSurveyData.getLearningObjective());
			progressiveSurveyResponsePercentageTextView.setText(Double.toString(progressiveSurveyData.getStudentConfidencePercentage()));
			eloNumber++;
		}
	}

	@NonNull
	@Override
	public ProgressiveSurveyAdapter_Student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_progressive_survey_student, parentViewGroup, false);
		return new ProgressiveSurveyAdapter_Student.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final @NonNull ProgressiveSurveyAdapter_Student.ViewHolder holder, final int position)
	{
		List<ProgressiveSurveyData> progressiveSurveyDataList = progressiveSurveyOperationsContext.onGetLearningObjectives();
		ProgressiveSurveyData progressiveSurveyData = progressiveSurveyDataList.get(position);
		holder.displayProgressiveSurveyData(progressiveSurveyData);
		holder.itemView.setTag(position);
		holder.progressiveSurveyResponseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
			{
				holder.progressiveSurveyResponsePercentageTextView.setText(Integer.toString(progress) + ".0 %");
				progressiveSurveyOperationsContext.setStudentConfidence(position, (double) progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
	}

	@Override
	public int getItemCount()
	{
		if (progressiveSurveyOperationsContext.onGetLearningObjectives() != null)
			return progressiveSurveyOperationsContext.onGetLearningObjectives().size();
		return -1;
	}

	public String getSeekBarAmount() {
		String percentage = String.valueOf(progressiveSurveyResponseSeekBar.getProgress());

		return percentage;
	}
}

