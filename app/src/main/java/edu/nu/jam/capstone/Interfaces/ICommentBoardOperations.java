package edu.nu.jam.capstone.Interfaces;

import androidx.cardview.widget.CardView;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;

public interface ICommentBoardOperations
{
	List<CommentData> onGetComments();
	void onItemSelected(int position, CardView view);
}