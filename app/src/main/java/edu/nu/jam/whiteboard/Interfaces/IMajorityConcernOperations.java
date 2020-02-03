package edu.nu.jam.whiteboard.Interfaces;

import android.view.View;
import edu.nu.jam.whiteboard.Data.CommentData;

import java.util.List;

public interface IMajorityConcernOperations
{
	List<CommentData> onGetConcerns();
	void onItemSelected(int position, View view);
}