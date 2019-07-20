package edu.nu.jam.capstone.Interfaces;

import android.view.View;
import edu.nu.jam.capstone.Data.Comment;

import java.util.List;

public interface IPriorityCommentOperations
{
	List<Comment> onGetComments();
	void onItemSelected(int position, View view);
}