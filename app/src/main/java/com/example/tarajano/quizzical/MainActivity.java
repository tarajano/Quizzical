package com.example.tarajano.quizzical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonTrue, buttonFalse;
    private TextView questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating refs for buttons
        buttonTrue = (Button) findViewById(R.id.buttontTrue);
        buttonFalse = (Button) findViewById(R.id.buttontFalse);

        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText("Hello from code.");

        // Setting actions for buttons
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "True button clicked");
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("bootcamp", "False button clicked");
            }
        });
    }

}
