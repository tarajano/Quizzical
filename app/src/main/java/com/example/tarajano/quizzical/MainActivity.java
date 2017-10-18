package com.example.tarajano.quizzical;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonTrue, buttonFalse, buttonReset;
    private TextView questionText, answerText;
    private View mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating refs for buttons
        buttonTrue = (Button) findViewById(R.id.buttontTrue);
        buttonFalse = (Button) findViewById(R.id.buttontFalse);
        buttonReset = (Button) findViewById(R.id.buttontReset);

        // Refs to text
        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText("Great workout this morning right?");
        answerText = (TextView) findViewById(R.id.anserwText);

        // Refs to activity
        mainActivity = (View) findViewById(R.id.mainActivity);

        // Setting actions for buttons
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "True button clicked");
                answerText.setText("Sure it was!");
                mainActivity.setBackgroundColor(Color.parseColor("#47d147"));
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "False button clicked");
                answerText.setText("Ups! I disagree.");
                mainActivity.setBackgroundColor(Color.parseColor("#ff471a"));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "Reset button clicked");
                answerText.setText("");
                mainActivity.setBackgroundColor(Color.parseColor("#cce6ff"));
            }
        });

    }

}
