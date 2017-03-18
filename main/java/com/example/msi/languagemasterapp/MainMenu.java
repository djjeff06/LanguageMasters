package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.support.design.widget.Snackbar;

public class MainMenu extends AppCompatActivity {

    public static final String EXTRA_MAIN = "main";

    TextView tvMainMenuTitle, tvInfoDetails;
    Button btnLearn, btnQuiz, btnHighScore, btnBack;
    ImageButton btnInfoLearn, btnInfoQuiz, btnInfoHighScore, btnInfoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvMainMenuTitle = (TextView) findViewById(R.id.tv_mainmenutitle);
        tvInfoDetails = (TextView) findViewById(R.id.tv_infomaindetails);
        btnLearn = (Button) findViewById(R.id.btn_learn);
        btnQuiz = (Button) findViewById(R.id.btn_quiz);
        btnHighScore = (Button) findViewById(R.id.btn_highscore);
        btnBack = (Button) findViewById(R.id.btn_returncover);
        btnInfoLearn = (ImageButton) findViewById(R.id.btn_infolearn);
        btnInfoQuiz = (ImageButton) findViewById(R.id.btn_infoquiz);
        btnInfoHighScore = (ImageButton) findViewById(R.id.btn_infohighscore);
        btnInfoBack = (ImageButton) findViewById(R.id.btn_inforeturncover);

        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),WordPhraseSelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(EXTRA_MAIN,"quiz");
                startActivity(i);
            }
        });

        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),CoverPage.class);
                startActivity(i);
            }
        });

        btnInfoLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Click to start learning the language!");
                new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millis){}

                    @Override
                    public void onFinish(){
                        tvInfoDetails.setText("");
                    }
                }.start();
            }
        });

        btnInfoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Click to test yourself with the multiple types of quiz!");
                new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millis){}

                    @Override
                    public void onFinish(){
                        tvInfoDetails.setText("");
                    }
                }.start();
            }
        });

        btnInfoHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Click to view the highscores!");
                new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millis){}

                    @Override
                    public void onFinish(){
                        tvInfoDetails.setText("");
                    }
                }.start();
            }
        });

        btnInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Click to return to language selection!");
                new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millis){}

                    @Override
                    public void onFinish(){
                        tvInfoDetails.setText("");
                    }
                }.start();
            }
        });

        String language = getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE);
        tvMainMenuTitle.setText("Learning "+language);

    }
}
