package com.emuen.simpleQuiz;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView firstOption;
    private TextView secondtOption;
    private TextView thirdOption;
    private TextView fourthOption;

    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromResources());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("questions");
            Log.d("Simple", "userArray " + userArray.length() );
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                Log.d("Simple", "Get user detail" + userDetail.toString());

                // fetch email and name and store it in arraylist
                questions.add(userDetail.getString("question"));
                answers.add(userDetail.getString("answer"));

            }
        } catch (JSONException e) {
            Log.d("Simple", "JSONEXCEPTION: " + e);
            e.printStackTrace();
        }

        Log.d("Simple", "onCreate " + questions );

        mTextMessage = (TextView) findViewById(R.id.message);
        firstOption = (TextView) findViewById(R.id.option1);
        secondtOption = (TextView) findViewById(R.id.option2);
        thirdOption = (TextView) findViewById(R.id.option3);
        fourthOption = (TextView) findViewById(R.id.option4);



        mTextMessage.setText(questions.get(0));
        firstOption.setText("Berlin");
        secondtOption.setText("Paris");
        thirdOption.setText("København");
        fourthOption.setText("London");
    }

    public String loadJSONFromResources() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.quiz_capitals);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
