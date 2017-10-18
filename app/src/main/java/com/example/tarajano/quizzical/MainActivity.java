package com.example.tarajano.quizzical;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TODO
    // fix the rotation and onSave states

    // Fields
    private Button buttonTrue, buttonFalse, buttonReset, buttonNext;
    private TextView questionText, answerText;
    private boolean questionAnswered, lastAnswer;
    private View mainActivity;
    private Quiz quiz;

    private static final String KEY_ANSWERED = "QUESTION_ANSWERED";
    private static final String KEY_LAST_ANSWER = "LAST_ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating refs for buttons
        buttonTrue = (Button) findViewById(R.id.buttontTrue);
        buttonFalse = (Button) findViewById(R.id.buttontFalse);
        buttonReset = (Button) findViewById(R.id.buttontReset);
        buttonNext =  (Button) findViewById(R.id.buttontNext);

        //quiz = Quiz.getInstance();

        // Refs to text
        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText("Great workout this morning right?");
        answerText = (TextView) findViewById(R.id.anserwText);

        // Ref to activity
        mainActivity = (View) findViewById(R.id.mainActivity);

        // Setting actions for buttons
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "True button clicked");
                //answerText.setText("Sure it was!");
                checkAnswer(true);
                //mainActivity.setBackgroundColor(Color.parseColor("#47d147"));
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "False button clicked");
                //answerText.setText("Ups! I disagree.");
                checkAnswer(false);
                //mainActivity.setBackgroundColor(Color.parseColor("#ff471a"));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "Reset button clicked");
                answerText.setText("");
                //mainActivity.setBackgroundColor(Color.parseColor("#cce6ff"));
            }
        });

        if (savedInstanceState != null) {
            Log.e("bootcamp", "if savedInstanceState");
            questionAnswered = savedInstanceState.getBoolean(KEY_ANSWERED, false);
            if (questionAnswered) {
                lastAnswer = savedInstanceState.getBoolean(KEY_LAST_ANSWER);
                checkAnswer(lastAnswer);
            }
        } else {
            Log.e("bootcamp", "else savedInstanceState");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWERED, lastAnswer);
        outState.putBoolean(KEY_LAST_ANSWER, questionAnswered);
    }

    private void checkAnswer(boolean anwser){
        questionAnswered = true;
        lastAnswer = anwser;
        if (anwser == true){
            answerText.setText("Correct");
            //mainActivity.setBackgroundColor(Color.parseColor("#47d147"));
        } else {
            answerText.setText("Incorrect");
            //mainActivity.setBackgroundColor(Color.parseColor("#ff471a"));
        }
        Log.e("bootcamp", "checkAnswer");
    }

}
