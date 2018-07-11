package com.example.user.brainquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button button_confirm_next;
    private TextView text_view_score;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    Button button;
    public int counter = 30;
    TextView countdown;
    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private QuestionBank mQuestionLibrary = new QuestionBank();


    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        countdown = (TextView) findViewById(R.id.text_view_countdown);
        /*timer();*/
        // setup screen for the first question with four alternative to answer
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.text_view_question);
        mButtonChoice1 = (Button)findViewById(R.id.radio_button1);
        mButtonChoice2 = (Button)findViewById(R.id.radio_button2);
        mButtonChoice3 = (Button)findViewById(R.id.radio_button3);
        mButtonChoice4 = (Button)findViewById(R.id.radio_button4);
        updateQuestion();
        timer();
        // show current total score for the user
        updateScore(mScore);
    }
    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mQuestionNumber<mQuestionLibrary.getLength() ){
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            Toast.makeText(QuizActivity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore); // pass the current score to the second screen
            startActivity(intent);
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/"+mQuestionLibrary.getLength());
    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText().equals(mAnswer)){
            if(counter>0){
                mScore = mScore + 1;
                Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                counter=30;
            }
            }
           else
        {
            Toast.makeText(QuizActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            counter=30;
        }
        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
        counter=30;
    }
    private void timer() {
       new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                countdown.setText(String.valueOf(counter));
                counter--;
                if (counter < 10) {
                    countdown.setTextColor(Color.RED);
                }
                else {
                    countdown.setTextColor(Color.BLACK);
                }
            }
            @Override
            public void onFinish() {
                countdown.setText("Times up!!!");
                updateQuestion();
            }
        }.start();
        }

        }

 /*   button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        countdown.setText(String.valueOf(counter));
                        counter--;
                    }

                    @Override
                    public void onFinish() {
                        countdown.setText("Finish");
                    }
                }.start();
            }
        });*/
