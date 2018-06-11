package com.emuen.simpleQuiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String question;
        String answerCorrect;
        String answerWrong1;
        String answerWrong2;
        String answerWrong3;

        Random r = new Random();

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromResources());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("questions");
            Log.d("Simple", "userArray " + userArray.length() );
            // implement for loop for getting users list data

            int i1 = r.nextInt(userArray.length());
            int i2 = r.nextInt(userArray.length());
            int i3 = r.nextInt(userArray.length());
            int i4 = r.nextInt(userArray.length());
            while (i1 == i2) {
                i2 = r.nextInt(userArray.length());
            }
            while ((i3 == i1) || (i3 == i2)) {
                i3 = r.nextInt(userArray.length());
            }
            while ((i4 == i1) || (i4 == i2) || (i4 == i3)) {
                i4 = r.nextInt(userArray.length());
            }

            JSONObject userDetail = userArray.getJSONObject(i1);
            question = userDetail.getString("question");
            answerCorrect = userDetail.getString("answer");
            userDetail = userArray.getJSONObject(i2);
            answerWrong1 = userDetail.getString("answer");
            userDetail = userArray.getJSONObject(i3);
            answerWrong2 = userDetail.getString("answer");
            userDetail = userArray.getJSONObject(i4);
            answerWrong3 = userDetail.getString("answer");

            Log.d("Simple", "Spørgsmål: " + question);
            Log.d("Simple", "Svar korrekt: " + answerCorrect);
            Log.d("Simple", "Svar forkert 1: " + answerWrong1);
            Log.d("Simple", "Svar forkert 2: " + answerWrong2);
            Log.d("Simple", "Svar forkert 3: " + answerWrong3);

            /*
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                Log.d("Simple", "Get user detail" + userDetail.toString());

                // fetch email and name and store it in arraylist
                question.add(userDetail.getString("question"));
                answer.add(userDetail.getString("answer"));
            }
                */
        } catch (JSONException e) {
            Log.d("Simple", "JSONEXCEPTION: " + e);
            e.printStackTrace();
        }

        //Log.d("Simple", "onCreate " + question );

        mTextMessage = (TextView) findViewById(R.id.message);


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
