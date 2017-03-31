package com.example.msi.languagemasterapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.msi.languagemasterapp.MainNavigation.sp;

/**
 * Created by msi on 3/31/2017.
 */

public class HighScoreAdapter extends CursorRecyclerViewAdapter<HighScoreAdapter.HSViewHolder>{

    private int spDifficulty, spCategory, spLanguage, spWordPhrase, spMode;

    public HighScoreAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(HSViewHolder viewHolder, Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(HighScore.COLUMN_NAME));
        int score = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_SCORE));
        int language = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_LANGUAGE));
        int difficulty = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_DIFFICULTY));
        int wordphrase = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_WORDPHRASE));
        int category = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_CATEGORY));
        int mode = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_MODE));

        if(wordphrase == spWordPhrase && difficulty == spDifficulty &&
                (category == spCategory || spCategory == Word.CATEGORY_ALL) &&
                language == spLanguage && mode == spMode) {
            viewHolder.tvHSName.setText(name);
            viewHolder.tvHSScore.setText(score+"");
        }

    }

    @Override
    public HSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_high_score, parent, false);

        spDifficulty = sp.getInt(Word.COLUMN_DIFFICULTY,-1);
        spCategory = sp.getInt(Word.COLUMN_CATEGORY,-1);
        spLanguage = sp.getInt(Word.COLUMN_LANGUAGE,-1);
        spWordPhrase = sp.getInt(Word.COLUMN_WORDPHRASE,-1);
        spMode = sp.getInt(HighScore.COLUMN_MODE,-1);

        return new HSViewHolder(v);

    }

    public class HSViewHolder extends RecyclerView.ViewHolder{

        TextView tvHSName, tvHSScore;

        public HSViewHolder(View itemView) {
            super(itemView);
            tvHSName = (TextView) itemView.findViewById(R.id.tv_hsname);
            tvHSScore = (TextView) itemView.findViewById(R.id.tv_hsscore);
        }
    }

}
