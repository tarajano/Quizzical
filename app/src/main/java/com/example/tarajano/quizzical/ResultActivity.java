package com.example.tarajano.quizzical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-18.
 */

public class ResultActivity extends AppCompatActivity {

    TextView resultText;
    public static final String KEY_SCORE = "score";
    public static final String KEY_TOTAL = "total";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = (TextView) findViewById(R.id.results_text);
        resultText.setText(KEY_SCORE);
        int score = getIntent().getIntExtra(KEY_SCORE,0);
        int total = getIntent().getIntExtra(KEY_TOTAL,0);

        String result = String.format("%d / %d", score, total);
        resultText.setText(result);
    }

}
