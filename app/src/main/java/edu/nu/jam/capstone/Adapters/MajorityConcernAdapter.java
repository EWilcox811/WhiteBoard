package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.nu.jam.capstone.Data.CommentData;
import java.util.List;
import edu.nu.jam.capstone.Interfaces.IMajorityConcernOperations;
import edu.nu.jam.capstone.R;


public class MajorityConcernAdapter extends RecyclerView.Adapter<MajorityConcernAdapter.ViewHolder>
{
	//TODO: fix id
	private IMajorityConcernOperations commentOperationsContext;

	public MajorityConcernAdapter(Context context)
	{
		if (!(context instanceof IMajorityConcernOperations))
			throw new ClassCastException("IMajorityConcernOperations cast exception.");

		commentOperationsContext = (IMajorityConcernOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		private TextView commentTextView;
		private TextView percentageTextView;
		private CheckBox markAsHandledCheckBox;
		private Button viewRepliesBtn;

		ViewHolder(@NonNull View itemView)
		{
			super(itemView);

			bindControls();
			registerHandlers();
		}

		private void bindControls()
		{
			commentTextView = itemView.findViewById(R.id.surveyQuestionTextView);
			percentageTextView = itemView.findViewById(R.id.percentageTextView);
			markAsHandledCheckBox = itemView.findViewById(R.id.markAsHandledCheckBox);
			viewRepliesBtn = itemView.findViewById(R.id.viewRepliesBtn);
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

		private void displayRolodexData(CommentData commentData)
		{
			commentTextView.setText(commentData.getContent());
			percentageTextView.setText(Double.toString(commentData.getPercentage()));
		}
	}

	@NonNull
	@Override
	public MajorityConcernAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_majority_concern, parentViewGroup, false);
		return new MajorityConcernAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MajorityConcernAdapter.ViewHolder holder, int position)
	{
		List<CommentData> commentDataList = commentOperationsContext.onGetConcerns();
		CommentData rolodexRecordInformation = commentDataList.get(position);
		holder.displayRolodexData(rolodexRecordInformation);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (commentOperationsContext.onGetConcerns() != null)
			return commentOperationsContext.onGetConcerns().size();
		return -1;
	}
}
