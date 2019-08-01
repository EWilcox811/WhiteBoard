package edu.nu.jam.capstone.Requestsmodule;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Button testBtn;
    private Button testBtn2;
    private ArrayList<HashMap<String,String>> classListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        testBtn = findViewById(R.id.testbutton);
        testBtn2 = findViewById(R.id.testbutton2);


        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClassListHelper(new AsyncResponder(){
                    @Override
                    public void processFinish(String output){
                        dbHelper = new DatabaseHelper();
                        dbHelper.onClassListHelperCompleted(output);
                        classListArray = dbHelper.getClassList();
                        for(int i = 0; i< classListArray.size();i++)
                        {
                            System.out.println(classListArray.get(i).get("subject"));
                        }
                    }
                }, MainActivity.this).execute();
            }
        });

        testBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginHelper(new AsyncResponder(){
                    @Override
                    public void processFinish(String output){
                        dbHelper = new DatabaseHelper();
                        dbHelper.onLoginFinished(output);
                        String token = dbHelper.getLoginToken();
                        System.out.println(token);
                    }
                }, MainActivity.this, "john_bravo", "password").execute();
            }
        });


    }
}
