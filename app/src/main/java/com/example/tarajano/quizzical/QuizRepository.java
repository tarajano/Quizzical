package com.example.tarajano.quizzical;

import android.content.Context;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okio.Okio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public Quiz getLocalQuiz(String quizFilePath){

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
            Quiz quiz = jsonAdapter.fromJson(
                    Okio.buffer(Okio.source(assetInputStream))
            );
            return quiz;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not parse json", e);
            return null;
        }

    }

    public void getRemoteQuiz(int id, final Callback callback){

        QuizService service = getQuizService();

        service.getQuiz(id).enqueue(new retrofit2.Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    private QuizService getQuizService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://oolong.tahnok.me/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return retrofit.create(QuizService.class);
    }

    public interface Callback{
        void onFailure();
        void onSuccess(Quiz quiz);
    }

    public void getRemoteQuizzes(final QuizzesCallback callback) {
        QuizService service = getQuizService();

        service.getQuizzes().enqueue(new retrofit2.Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }
            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public interface QuizzesCallback {
        void onFailure();
        void onSuccess(List<Quiz> quizzes);
    }
}
