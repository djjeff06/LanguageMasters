package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Level;

public class WordPhraseSelection extends AppCompatActivity {

    public static final String EXTRA_WORDPHRASE = "wordphrase";

    TextView tvTitle;
    Button btnWord, btnPhrase, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_phrase_selection);

        tvTitle = (TextView) findViewById(R.id.tv_wordphrasetitle);
        btnWord = (Button) findViewById(R.id.btn_wordselection);
        btnPhrase = (Button) findViewById(R.id.btn_phraseselection);
        btnBack = (Button) findViewById(R.id.btn_backwordphrase);

        btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),LevelCategorySelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(EXTRA_WORDPHRASE, "word");
                startActivity(i);
            }
        });

        btnPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), LevelCategorySelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(EXTRA_WORDPHRASE, "phrase");
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),MainMenu.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                startActivity(i);
            }
        });

    }
}
