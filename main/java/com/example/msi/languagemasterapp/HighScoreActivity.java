package com.example.msi.languagemasterapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by msi on 3/31/2017.
 */

public class HighScoreActivity extends Fragment {

    RecyclerView rvHighScore;
    HighScoreAdapter hsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.activity_high_score,container,false);

        rvHighScore = (RecyclerView) view.findViewById(R.id.rv_highscore);

        Cursor c = MainNavigation.dbHelper.getAllHighScore();
        hsAdapter = new HighScoreAdapter(getContext(),c);

        rvHighScore.setAdapter(hsAdapter);
        rvHighScore.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor c = MainNavigation.dbHelper.getAllHighScore();
        hsAdapter.changeCursor(c);
    }
}
