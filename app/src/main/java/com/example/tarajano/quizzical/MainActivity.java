package com.example.tarajano.quizzical;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TODO
    // fix the rotation and onSave states

    // Var fields
    private Quiz quiz;
    private int score = 0;
    private int questionIndex = 0;
    private TextView questionText, answerText;
    private boolean questionAnswered, lastAnswer;
    private Button buttonTrue, buttonFalse, buttonNext;
    private static final long startTime = 0;
    private String pathToJSON = "quiz.json";

    // Constants fields
    private static final String KEY_SCORE = "score";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_LAST_ANSWER = "LAST_ANSWER";
    private static final String KEY_ANSWERED = "QUESTION_ANSWERED";
    private static final String KEY_QUESTION_INDEX = "KEY_QUESTION_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display layout
        setContentView(R.layout.activity_main);

        // Refs to buttons
        buttonTrue = (Button) findViewById(R.id.buttontTrue);
        buttonFalse = (Button) findViewById(R.id.buttontFalse);
        buttonNext =  (Button) findViewById(R.id.buttontNext);

        // Refs to text
        answerText = (TextView) findViewById(R.id.anserwText);
        questionText = (TextView) findViewById(R.id.questionText);

        // Create question
        quiz = new QuizRepository(this).getQuiz(pathToJSON);

        // Actions for buttons
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "buttonTrue clicked");
                checkAnswer(true);
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "buttonFalse clicked");
                checkAnswer(false);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "buttonNext clicked");
                nextQuestion();
            }
        });

        // Saving Bundle data
        if (savedInstanceState != null) {
            Log.e("bootcamp", "savedInstanceState not null");
            questionAnswered = savedInstanceState.getBoolean(KEY_ANSWERED, false);
            questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX, -1);
            score = savedInstanceState.getInt(KEY_SCORE, -1);
            Log.e("bootcamp", " Qidx:" + questionIndex + " Qanswd:" + questionAnswered + " score:" + score);

            if (questionAnswered) {
                lastAnswer = savedInstanceState.getBoolean(KEY_LAST_ANSWER);
            }

        } else {
            questionIndex = 0;
            Log.e("bootcamp", "savedInstanceState is null. (questionIndex = 0)");
        }

        if(questionAnswered){
            checkAnswer(lastAnswer);
        }
    }

    private void nextQuestion(){
        questionIndex = questionIndex + 1;

        if(questionIndex == quiz.getQuestions().size()){
            questionIndex = 0;
            Intent resultsIntent = new Intent(this,ResultActivity.class);
            resultsIntent.putExtra(ResultActivity.KEY_SCORE,score);
            resultsIntent.putExtra(ResultActivity.KEY_TOTAL, quiz.getQuestions().size());
            startActivity(resultsIntent);
        } else {
            questionAnswered = false;
            showQuestion();
        }
    }

    private void showQuestion(){
        questionText.setText(getCurrentQuestion().getStatement());
        answerText.setText("");
        buttonNext.setEnabled(false);
    }

    private Question getCurrentQuestion(){
        return quiz.getQuestions().get(questionIndex);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWERED, questionAnswered);
        outState.putBoolean(KEY_LAST_ANSWER, lastAnswer);
        outState.putInt(KEY_QUESTION_INDEX, questionIndex);
    }

    private void checkAnswer(boolean anwser){
        questionAnswered = true;
        lastAnswer = anwser;
        if (anwser == getCurrentQuestion().getAnswer()){
            answerText.setText("Correct");
            score = score + 1;
            //buttonTrue.setEnabled(false);
        } else {
            answerText.setText("Incorrect");
            //buttonFalse.setEnabled(false);
        }
        buttonNext.setEnabled(true);
        Log.e("bootcamp", "checkAnswer");
    }

}
