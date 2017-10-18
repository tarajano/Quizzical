package com.example.tarajano.quizzical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating refs for buttons
        Button buttonTrue = (Button) findViewById(R.id.buttontTrue);
        Button buttonFalse = (Button) findViewById(R.id.buttontFalse);

        
    }

}
