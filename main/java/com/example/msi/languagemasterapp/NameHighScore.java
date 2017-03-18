package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NameHighScore extends AppCompatActivity {

    TextView tvLabel, tvScore;
    Button btnFinish;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_high_score);

        tvLabel = (TextView) findViewById(R.id.tv_namehighscoretitle);
        tvScore = (TextView) findViewById(R.id.tv_newscore);
        btnFinish = (Button) findViewById(R.id.btn_finishhighscore);
        etName = (EditText) findViewById(R.id.et_newname);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),QuizMenu.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                if(getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY).equals("level"))
                    i.putExtra(LevelSelection.EXTRA_LEVEL,getIntent().getStringExtra(LevelSelection.EXTRA_LEVEL));
                else
                    i.putExtra(CategorySelection.EXTRA_CATEGORY,getIntent().getStringExtra(CategorySelection.EXTRA_CATEGORY));
                startActivity(i);
            }
        });

        tvScore.setText("Score: "+getIntent().getStringExtra(QuizFlashCard.EXTRA_SCORE));

    }
}
