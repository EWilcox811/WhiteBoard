package edu.nu.jam.whiteboard;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

public class SessionSelectionActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session_selection);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}
	/**
	 * Used for the toolbar
	 * @param item
	 * @return
	 */

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		if(item.getItemId() == android.R.id.home)
		{
			NavUtils.navigateUpFromSameTask(this);
		}
		return super.onOptionsItemSelected(item);
	}
}