package edu.nu.jam.capstone.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Data.ProgressiveSurveyData;
import edu.nu.jam.capstone.Data.SessionData;
import edu.nu.jam.capstone.Interfaces.IProgressiveSurveyAdapterOperations;
import edu.nu.jam.capstone.Interfaces.ISessionSelectionOperations;
import edu.nu.jam.capstone.R;

public class SessionSelectionAdapter extends RecyclerView.Adapter<SessionSelectionAdapter.ViewHolder>
{
	private ISessionSelectionOperations sessionSelectionOperationsContext;

	public SessionSelectionAdapter(Context context)
	{
		if (!(context instanceof ISessionSelectionOperations))
			throw new ClassCastException("ISessionSelectionOperations cast exception.");

		sessionSelectionOperationsContext = (ISessionSelectionOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private CheckBox sessionCheckBox;
		private TextView courseNumberLabelTextView;
		private TextView courseNameLabelTextView;
		private TextView sessionLabelTextView;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			sessionCheckBox = itemView.findViewById(R.id.sessionCheckBox);
			courseNumberLabelTextView = itemView.findViewById(R.id.courseNumberLabelTextView);
			courseNameLabelTextView = itemView.findViewById(R.id.courseNameLabelTextView);
			sessionLabelTextView = itemView.findViewById(R.id.sessionLabelTextView);
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

		private void displaySessionData(SessionData sessionData)
		{
			courseNumberLabelTextView.setText(sessionData.getCourseNumber());
			courseNameLabelTextView.setText(sessionData.getCourseName());
			sessionLabelTextView.setText(sessionData.getTerm());
		}
	}

	@NonNull
	@Override
	public SessionSelectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_session_selection, parentViewGroup, false);
		return new SessionSelectionAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull SessionSelectionAdapter.ViewHolder holder, int position)
	{
		List<SessionData> sessionDataList = sessionSelectionOperationsContext.onGetSessions();
		SessionData sessionData = sessionDataList.get(position);
		holder.displaySessionData(sessionData);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (sessionSelectionOperationsContext.onGetSessions() != null)
			return sessionSelectionOperationsContext.onGetSessions().size();
		return -1;
	}
}