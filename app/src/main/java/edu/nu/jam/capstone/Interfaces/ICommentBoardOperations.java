package edu.nu.jam.capstone.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;

public interface ICommentBoardOperations
{
	List<CommentData> onGetComments();
	void onItemSelected(int position, View view);
}