package edu.nu.jam.capstone.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.capstone.Data.ProgressiveSurveyData;

public interface IProgressiveSurveyAdapterOperations
{
	List<ProgressiveSurveyData> onGetLearningObjectives();
	void onItemSelected(int position, View view);
	void setStudentConfidence(int position, double confidence);//used for student view only, will be the value passed to backend
	void setClassConfidence(int position);//used for professor view only will display the class confidence metric.
}
