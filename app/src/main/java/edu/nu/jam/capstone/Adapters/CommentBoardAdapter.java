package edu.nu.jam.capstone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
            throw new ClassCastException("Activity does not implement ICommentBoardOperations.");

        commentOperationsContext = (ICommentBoardOperations) context;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView commentTextView;
        private TextView repliesTextView;
        private TextView upVotesTextView;
        private ImageView replyImageView;
        private ImageView upVoteImageView;
        private TextView usernameTextView;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            bindControls();
            registerHandlers();

        }

        private void bindControls()
        {
            commentTextView = itemView.findViewById(R.id.commentCardTextView);
            repliesTextView = itemView.findViewById(R.id.numReplies);
            upVotesTextView = itemView.findViewById(R.id.numVotes);
            replyImageView = itemView.findViewById(R.id.replyToComment);
            upVoteImageView = itemView.findViewById(R.id.upVoteComment);
            usernameTextView = itemView.findViewById(R.id.userNameTextView);
        }

        private void registerHandlers()
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    commentOperationsContext.onItemSelected(getAdapterPosition());
                }
            });
            replyImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    commentOperationsContext.onReplyClicked(getAdapterPosition());
                }
            });

        }

        private void displayCommentData(CommentData commentData)
        {
            commentTextView.setText(commentData.getContent());
            repliesTextView.setText(Integer.toString(commentData.getNumberOfReplies()));
            upVotesTextView.setText(Integer.toString(commentData.getUpvotes()));
            usernameTextView.setText(commentData.getUsername());
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
    public void onBindViewHolder(@NonNull CommentBoardAdapter.ViewHolder holder, final int position)
    {
        List<CommentData> commentDataList = commentOperationsContext.onGetComments();
        CommentData commentData = commentDataList.get(position);
        holder.displayCommentData(commentData);
        holder.itemView.setTag(position);

        holder.replyImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                commentOperationsContext.onReplyClicked(position);
            }
        });
        holder.upVoteImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                commentOperationsContext.onUpVoteClicked(position);
            }
        });
        holder.commentTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                commentOperationsContext.onTextViewClicked(position);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        if (commentOperationsContext.onGetComments() != null)
            return commentOperationsContext.onGetComments().size();
        return -1;
    }

}
