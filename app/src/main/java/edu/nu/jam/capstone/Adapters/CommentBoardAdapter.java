package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Interfaces.ICommentBoardOperations;
import edu.nu.jam.capstone.R;

// TODO: Update all fields

public class CommentBoardAdapter extends RecyclerView.Adapter<CommentBoardAdapter.ViewHolder>
{
	private ICommentBoardOperations commentOperationsContext, mListener;
	public void setOnICommentBoardListener(ICommentBoardOperations listener)
	{
		mListener = listener;
	}

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
		private TextView repliesTextView;
		private TextView upVotesTextView;
		private ImageView replyImageView;
		private ImageView upVoteImageView;

		ViewHolder(@NonNull View itemView, final ICommentBoardOperations listener)
		{
			super(itemView);

			bindControls();
			registerHandlers(listener);
		}

		private void bindControls()
		{
			commentTextView = itemView.findViewById(R.id.commentCardTextView);
			repliesTextView = itemView.findViewById(R.id.numReplies);
			upVotesTextView = itemView.findViewById(R.id.numVotes);
			replyImageView = itemView.findViewById(R.id.replyToComment);
			upVoteImageView = itemView.findViewById(R.id.upVoteComment);
		}

		private void registerHandlers(final ICommentBoardOperations listener)
		{
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					int index = (int) view.getTag();
					commentOperationsContext.onItemSelected(index);
				}
			});
			replyImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(listener != null)
					{
						int position = getAdapterPosition();
						if(position != RecyclerView.NO_POSITION)
						{
							listener.onReplyClicked(position);
						}
					}

				}
			});

		}

		private void displayCommentData(CommentData commentData)
		{
			commentTextView.setText(commentData.getContent());
			repliesTextView.setText(Integer.toString(commentData.getNumberOfReplies()));
			upVotesTextView.setText(Integer.toString(commentData.getUpvotes()));
		}
	}

	@NonNull
	@Override
	public CommentBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_comment, parentViewGroup, false);
		return new CommentBoardAdapter.ViewHolder(view,  mListener);
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
