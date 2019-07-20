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
import edu.nu.jam.capstone.Data.Comment;
import java.util.List;
import edu.nu.jam.capstone.Interfaces.IPriorityCommentOperations;
import edu.nu.jam.capstone.R;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>
{
	private IPriorityCommentOperations commentOperationsContext;

	public CommentAdapter(Context context)
	{
		if (!(context instanceof IPriorityCommentOperations))
			throw new ClassCastException("IPriorityCommentOperations cast exception.");

		commentOperationsContext = (IPriorityCommentOperations) context;
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
			commentTextView = itemView.findViewById(R.id.commentTextView);
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

		private void displayRolodexData(Comment comment)
		{
			commentTextView.setText(comment.getContent());
			percentageTextView.setText(Double.toString(comment.getPercentage()));
		}
	}

	@NonNull
	@Override
	public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int viewType)
	{
		View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.card_majority_concern, parentViewGroup, false);
		return new CommentAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position)
	{
		List<Comment> commentList = commentOperationsContext.onGetComments();
		Comment rolodexRecordInformation = commentList.get(position);
		holder.displayRolodexData(rolodexRecordInformation);
		holder.itemView.setTag(position);
	}

	@Override
	public int getItemCount()
	{
		return commentOperationsContext.onGetComments().size();
	}
}
