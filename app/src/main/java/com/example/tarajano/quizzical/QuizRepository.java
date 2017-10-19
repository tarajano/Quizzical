package com.example.tarajano.quizzical;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-19.
 */

public class QuizRepository {

    private static  final String LOG_TAG = QuizRepository.class.getSimpleName();
    private static  final String QUIZ_JSON = "quiz.json";

    private Context context;

    public QuizRepository(Context context) {
        this.context = context;
    }

    public Quiz getQuiz(){

        InputStream assetInputStream;
        try{
            assetInputStream = context.getAssets().open(QUIZ_JSON);
        }catch(IOException e){
            Log.e(LOG_TAG, "Could not open json", e);
            return null;
        }

    }

}
