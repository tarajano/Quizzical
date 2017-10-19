package com.example.tarajano.quizzical;

import android.content.Context;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;

import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-19.
 */

public class QuizRepository {

    private static  final String LOG_TAG = QuizRepository.class.getSimpleName();
    private Context context;

    public QuizRepository(Context context) {
        this.context = context;
    }

    public Quiz getQuiz(String quizFilePath){

        InputStream assetInputStream;
        try{
            assetInputStream = context.getAssets().open(quizFilePath);
        }catch(IOException e){
            Log.e(LOG_TAG, "Could not open json", e);
            return null;
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Quiz> jsonAdapter = moshi.adapter(Quiz.class);

        try {
            Quiz quiz = jsonAdapter.fromJson(Okio.buffer(Okio.source(assetInputStream)));
            return quiz;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not parse json", e);
            return null;
        }

    }

    public Quiz getRemoteQuiz(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oolong.tahnok.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return null;
    }

}
