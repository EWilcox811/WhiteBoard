package edu.nu.jam.capstone.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.capstone.Data.SessionData;

public interface ISessionSelectionOperations
{
	List<SessionData> onGetSessions();
	void onItemSelected(int position, View view);
}
