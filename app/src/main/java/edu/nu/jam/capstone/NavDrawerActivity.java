package edu.nu.jam.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

// TODO: Uncomment out this code.

public class NavDrawerActivity {}
//
//public class NavDrawerActivity extends AppCompatActivity
//		implements NavigationView.OnNavigationItemSelectedListener
//{
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		Intent intent = getIntent();
//		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.nav_drawer);
//		Toolbar toolbar = findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
//		FloatingActionButton fab = findViewById(R.id.newCommentFAB);
//		fab.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//						.setAction("Action", null).show();
//			}
//		});
//		DrawerLayout drawer = findViewById(R.id.drawer_layout);
//		NavigationView navigationView = findViewById(R.id.nav_view);
//		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//		drawer.addDrawerListener(toggle);
//		toggle.syncState();
//		navigationView.setNavigationItemSelectedListener(this);
//	}
//
//	@Override
//	public void onBackPressed() {
//		DrawerLayout drawer = findViewById(R.id.drawer_layout);
//		if (drawer.isDrawerOpen(GravityCompat.START)) {
//			drawer.closeDrawer(GravityCompat.START);
//		} else {
//			super.onBackPressed();
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
//		//noinspection SimplifiableIfStatement
//		switch(id)
//		{
//			case(R.id.action_settings):
//				//TODO takes them to their profile setting
//				break;
//			case(R.id.action_logout):
//				//TODO logs them out and sends them back to login screen
//				break;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}
//
//	@SuppressWarnings("StatementWithEmptyBody")
//	@Override
//	public boolean onNavigationItemSelected(MenuItem item) {
//		// Handle navigation view item clicks here.
//		int id = item.getItemId();
//
//		switch(id){
//			case(R.id.nav_progressiveSurvey):
//				//TODO send to progressive survey activity
//				break;
//			case(R.id.nav_logout):
//				//TODO logout and send back to login screen
//				break;
//			case(R.id.nav_newComment):
//				//TODO send to new comment activity
//				break;
//			// Handle the camera action
//		}
//
//		DrawerLayout drawer = findViewById(R.id.drawer_layout);
//		drawer.closeDrawer(GravityCompat.START);
//		return true;
//	}
//}