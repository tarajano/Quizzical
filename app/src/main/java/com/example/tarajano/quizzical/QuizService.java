package com.example.tarajano.quizzical;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-19.
 */
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizService {
    @GET("cdn/quiz.json")
    Call<Quiz> getQuiz();
}
