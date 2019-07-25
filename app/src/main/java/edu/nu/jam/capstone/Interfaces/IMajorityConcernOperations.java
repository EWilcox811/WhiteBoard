package edu.nu.jam.capstone.Interfaces;

import android.view.View;
import edu.nu.jam.capstone.Data.CommentData;

import java.util.List;

public interface IMajorityConcernOperations
{
	List<CommentData> onGetConcerns();
	void onItemSelected(int position, View view);
}