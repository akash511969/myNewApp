package com.example.user.brainquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingScreenActivity extends Activity {
private static final int REQUEST_CODE_QUIZ=1;
public static final String SHARE_PREFS="shared_Prefs";
    public static final String KEY_HIGHSCORE="keyHighScore";
    private TextView textViewHighscore;
    private int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        textViewHighscore=findViewById(R.id.textScore);
          Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    }
