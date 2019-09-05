package edu.nu.jam.capstone.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.capstone.Data.CommentData;
import edu.nu.jam.capstone.Data.ProgressiveSurveyData;

// todo: change name (remove adapter)

public interface IProgressiveSurveyAdapterOperations
{
	List<ProgressiveSurveyData> onGetLearningObjectives();
	void onItemSelected(int position, View view);
}
