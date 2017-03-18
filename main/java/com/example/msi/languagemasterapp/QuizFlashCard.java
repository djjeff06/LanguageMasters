package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class QuizFlashCard extends AppCompatActivity {

    public static final String EXTRA_SCORE = "score";

    TextView tvQuizTitle, tvQuizCharacter, tvQuizPronunciation, tvLabel, tvScore;
    Button btnQuizA, btnQuizB, btnQuizC, btnQuizD, btnExit;

    private boolean life;
    private int normalCount, ranNum, score, turn;
    private Word currentWord;
    private ArrayList<Word> wordList;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_flash_card);

        tvQuizTitle = (TextView) findViewById(R.id.tv_quiztitle);
        tvQuizCharacter = (TextView) findViewById(R.id.tv_quizcharacter);
        tvQuizPronunciation = (TextView) findViewById(R.id.tv_quizpronunciation);
        tvLabel = (TextView) findViewById(R.id.tv_label);
        tvScore = (TextView)findViewById(R.id.tv_currentscore);
        btnQuizA = (Button) findViewById(R.id.btn_quiza);
        btnQuizB = (Button) findViewById(R.id.btn_quizb);
        btnQuizC = (Button) findViewById(R.id.btn_quizc);
        btnQuizD = (Button) findViewById(R.id.btn_quizd);
        btnExit = (Button) findViewById(R.id.btn_exitquiz);

        btnQuizA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizA.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizB.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizC.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizD.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), QuizMenu.class);
                startActivity(i);
            }
        });

        if(tvQuizTitle.getText().toString().contains("?")) {
            String mode = getIntent().getStringExtra(QuizMenu.EXTRA_MODE);
            tvQuizTitle.setText(mode + " Quiz");
        }

        switch(getIntent().getStringExtra(QuizMenu.EXTRA_MODE)) {

            case "Normal":
                normalCount = 20;
                break;

            case "Survival":
                life = true;
                break;

            case "Timed":
                new CountDownTimer(30 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvLabel.setText("Time: " + millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        Intent i = new Intent(getBaseContext(), QuizResult.class);
                        i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                        i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                        i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                        i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                        if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                            i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                        else
                            i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                        i.putExtra(QuizMenu.EXTRA_MODE, getIntent().getStringExtra(QuizMenu.EXTRA_MODE));
                        i.putExtra(QuizFlashCard.EXTRA_SCORE, Integer.toString(score));
                        startActivity(i);
                    }
                }.start();
                break;

        }

        //for beta only
        wordList = new ArrayList<>();
        initList();
        currentWord = wordList.get(0);

        tvQuizCharacter.setText(currentWord.getCharacter());
        tvQuizPronunciation.setText(currentWord.getPronunciation());
        btnQuizA.setText(currentWord.getEnglish());
        btnQuizB.setText("Integrity");
        btnQuizC.setText("Bravery");
        btnQuizD.setText("Ambition");
        if(getIntent().getStringExtra(QuizMenu.EXTRA_MODE).equals("Normal")) {
            turn = 1;
            tvLabel.setText("Turn " + turn);
        }
        random = new Random();
        score = 0;

    }

    //for beta only
    public void initList(){

        wordList.add(new Word("忠","zhong","Loyalty",null,0));
        wordList.add(new Word("贪","tan","Greed",null,0));

    }

    public void executeButton(){

        switch(getIntent().getStringExtra(QuizMenu.EXTRA_MODE)){

            case "Normal":
                if(normalCount>1) {
                    ranNum = random.nextInt(2);
                    currentWord = wordList.get(ranNum);
                    tvQuizCharacter.setText(currentWord.getCharacter());
                    tvQuizPronunciation.setText(currentWord.getPronunciation());

                    ranNum = random.nextInt(4);
                    switch (ranNum) {
                        case 0:
                            btnQuizA.setText(currentWord.getEnglish());
                            btnQuizB.setText("Integrity");
                            btnQuizC.setText("Bravery");
                            btnQuizD.setText("Ambition");
                            break;
                        case 1:
                            btnQuizA.setText("Integrity");
                            btnQuizB.setText(currentWord.getEnglish());
                            btnQuizC.setText("Ambition");
                            btnQuizD.setText("Bravery");
                            break;
                        case 2:
                            btnQuizA.setText("Ambition");
                            btnQuizB.setText("Bravery");
                            btnQuizC.setText(currentWord.getEnglish());
                            btnQuizD.setText("Integrity");
                            break;
                        case 3:
                            btnQuizA.setText("Bravery");
                            btnQuizB.setText("Ambition");
                            btnQuizC.setText("Integrity");
                            btnQuizD.setText(currentWord.getEnglish());
                            break;
                    }

                    turn++;
                    tvLabel.setText("Turn: "+turn);
                    normalCount--;
                }
                else{
                    Intent i = new Intent(getBaseContext(), QuizResult.class);
                    i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                    i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                        i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                    else
                        i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                    i.putExtra(QuizMenu.EXTRA_MODE, getIntent().getStringExtra(QuizMenu.EXTRA_MODE));
                    i.putExtra(EXTRA_SCORE,Integer.toString(score));
                    startActivity(i);
                }
                break;

            case "Survival":
                if(life) {
                    ranNum = random.nextInt(2);
                    currentWord = wordList.get(ranNum);
                    tvQuizCharacter.setText(currentWord.getCharacter());
                    tvQuizPronunciation.setText(currentWord.getPronunciation());

                    ranNum = random.nextInt(4);
                    switch (ranNum) {
                        case 0:
                            btnQuizA.setText(currentWord.getEnglish());
                            btnQuizB.setText("Integrity");
                            btnQuizC.setText("Bravery");
                            btnQuizD.setText("Ambition");
                            break;
                        case 1:
                            btnQuizA.setText("Integrity");
                            btnQuizB.setText(currentWord.getEnglish());
                            btnQuizC.setText("Ambition");
                            btnQuizD.setText("Bravery");
                            break;
                        case 2:
                            btnQuizA.setText("Ambition");
                            btnQuizB.setText("Bravery");
                            btnQuizC.setText(currentWord.getEnglish());
                            btnQuizD.setText("Integrity");
                            break;
                        case 3:
                            btnQuizA.setText("Bravery");
                            btnQuizB.setText("Ambition");
                            btnQuizC.setText("Integrity");
                            btnQuizD.setText(currentWord.getEnglish());
                            break;
                    }

                }
                else{
                    Intent i = new Intent(getBaseContext(), QuizResult.class);
                    i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                    i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                        i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                    else
                        i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                    i.putExtra(QuizMenu.EXTRA_MODE, getIntent().getStringExtra(QuizMenu.EXTRA_MODE));
                    i.putExtra(EXTRA_SCORE,Integer.toString(score));
                    startActivity(i);
                }
                break;

            case "Timed":
                ranNum = random.nextInt(2);
                currentWord = wordList.get(ranNum);
                tvQuizCharacter.setText(currentWord.getCharacter());
                tvQuizPronunciation.setText(currentWord.getPronunciation());

                ranNum = random.nextInt(4);
                switch (ranNum) {
                    case 0:
                        btnQuizA.setText(currentWord.getEnglish());
                        btnQuizB.setText("Integrity");
                        btnQuizC.setText("Bravery");
                        btnQuizD.setText("Ambition");
                        break;
                    case 1:
                        btnQuizA.setText("Integrity");
                        btnQuizB.setText(currentWord.getEnglish());
                        btnQuizC.setText("Ambition");
                        btnQuizD.setText("Bravery");
                        break;
                    case 2:
                        btnQuizA.setText("Ambition");
                        btnQuizB.setText("Bravery");
                        btnQuizC.setText(currentWord.getEnglish());
                        btnQuizD.setText("Integrity");
                        break;
                    case 3:
                        btnQuizA.setText("Bravery");
                        btnQuizB.setText("Ambition");
                        btnQuizC.setText("Integrity");
                        btnQuizD.setText(currentWord.getEnglish());
                        break;
                }
                break;

        }

    }

}
