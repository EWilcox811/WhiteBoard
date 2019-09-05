package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Data.InitialSurveyData;
import edu.nu.jam.capstone.Interfaces.IInitialSurveyAdapterOperations;
import edu.nu.jam.capstone.R;

public class InitialSurveyAdapter_Professor extends RecyclerView.Adapter<InitialSurveyAdapter_Professor.ViewHolder>
{
	private IInitialSurveyAdapterOperations initialSurveyOperationsContext;

	public InitialSurveyAdapter_Professor(Context context)
	{
		if (!(context instanceof IInitialSurveyAdapterOperations))
			throw new ClassCastException("IInitialSurveyAdapterOperations cast exception.");

		initialSurveyOperationsContext = (IInitialSurveyAdapterOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView surveyQuestionTextView;

		private TextView responseItemLabelTextView1;
		private TextView responsePercentageTextView1;

		private TextView responseItemLabelTextView2;
		private TextView responsePercentageTextView2;

		private TextView responseItemLabelTextView3;
		private TextView responsePercentageTextView3;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			surveyQuestionTextView = itemView.findViewById(R.id.surveyQuestionTextView);
			responseItemLabelTextView1 = itemView.findViewById(R.id.responseItemLabelTextView1);
			responsePercentageTextView1 = itemView.findViewById(R.id.responsePercentageTextView1);
			responseItemLabelTextView2 = itemView.findViewById(R.id.responseItemLabelTextView2);
			responsePercentageTextView2 = itemView.findViewById(R.id.responsePercentageTextView2);
			responseItemLabelTextView3 = itemView.findViewById(R.id.responseItemLabelTextView3);
			responsePercentageTextView3 = itemView.findViewById(R.id.responsePercentageTextView3);
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

		private void displayInitialSurveyData(InitialSurveyData initialSurveyData)
		{
			surveyQuestionTextView.setText(initialSurveyData.getQuestion());
//			responseItemLabelTextView1.setText(initialSurveyData.getResponseItem1());
//			responseItemLabelTextView2.setText(initialSurveyData.getResponseItem2());
//			responseItemLabelTextView3.setText(initialSurveyData.getResponseItem3());
//			responsePercentageTextView1.setText(Double.toString(initialSurveyData.getResponsePercentage()));
//			responsePercentageTextView2.setText(Double.toString(initialSurveyData.getResponsePercentage2()));
//			responsePercentageTextView3.setText(Double.toString(initialSurveyData.getResponsePercentage3()));
		}
	}

	@NonNull
	@Override
	public InitialSurveyAdapter_Professor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_initial_survey_professor, parentViewGroup, false);
		return new InitialSurveyAdapter_Professor.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull InitialSurveyAdapter_Professor.ViewHolder holder, int position)
	{
		List<InitialSurveyData> initialSurveyDataList = initialSurveyOperationsContext.onGetInitialSurveyQuestions();
		InitialSurveyData initialSurveyData = initialSurveyDataList.get(position);
		holder.displayInitialSurveyData(initialSurveyData);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (initialSurveyOperationsContext.onGetInitialSurveyQuestions() != null)
			return initialSurveyOperationsContext.onGetInitialSurveyQuestions().size();
		return -1;
	}
}
