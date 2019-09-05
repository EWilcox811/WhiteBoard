package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Interfaces.ISurveySelectionOperations;
import edu.nu.jam.capstone.R;

public class SurveySelectionAdapter extends RecyclerView.Adapter<SurveySelectionAdapter.ViewHolder>
{
	private ISurveySelectionOperations surveySelectionOperationsContext;

	public SurveySelectionAdapter(Context context)
	{
		if (!(context instanceof ISurveySelectionOperations))
			throw new ClassCastException("ISurveySelectionOperations cast exception.");

		surveySelectionOperationsContext = (ISurveySelectionOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView surveyItemTextView;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			surveyItemTextView = itemView.findViewById(R.id.surveyItemTextView);
		}

		private void registerHandlers()
		{
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					int index = (int) view.getTag();
					View fetchedViewOrigin = (View) surveyItemTextView.getParent();
					View fetchedViewTarget = (View) fetchedViewOrigin.getParent();
					surveySelectionOperationsContext.onItemSelected(index, fetchedViewTarget);
				}
			});
		}

		private void displaySurveyData(String surveyData)
		{
			surveyItemTextView.setText(surveyData);
		}
	}

	@NonNull
	@Override
	public SurveySelectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_survey_selection, parentViewGroup, false);
		return new SurveySelectionAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull SurveySelectionAdapter.ViewHolder holder, int position)
	{
		List<String> surveyDataList = surveySelectionOperationsContext.onGetSurveys();
		String surveyData = surveyDataList.get(position);
		holder.displaySurveyData(surveyData);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (surveySelectionOperationsContext.onGetSurveys() != null)
			return surveySelectionOperationsContext.onGetSurveys().size();
		return -1;
	}
}
