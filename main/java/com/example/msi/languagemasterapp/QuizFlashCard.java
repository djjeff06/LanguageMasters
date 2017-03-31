package com.example.msi.languagemasterapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.msi.languagemasterapp.MainNavigation.sp;

public class QuizFlashCard extends Fragment {

    TextView tvQuizTitle, tvQuizCharacter, tvQuizPronunciation, tvLabel, tvScore;
    Button btnQuizA, btnQuizB, btnQuizC, btnQuizD;

    private boolean life, checker;
    private int normalCount, ranNum, score, turn, id1,id2,id3, spWordPhrase, spLanguage, spDifficulty, spCategory;
    private Word currentWord, tempWord, word;
    private ArrayList<Word> wordList, wordBackup;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.activity_quiz_flash_card,container,false);

        tvQuizTitle = (TextView) view.findViewById(R.id.tv_quiztitle);
        tvQuizCharacter = (TextView) view.findViewById(R.id.tv_quizcharacter);
        tvQuizPronunciation = (TextView) view.findViewById(R.id.tv_quizpronunciation);
        tvLabel = (TextView) view.findViewById(R.id.tv_label);
        tvScore = (TextView) view.findViewById(R.id.tv_currentscore);
        btnQuizA = (Button) view.findViewById(R.id.btn_quiza);
        btnQuizB = (Button) view.findViewById(R.id.btn_quizb);
        btnQuizC = (Button) view.findViewById(R.id.btn_quizc);
        btnQuizD = (Button) view.findViewById(R.id.btn_quizd);

        random = new Random();

        btnQuizA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizA.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizB.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizC.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        btnQuizD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentWord.getEnglish().equals(btnQuizD.getText().toString())) {
                    score++;
                    tvScore.setText("Score: "+score);
                }
                else
                    life = false;
                executeButton();

            }
        });

        if(tvQuizTitle.getText().toString().contains("?")) {

            int quizmode = sp.getInt(HighScore.COLUMN_MODE, -1);
            switch(quizmode){
                case HighScore.QUIZMODE_NORMAL:
                    tvQuizTitle.setText("Normal Quiz");
                    break;
                case HighScore.QUIZMODE_TIMED:
                    tvQuizTitle.setText("Timed Quiz");
                    break;
                case HighScore.QUIZMODE_SURVIVAL:
                    tvQuizTitle.setText("Survival Quiz");
                    break;
            }

        }

        //use shared preference to search for game mode
        int quizmode = sp.getInt(HighScore.COLUMN_MODE, -1);
        switch(quizmode) {

            case HighScore.QUIZMODE_NORMAL:
                normalCount = 10;
                break;

            case HighScore.QUIZMODE_SURVIVAL:
                life = true;
                break;

            case HighScore.QUIZMODE_TIMED:
                new CountDownTimer(30 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvLabel.setText("Time: " + millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt(HighScore.COLUMN_SCORE, score);
                        editor.commit();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fl_fragment, new QuizResult());
                        ft.commit();

                    }
                }.start();
                break;

        }

        if(wordList == null )
            wordList = new ArrayList<>();
        else if(!wordList.isEmpty())
            wordList.clear();
        if(wordBackup == null)
            wordBackup = new ArrayList<>();
        else if(!wordBackup.isEmpty())
            wordBackup.clear();

        Cursor cursor = MainNavigation.dbHelper.getAllWords();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            word = new Word();
            String character = cursor.getString(cursor.getColumnIndex(Word.COLUMN_CHARACTER));
            word.setCharacter(character);
            String pronunciation = cursor.getString(cursor.getColumnIndex(Word.COLUMN_PRONUNCIATION));
            word.setPronunciation(pronunciation);
            String english = cursor.getString(cursor.getColumnIndex(Word.COLUMN_ENGLISH));
            word.setEnglish(english);
            int language = cursor.getInt(cursor.getColumnIndex(Word.COLUMN_LANGUAGE));
            word.setLanguage(language);
            int category = cursor.getInt(cursor.getColumnIndex(Word.COLUMN_CATEGORY));
            word.setCategory(category);
            int difficulty = cursor.getInt(cursor.getColumnIndex(Word.COLUMN_DIFFICULTY));
            word.setDifficulty(difficulty);
            int wordPhrase = cursor.getInt(cursor.getColumnIndex(Word.COLUMN_WORDPHRASE));
            word.setWordPhrase(wordPhrase);
            int id = cursor.getInt(cursor.getColumnIndex(Word.COLUMN_ID));
            word.setId(id);
            wordList.add(word);
            wordBackup.add(word);
            cursor.moveToNext();
        }

        spDifficulty = sp.getInt(Word.COLUMN_DIFFICULTY,-1);
        spCategory = sp.getInt(Word.COLUMN_CATEGORY,-1);
        spLanguage = sp.getInt(Word.COLUMN_LANGUAGE,-1);
        spWordPhrase = sp.getInt(Word.COLUMN_WORDPHRASE,-1);

        checker = false;
        while(!checker){
            ranNum = random.nextInt(wordList.size());
            currentWord = wordList.get(ranNum);
            if(currentWord.getWordPhrase() == spWordPhrase && currentWord.getDifficulty() == spDifficulty &&
                (currentWord.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                currentWord.getLanguage() == spLanguage) {
                checker = true;
                wordList.remove(ranNum);
                tvQuizCharacter.setText(currentWord.getCharacter());
                tvQuizPronunciation.setText(currentWord.getPronunciation());
                ranNum = random.nextInt(4);
                switch (ranNum) {
                    case 0:
                        btnQuizA.setText(currentWord.getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id1 = wordBackup.get(ranNum).getId();
                        } while (id1 == currentWord.getId());
                        btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id2 = wordBackup.get(ranNum).getId();
                        } while (id2 == currentWord.getId() || id2 == id1);
                        btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id3 = wordBackup.get(ranNum).getId();
                        } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                        btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                        break;
                    case 1:
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id1 = wordBackup.get(ranNum).getId();
                        } while (id1 == currentWord.getId());
                        btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                        btnQuizB.setText(currentWord.getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id2 = wordBackup.get(ranNum).getId();
                        } while (id2 == currentWord.getId() || id2 == id1);
                        btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id3 = wordBackup.get(ranNum).getId();
                        } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                        btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                        break;
                    case 2:
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id1 = wordBackup.get(ranNum).getId();
                        } while (id1 == currentWord.getId());
                        btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id2 = wordBackup.get(ranNum).getId();
                        } while (id2 == currentWord.getId() || id2 == id1);
                        btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                        btnQuizC.setText(currentWord.getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id3 = wordBackup.get(ranNum).getId();
                        } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                        btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                        break;
                    case 3:
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id1 = wordBackup.get(ranNum).getId();
                        } while (id1 == currentWord.getId());
                        btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id2 = wordBackup.get(ranNum).getId();
                        } while (id2 == currentWord.getId() || id2 == id1);
                        btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                        do {
                            ranNum = random.nextInt(wordBackup.size());
                            id3 = wordBackup.get(ranNum).getId();
                        } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                        btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                        btnQuizD.setText(currentWord.getEnglish());
                        break;
                }
            }
        }

        turn = 1;
        tvLabel.setText("Turn " + turn);

        random = new Random();
        score = 0;

        return view;

    }

    public void executeButton(){

        int quizmode = sp.getInt(HighScore.COLUMN_MODE, -1);
        switch(quizmode){

            case HighScore.QUIZMODE_NORMAL:
                if(normalCount>1) {
                    checker = false;
                    while(!checker){
                        ranNum = random.nextInt(wordList.size());
                        currentWord = wordList.get(ranNum);
                        if(currentWord.getWordPhrase() == spWordPhrase && currentWord.getDifficulty() == spDifficulty &&
                                (currentWord.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                                currentWord.getLanguage() == spLanguage) {
                            checker = true;
                            wordList.remove(ranNum);
                            tvQuizCharacter.setText(currentWord.getCharacter());
                            tvQuizPronunciation.setText(currentWord.getPronunciation());
                            ranNum = random.nextInt(4);
                            switch (ranNum) {
                                case 0:
                                    btnQuizA.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 1:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizB.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 2:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizC.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 3:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizD.setText(currentWord.getEnglish());
                                    break;
                            }
                        }
                    }

                    turn++;
                    tvLabel.setText("Turn: "+turn);
                    normalCount--;
                }
                else{
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt(HighScore.COLUMN_SCORE, score);
                    editor.commit();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fl_fragment, new QuizResult());
                    ft.commit();
                }
                break;

            case HighScore.QUIZMODE_SURVIVAL:
                if(life) {
                    checker = false;
                    while(!checker) {
                        ranNum = random.nextInt(wordList.size());
                        currentWord = wordList.get(ranNum);
                        if(currentWord.getWordPhrase() == spWordPhrase && currentWord.getDifficulty() == spDifficulty &&
                                (currentWord.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                                currentWord.getLanguage() == spLanguage) {
                            checker = true;
                            wordList.remove(ranNum);
                            tvQuizCharacter.setText(currentWord.getCharacter());
                            tvQuizPronunciation.setText(currentWord.getPronunciation());
                            ranNum = random.nextInt(4);
                            switch (ranNum) {
                                case 0:
                                    btnQuizA.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 1:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizB.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 2:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizC.setText(currentWord.getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                    break;
                                case 3:
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id1 = wordBackup.get(ranNum).getId();
                                    } while (id1 == currentWord.getId());
                                    btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id2 = wordBackup.get(ranNum).getId();
                                    } while (id2 == currentWord.getId() || id2 == id1);
                                    btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                    do {
                                        ranNum = random.nextInt(wordBackup.size());
                                        id3 = wordBackup.get(ranNum).getId();
                                    } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                    btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                    btnQuizD.setText(currentWord.getEnglish());
                                    break;
                            }
                        }
                    }
                }
                else{
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt(HighScore.COLUMN_SCORE, score);
                    editor.commit();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fl_fragment, new QuizResult());
                    ft.commit();
                }
                break;

            case HighScore.QUIZMODE_TIMED:
                checker = false;
                while(!checker) {
                    ranNum = random.nextInt(wordList.size());
                    currentWord = wordList.get(ranNum);
                    if(currentWord.getWordPhrase() == spWordPhrase && currentWord.getDifficulty() == spDifficulty &&
                            (currentWord.getCategory() == spCategory || spCategory == Word.CATEGORY_ALL) &&
                            currentWord.getLanguage() == spLanguage) {
                        checker = true;
                        wordList.remove(ranNum);
                        tvQuizCharacter.setText(currentWord.getCharacter());
                        tvQuizPronunciation.setText(currentWord.getPronunciation());
                        ranNum = random.nextInt(4);
                        switch (ranNum) {
                            case 0:
                                btnQuizA.setText(currentWord.getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id1 = wordBackup.get(ranNum).getId();
                                } while (id1 == currentWord.getId());
                                btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id2 = wordBackup.get(ranNum).getId();
                                } while (id2 == currentWord.getId() || id2 == id1);
                                btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id3 = wordBackup.get(ranNum).getId();
                                } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                break;
                            case 1:
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id1 = wordBackup.get(ranNum).getId();
                                } while (id1 == currentWord.getId());
                                btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                btnQuizB.setText(currentWord.getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id2 = wordBackup.get(ranNum).getId();
                                } while (id2 == currentWord.getId() || id2 == id1);
                                btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id3 = wordBackup.get(ranNum).getId();
                                } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                break;
                            case 2:
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id1 = wordBackup.get(ranNum).getId();
                                } while (id1 == currentWord.getId());
                                btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id2 = wordBackup.get(ranNum).getId();
                                } while (id2 == currentWord.getId() || id2 == id1);
                                btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                btnQuizC.setText(currentWord.getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id3 = wordBackup.get(ranNum).getId();
                                } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                btnQuizD.setText(wordBackup.get(ranNum).getEnglish());
                                break;
                            case 3:
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id1 = wordBackup.get(ranNum).getId();
                                } while (id1 == currentWord.getId());
                                btnQuizA.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id2 = wordBackup.get(ranNum).getId();
                                } while (id2 == currentWord.getId() || id2 == id1);
                                btnQuizB.setText(wordBackup.get(ranNum).getEnglish());
                                do {
                                    ranNum = random.nextInt(wordBackup.size());
                                    id3 = wordBackup.get(ranNum).getId();
                                } while (id3 == currentWord.getId() || id3 == id1 || id3 == id2);
                                btnQuizC.setText(wordBackup.get(ranNum).getEnglish());
                                btnQuizD.setText(currentWord.getEnglish());
                                break;
                        }
                    }
                }
                break;

        }

    }

}
