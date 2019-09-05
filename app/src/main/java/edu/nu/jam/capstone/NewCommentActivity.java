package edu.nu.jam.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.NavUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.nu.jam.capstone.Requestsmodule.DatabaseHelper;

public class NewCommentActivity extends AppCompatActivity
{
    //Intent extra
    public static final String EXTRA_NEW_COMMENT = "comment";
    public static final String EXTRA_IS_ANONYMOUS = "isAnonymous";

    private FloatingActionButton confirmCommentFAB;
    private EditText commentEditText;
    private AppCompatCheckBox isAnonymousCB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        bindControls();
        returnIntent();
    }

    private void returnIntent()
    {
        final Intent intentReturn = new Intent();
        confirmCommentFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intentReturn.putExtra(EXTRA_NEW_COMMENT, commentEditText.getText().toString());
                intentReturn.putExtra(EXTRA_IS_ANONYMOUS, isAnonymousCB.isChecked());
                setResult(Activity.RESULT_OK, intentReturn);
                finish();
            }
        });

    }

    private void bindControls()
    {
        confirmCommentFAB = findViewById(R.id.confirmCommentFAB);
        commentEditText = findViewById(R.id.newCommentET);
        isAnonymousCB = findViewById(R.id.anonymityCheckBox);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        MenuItem menuItem = item;
        if(item.getItemId() == android.R.id.home)
        {
            Toast.makeText(this, "CLICK", Toast.LENGTH_LONG).show();
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
