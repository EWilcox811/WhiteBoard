package edu.nu.jam.capstone.Interfaces;

import android.view.View;

import java.util.List;

public interface ISurveySelectionOperations
{
	List<String> onGetSurveys();
	void onItemSelected(int position, View view);
}