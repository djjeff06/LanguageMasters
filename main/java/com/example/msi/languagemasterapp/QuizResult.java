package com.example.msi.languagemasterapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.msi.languagemasterapp.MainNavigation.sp;

public class QuizResult extends Fragment {

    TextView tvScore;
    Button btnFinish;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.activity_quiz_result,container,false);

        tvScore = (TextView) view.findViewById(R.id.tv_finalscore);
        btnFinish = (Button) view.findViewById(R.id.btn_gameoverfinish);

        int score = sp.getInt(HighScore.COLUMN_SCORE, -1);
        tvScore.setText("Your Score is "+score+"!");

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fl_fragment, new NameHighScore());
                ft.commit();
            }
        });

        return view;

    }

}
