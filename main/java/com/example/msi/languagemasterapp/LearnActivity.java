package com.example.msi.languagemasterapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.msi.languagemasterapp.MainNavigation.sp;

public class LearnActivity extends Fragment implements MainNavigation.ScrollInterface{

    private TextView tvCharacter, tvPronunciation, tvEnglish;
    private ImageView ivBack, ivNext;
    private ScrollView svScroll;
    private boolean checker;
    private int spDifficulty, spLanguage,spCategory,spWordPhrase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_learn,container,false);

        tvCharacter = (TextView) view.findViewById(R.id.tv_learncharacter);
        tvPronunciation = (TextView) view.findViewById(R.id.tv_learnpronunciation);
        tvEnglish = (TextView) view.findViewById(R.id.tv_learnenglish);
        ivBack = (ImageView) view.findViewById(R.id.iv_learnprev);
        ivNext = (ImageView) view.findViewById(R.id.iv_learnnext);
        svScroll = (ScrollView) view.findViewById(R.id.sv_learnscroll);

        spDifficulty = sp.getInt(Word.COLUMN_DIFFICULTY,-1);
        spCategory = sp.getInt(Word.COLUMN_CATEGORY,-1);
        spLanguage = sp.getInt(Word.COLUMN_LANGUAGE,-1);
        spWordPhrase = sp.getInt(Word.COLUMN_WORDPHRASE,-1);

            if (!sp.contains(Word.LEARNCURRENTID)) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(Word.LEARNCURRENTID, MainNavigation.idList.get(0));
                editor.commit();
            }

            Word word = MainNavigation.dbHelper.getWord(sp.getInt(Word.LEARNCURRENTID, -1));
        if(word.getWordPhrase() == spWordPhrase && word.getDifficulty() == spDifficulty &&
                (word.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                word.getLanguage() == spLanguage) {
                tvCharacter.setText(word.getCharacter());
                tvPronunciation.setText(word.getPronunciation());
                tvEnglish.setText(word.getEnglish());
            }
            else{
                nextCard();
            }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevCard();
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCard();
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                    //do something
                    nextCard();
                }
                else if(event.getAction()==MotionEvent.ACTION_MOVE)
                    nextCard();
                return true;
            }
        });

        return view;
    }

    @Override
    public void scrollCard(){
        nextCard();
    }

    public boolean nextCard(){
        checker = false;
        int index = MainNavigation.idList.indexOf(sp.getInt(Word.LEARNCURRENTID,-1));
        if(index+1 < MainNavigation.idList.size())
            index++;
        else
            index = 0;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Word.LEARNCURRENTID, MainNavigation.idList.get(index));
        editor.commit();
        Word word = MainNavigation.dbHelper.getWord(sp.getInt(Word.LEARNCURRENTID,-1));
        if(word.getWordPhrase() == spWordPhrase && word.getDifficulty() == spDifficulty &&
                (word.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                word.getLanguage() == spLanguage) {
            tvCharacter.setText(word.getCharacter());
            tvPronunciation.setText(word.getPronunciation());
            tvEnglish.setText(word.getEnglish());
            return true;
        }
        else{
            int count = MainNavigation.idList.size();
            while(count>0 && !checker){
                if(nextCard()) {
                    checker = true;
                }
                else{
                    count--;
                }
            }
        }
        return false;
    }

    public boolean prevCard(){
        checker = false;
        int index = MainNavigation.idList.indexOf(sp.getInt(Word.LEARNCURRENTID,-1));
        if(index-1 >= 0)
            index--;
        else
            index = MainNavigation.idList.size()-1;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Word.LEARNCURRENTID, MainNavigation.idList.get(index));
        editor.commit();
        Word word = MainNavigation.dbHelper.getWord(sp.getInt(Word.LEARNCURRENTID,-1));
        if(word.getWordPhrase() == spWordPhrase && word.getDifficulty() == spDifficulty &&
                (word.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                word.getLanguage() == spLanguage) {
            tvCharacter.setText(word.getCharacter());
            tvPronunciation.setText(word.getPronunciation());
            tvEnglish.setText(word.getEnglish());
            return true;
        }
        else{
            int count = MainNavigation.idList.size();
            while(count>0 && !checker){
                if(prevCard()) {
                    checker = true;
                }
                else{
                    count--;
                }
            }
        }
        return false;
    }

}