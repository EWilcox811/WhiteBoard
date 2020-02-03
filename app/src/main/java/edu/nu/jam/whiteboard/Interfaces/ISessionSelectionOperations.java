package edu.nu.jam.whiteboard.Interfaces;

import android.view.View;

import java.util.List;

import edu.nu.jam.whiteboard.Data.SessionData;

public interface ISessionSelectionOperations
{
	List<SessionData> onGetSessions();
	void onItemSelected(int position, View view);
}
