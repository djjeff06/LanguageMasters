package com.example.msi.languagemasterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

import static com.example.msi.languagemasterapp.MainNavigation.sp;

public class NameHighScore extends Fragment {

    private TextView tvLabel, tvScore;
    private Button btnFinish;
    private EditText etName;
    private int score, spDifficulty, spCategory, spLanguage, spWordPhrase, spMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_name_high_score,container,false);

        tvLabel = (TextView) view.findViewById(R.id.tv_namehighscoretitle);
        tvScore = (TextView) view.findViewById(R.id.tv_newscore);
        btnFinish = (Button) view.findViewById(R.id.btn_finishhighscore);
        etName = (EditText) view.findViewById(R.id.et_newname);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HighScore highScore = new HighScore();
                highScore.setName(etName.getText().toString());
                highScore.setScore(score);
                highScore.setDifficulty(spDifficulty);
                highScore.setCategory(spCategory);
                highScore.setLanguage(spLanguage);
                highScore.setWordphrase(spWordPhrase);
                highScore.setMode(spMode);

                MainNavigation.dbHelper.addHighScore(highScore);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fl_fragment, new HighScoreActivity());
                ft.commit();

            }
        });

        spDifficulty = sp.getInt(Word.COLUMN_DIFFICULTY,-1);
        spCategory = sp.getInt(Word.COLUMN_CATEGORY,-1);
        spLanguage = sp.getInt(Word.COLUMN_LANGUAGE,-1);
        spWordPhrase = sp.getInt(Word.COLUMN_WORDPHRASE,-1);
        spMode = sp.getInt(HighScore.COLUMN_MODE, -1);
        score = sp.getInt(HighScore.COLUMN_SCORE, -1);
        tvScore.setText("Score: "+ score);

        return view;

    }

}
