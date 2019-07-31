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

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;
import edu.nu.jam.capstone.R;

// TODO: Update all fields

public class CommentBoardAdapter extends RecyclerView.Adapter<CommentBoardAdapter.ViewHolder>
{
	private ICommentBoardOperations commentOperationsContext;

	public CommentBoardAdapter(Context context)
	{
		if (!(context instanceof ICommentBoardOperations))
			throw new ClassCastException("ICommentBoardOperations cast exception.");

		commentOperationsContext = (ICommentBoardOperations) context;
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{

		// TODO: change theses
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
//					int index = (int) view.getTag();
//					View fetchedViewOrigin = (View) phoneNumberTextView.getParent();
//					View fetchedViewIntermediate = (View) fetchedViewOrigin.getParent();
//					View fetchedViewTarget = (View) fetchedViewIntermediate.getParent();
//					rolodexOperationsContext.onItemSelected(index, fetchedViewTarget);
				}
			});
		}

		private void displayCommentData(CommentData commentData)
		{
			commentTextView.setText(commentData.getContent());
			percentageTextView.setText(Double.toString(commentData.getPercentage()));
		}
	}

	@NonNull
	@Override
	public CommentBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_comment, parentViewGroup, false);
		return new CommentBoardAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CommentBoardAdapter.ViewHolder holder, int position)
	{
		List<CommentData> commentDataList = commentOperationsContext.onGetComments();
		CommentData commentData = commentDataList.get(position);
		holder.displayCommentData(commentData);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		if (commentOperationsContext.onGetComments() != null)
			return commentOperationsContext.onGetComments().size();
		return -1;
	}
}
