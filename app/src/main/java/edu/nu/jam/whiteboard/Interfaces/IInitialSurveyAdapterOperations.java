package edu.nu.jam.whiteboard.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.whiteboard.Data.InitialSurveyData;

public interface IInitialSurveyAdapterOperations
{
	List<InitialSurveyData> onGetInitialSurveyQuestions();
	void onItemSelected(int position, View view);
	void setStudentResponsePercentage(int position, double response);
}
