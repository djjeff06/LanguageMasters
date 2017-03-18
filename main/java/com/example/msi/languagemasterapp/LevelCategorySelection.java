package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LevelCategorySelection extends AppCompatActivity {

    public static final String EXTRA_LEVELCATEGORY = "levelcategory";

    TextView tvInfoDetails;
    Button btnLevel, btnCategory, btnBack;
    ImageButton btnInfoLevel, btnInfoCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_category_selection);

        tvInfoDetails = (TextView) findViewById(R.id.tv_infolevelcategory);
        btnLevel = (Button) findViewById(R.id.btn_levelquiz);
        btnCategory = (Button) findViewById(R.id.btn_categoryquiz);
        btnBack = (Button) findViewById(R.id.btn_backlevelcategory);
        btnInfoLevel = (ImageButton) findViewById(R.id.btn_infolevelquiz);
        btnInfoCategory = (ImageButton) findViewById(R.id.btn_infocategoryquiz);

        btnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),LevelSelection.class);
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(EXTRA_LEVELCATEGORY,"level");
                startActivity(i);
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(),CategorySelection.class);
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                i.putExtra(WordPhraseSelection.EXTRA_WORDPHRASE, getIntent().getStringExtra(WordPhraseSelection.EXTRA_WORDPHRASE));
                i.putExtra(EXTRA_LEVELCATEGORY,"category");
                startActivity(i);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), WordPhraseSelection.class);
                i.putExtra(CoverPage.EXTRA_LANGUAGE,getIntent().getStringExtra(CoverPage.EXTRA_LANGUAGE));
                i.putExtra(MainMenu.EXTRA_MAIN,getIntent().getStringExtra(MainMenu.EXTRA_MAIN));
                startActivity(i);
            }
        });

        btnInfoLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvInfoDetails.setText("Easy to hard levels!");
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

        btnInfoCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvInfoDetails.setText("Choose from your favorite categories!");
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
