package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuizMenu extends AppCompatActivity {

    public static final String EXTRA_MODE = "quiz mode";

    TextView tvInfoDetails;
    Button btnNormal, btnSurvival, btnTimed, btnBack;
    ImageButton btnInfoNormal, btnInfoSurvival, btnInfoTimed, btnInfoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        tvInfoDetails = (TextView) findViewById(R.id.tv_infoquizmodedetails);

        btnNormal = (Button) findViewById(R.id.btn_normalquiz);
        btnSurvival = (Button) findViewById(R.id.btn_survivalquiz);
        btnTimed = (Button) findViewById(R.id.btn_timedquiz);
        btnBack = (Button) findViewById(R.id.btn_returnmainfromquiz);

        btnInfoNormal = (ImageButton) findViewById(R.id.btn_infonormal);
        btnInfoSurvival = (ImageButton) findViewById(R.id.btn_infosurvival);
        btnInfoTimed = (ImageButton) findViewById(R.id.btn_infotimed);
        btnInfoBack = (ImageButton) findViewById(R.id.btn_inforeturnmainfromquiz);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), QuizFlashCard.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                    i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                else
                    i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                i.putExtra(EXTRA_MODE, "Normal");
                startActivity(i);

            }
        });

        btnSurvival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), QuizFlashCard.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                    i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                else
                    i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                i.putExtra(EXTRA_MODE, "Survival");
                startActivity(i);

            }
        });

        btnTimed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(), QuizFlashCard.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                    i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                else
                    i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                i.putExtra(EXTRA_MODE, "Timed");
                startActivity(i);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level")) {
                    Intent i = new Intent(getBaseContext(), LevelSelection.class);
                    i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                    i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(getBaseContext(), CategorySelection.class);
                    i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                    i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    startActivity(i);
                }
            }
        });

        btnInfoNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Test yourself with a set of 20 words!");
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

        btnInfoSurvival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("One mistake means game over! See how many words you can get right!");
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

        btnInfoTimed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvInfoDetails.setText("Time is ticking! Get as many correct answers as possible before the time is up!");
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
                tvInfoDetails.setText("Click to return to main menu!");
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

    }
}
