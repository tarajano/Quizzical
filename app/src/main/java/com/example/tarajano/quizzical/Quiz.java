package com.example.tarajano.quizzical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-18.
 */

public class Quiz {

    private int id;
    private String title;
    private List<Question> questions = new ArrayList<>();

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

//    public void addQuestion(Question question) {
//        questions.add(question);
//    }
}

