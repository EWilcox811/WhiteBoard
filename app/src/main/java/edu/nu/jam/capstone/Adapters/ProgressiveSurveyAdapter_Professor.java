package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Data.ProgressiveSurveyData;
import edu.nu.jam.capstone.Interfaces.IProgressiveSurveyAdapterOperations;
import edu.nu.jam.capstone.R;

public class ProgressiveSurveyAdapter_Professor extends RecyclerView.Adapter<ProgressiveSurveyAdapter_Professor.ViewHolder>
{
	private IProgressiveSurveyAdapterOperations progressiveSurveyOperationsContext;
	static int eloNumber = 1;

	public ProgressiveSurveyAdapter_Professor(Context context)
	{
		if (!(context instanceof IProgressiveSurveyAdapterOperations))
			throw new ClassCastException("IProgressiveSurveyAdapterOperations cast exception.");

		progressiveSurveyOperationsContext = (IProgressiveSurveyAdapterOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView progressiveQuestionTextView;
		private TextView classConfidenceLabelTextView;
		private TextView classConfidencePercentageTextView;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			progressiveQuestionTextView = itemView.findViewById(R.id.eloLabelTextView);
			classConfidenceLabelTextView = itemView.findViewById(R.id.classConfidenceLabelTextView);
			classConfidencePercentageTextView = itemView.findViewById(R.id.classConfidencePercentageTextView);
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
		}

		private void displayProgressiveSurveyData(ProgressiveSurveyData progressiveSurveyData)
		{

			progressiveQuestionTextView.setText("ELO #" + Integer.toString(eloNumber) + ":   " + progressiveSurveyData.getLearningObjective());
			classConfidenceLabelTextView.setText("Class Confidence:");
			classConfidencePercentageTextView.setText(Double.toString(progressiveSurveyData.getStudentConfidencePercentage()));
			eloNumber++;
		}
	}

	@NonNull
	@Override
	public ProgressiveSurveyAdapter_Professor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_progressive_survey_professor, parentViewGroup, false);
		return new ProgressiveSurveyAdapter_Professor.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ProgressiveSurveyAdapter_Professor.ViewHolder holder, int position)
	{
		List<ProgressiveSurveyData> progressiveSurveyDataList = progressiveSurveyOperationsContext.onGetLearningObjectives();
		ProgressiveSurveyData progressiveSurveyData = progressiveSurveyDataList.get(position);
		holder.displayProgressiveSurveyData(progressiveSurveyData);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (progressiveSurveyOperationsContext.onGetLearningObjectives() != null)
			return progressiveSurveyOperationsContext.onGetLearningObjectives().size();
		return -1;
	}
}

