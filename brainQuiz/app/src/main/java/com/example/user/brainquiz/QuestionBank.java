package com.example.user.brainquiz;

public class QuestionBank {
    //Array of Questions
    private String textQuestions[] = {
            "1. 1+1=",
            "2. 1+2=",
            "3. 1+3=",
            "4. 1+4="
    };
    //Array of answers
    private String multipleChoice[][] = {
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"},
            {"1", "2", "3", "5"}
    };
    //Array of Answers -in the same order as array of questions
    private String mCorrectAnswers[] = {"2", "3", "4", "5"};

    //method returns number of question
    public int getLength() {
        return textQuestions.length;
    }

    //method returns question from array textQuestion[] based on array inbox
    public String getQuestion(int a) {
        String question = textQuestions[a];
        return question;
    }

    public String getChoice(int index, int num) {
        String choice0 = multipleChoice[index][num - 1];
        return choice0;
    }

    //method returns correct answer for the question based on array index
    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
