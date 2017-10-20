package com.example.tarajano.quizzical;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-19.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuizService {
    @GET("cdn/quizzes/{id}.json")
    Call<Quiz> getQuiz(@Path("id") int id);

    @GET("cdn/quizzes.json")
    Call<List<Quiz>> getQuizzes();
}
