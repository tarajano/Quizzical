package com.example.tarajano.quizzical;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.tarajano.quizzical.R.id.simpleChronometer;

public class MainActivity extends AppCompatActivity implements QuizRepository.Callback {

    // Var fields
    private Quiz quiz;
    private int score = 0;
    private int questionIndex = 0;
    private TextView questionText, answerText;
    private boolean questionAnswered, lastAnswer;
    private Button buttonTrue, buttonFalse, buttonNext;
    private static final long startTime = 0;
    private Chronometer simpleChronometer;

    // Constants fields
    private static final String KEY_SCORE = "score";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_LAST_ANSWER = "LAST_ANSWER";
    private static final String KEY_ANSWERED = "QUESTION_ANSWERED";
    private static final String KEY_QUESTION_INDEX = "KEY_QUESTION_INDEX";
    private static final String QUIZ_ID = "quiz_id";
    //private static final String KEY_TOTALTIME = "totaTime";

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

        // Chronometer
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.setFormat("Time - %s"); // set the format for a chronometer
        simpleChronometer.start(); // start a chronometer

        // Create question
        int id = getIntent().getIntExtra(QUIZ_ID, -1);
        new QuizRepository(this).getRemoteQuiz(id, this);

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

        //TO.DO fix the rotation and onSave states
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
            //resultsIntent.putExtra(ResultActivity.KEY_TOTALTIME, simpleChronometer.  );
            startActivity(resultsIntent);
            // Chrono
            simpleChronometer.stop(); // stop a chronometer
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
        Question q = quiz.getQuestions().get(questionIndex);
        Log.e("getCurrentQuestion", " q:" + q + " questionIndex:" + questionIndex);
        return q;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWERED, questionAnswered);
        outState.putBoolean(KEY_LAST_ANSWER, lastAnswer);
        outState.putInt(KEY_QUESTION_INDEX, questionIndex);
        //TODO save quiz state on rotation
    }

    private void checkAnswer(boolean answer){
        questionAnswered = true;
        lastAnswer = answer;
        boolean rightAnswer = getCurrentQuestion().getAnswer();
        Log.e("bootcamp checkAnswer", "userAnswer:" + answer + " rightAnswer:" + rightAnswer);

        if (answer == rightAnswer){
            answerText.setText("Correct");
            score = score + 1;
        } else {
            answerText.setText("Incorrect");
        }
        buttonNext.setEnabled(true);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Something failed in the network", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Quiz quiz) {
        this.quiz = quiz;
        //moved here from onCreate
        showQuestion();
        if(questionAnswered){
            checkAnswer(lastAnswer);
        }
    }
}
