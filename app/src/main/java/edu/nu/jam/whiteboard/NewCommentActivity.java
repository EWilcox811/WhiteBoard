package edu.nu.jam.whiteboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
