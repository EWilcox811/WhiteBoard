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
		private TextView surveyQuestionTextView;

		private TextView responseCheckBox1;
		private TextView responseItemDescriptionTextView1;

		private TextView responseCheckBox2;
		private TextView responseItemDescriptionTextView2;

		private TextView responseCheckBox3;
		private TextView responseItemDescriptionTextView3;


		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
//			surveyQuestionTextView = itemView.findViewById(R.id.surveyQuestionTextView);
//			responseCheckBox1 = itemView.findViewById(R.id.responseCheckBox1);
//			responseItemDescriptionTextView1 = itemView.findViewById(R.id.responseItemDescriptionTextView1);
//			responseCheckBox2 = itemView.findViewById(R.id.responseCheckBox2);
//			responseItemDescriptionTextView2 = itemView.findViewById(R.id.responseItemDescriptionTextView2);
//			responseCheckBox3 = itemView.findViewById(R.id.responseCheckBox3);
//			responseItemDescriptionTextView3 = itemView.findViewById(R.id.responseItemDescriptionTextView3);
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
//			responseCheckBox1.setText(initialSurveyData.getResponseItem1());
//			responseCheckBox2.setText(initialSurveyData.getResponseItem2());
//			responseCheckBox3.setText(initialSurveyData.getResponseItem3());
//			responseItemDescriptionTextView1.setText(initialSurveyData.getResponseDescription());
//			responseItemDescriptionTextView2.setText(initialSurveyData.getResponseDescription2());
//			responseItemDescriptionTextView3.setText(initialSurveyData.getResponseDescription3());
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
	public void onBindViewHolder(@NonNull InitialSurveyAdapter_Student.ViewHolder holder, int position)
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
