package com.example.tarajano.quizzical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-18.
 */

public class Quiz {

    private static Quiz quiz;
    private List<Question> questions = new ArrayList<>();

    public static Quiz getInstance() {
        if (quiz == null) {
            quiz = new Quiz();
            quiz.addQuestion(new Question("The moon is made of cheese?", false));
            quiz.addQuestion(new Question("The sum of the internal angles of a triangle is 180?", true));
            quiz.addQuestion(new Question("Did we had a good workout this morning?", true));
        }
        return quiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

}
