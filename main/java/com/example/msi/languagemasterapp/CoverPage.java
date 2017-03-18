package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoverPage extends AppCompatActivity {

    public static final String EXTRA_LANGUAGE = "chinese";

    Button btnChinese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_page);

        btnChinese = (Button) findViewById(R.id.btn_chinese);

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getBaseContext(),MainMenu.class);
                i.putExtra(EXTRA_LANGUAGE, "Chinese");
                startActivity(i);

            }
        });

    }
}
