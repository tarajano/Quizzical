package com.example.tarajano.quizzical;

/**
 * Created by Manuel Alonso Tarajano (tarajano@gmail.com) on 2017-10-18.
 */

public class Question {

    private boolean answer;
    private String statement;

    // Constructor
    public Question(String proposition, boolean answer) {
        this.answer = answer;
        this.statement = proposition;
    }

    // Getters
    public boolean getAnswer() {
        return answer;
    }

    public String getStatement() {
        return statement;
    }
}
