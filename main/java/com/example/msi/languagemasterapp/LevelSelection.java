package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelection extends AppCompatActivity {

    public static final String EXTRA_LEVEL = "level";

    Button btnLevel1, btnLevel2, btnLevel3, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        btnLevel1 = (Button) findViewById(R.id.btn_level1);
        btnLevel2 = (Button) findViewById(R.id.btn_level2);
        btnLevel3 = (Button) findViewById(R.id.btn_level3);
        btnBack = (Button) findViewById(R.id.btn_backlevel);

        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE,getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_LEVEL,"1");
                    startActivity(i);
                }

            }
        });

        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE,getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_LEVEL,"2");
                    startActivity(i);
                }

            }
        });

        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE,getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY,getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_LEVEL,"3");
                    startActivity(i);
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),LevelCategorySelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                startActivity(i);
            }
        });

    }
}
