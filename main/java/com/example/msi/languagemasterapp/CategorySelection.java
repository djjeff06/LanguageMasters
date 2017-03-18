package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.logging.Level;

public class CategorySelection extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "category";

    Button btnCategory1, btnCategory2, btnCategory3, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        btnCategory1 = (Button) findViewById(R.id.btn_category1);
        btnCategory2 = (Button) findViewById(R.id.btn_category2);
        btnCategory3 = (Button) findViewById(R.id.btn_category3);
        btnBack = (Button) findViewById(R.id.btn_backcategory);

        btnCategory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(MainMenu.EXTRA_MAIN, getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY, getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_CATEGORY, btnCategory1.getText());
                    startActivity(i);
                }
            }
        });

        btnCategory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(MainMenu.EXTRA_MAIN, getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY, getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_CATEGORY, btnCategory2.getText());
                    startActivity(i);
                }
            }
        });

        btnCategory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra(MainMenu.EXTRA_MAIN).equals("quiz")) {
                    Intent i = new Intent(getBaseContext(), QuizMenu.class);
                    i.putExtra(MainMenu.EXTRA_MAIN, getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                    i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                    i.putExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY, getIntent().getStringExtra(LevelCategorySelection.EXTRA_LEVELCATEGORY));
                    i.putExtra(EXTRA_CATEGORY, btnCategory3.getText());
                    startActivity(i);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(),LevelCategorySelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN, getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                startActivity(i);

            }
        });

        if(getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE).equals("word")){
            btnCategory1.setText("Food");
            btnCategory2.setText("Numbers");
            btnCategory3.setText("People");
        }

        else{
            btnCategory1.setText("Greetings");
            btnCategory2.setText("Directions");
            btnCategory3.setText("Others");
        }

    }
}
