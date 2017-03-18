package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {

    TextView tvScore;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        tvScore = (TextView) findViewById(R.id.tv_finalscore);
        btnFinish = (Button) findViewById(R.id.btn_gameoverfinish);

        if(!getIntent().getStringExtra(QuizFlashCard.EXTRA_SCORE).isEmpty()){
            tvScore.setText("Your Score is "+getIntent().getStringExtra(QuizFlashCard.EXTRA_SCORE)+"!");
        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),NameHighScore.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                    i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                else
                    i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                i.putExtra(QuizMenu.EXTRA_MODE, getIntent().getStringExtra(QuizMenu.EXTRA_MODE));
                i.putExtra(QuizFlashCard.EXTRA_SCORE, getIntent().getStringExtra(QuizFlashCard.EXTRA_SCORE));
                startActivity(i);
            }
        });

    }
}
