package com.example.msi.languagemasterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by msi on 3/15/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String SCHEMA = "language";
    public static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;

        sql = "CREATE TABLE "+ Word.TABLE + " ( "+ Word.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Word.COLUMN_CHARACTER + " TEXT NOT NULL, "+ Word.COLUMN_PRONUNCIATION + " TEXT NOT NULL, "+ Word.COLUMN_ENGLISH + " TEXT NOT NULL, "
                + Word.COLUMN_LANGUAGE + " INTEGER NOT NULL, "+ Word.COLUMN_CATEGORY + " INTEGER NOT NULL, " + Word.COLUMN_DIFFICULTY + " INTEGER NOT NULL, "
                + Word.COLUMN_WORDPHRASE + " INTEGER NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE "+ HighScore.TABLE + " ( "+ HighScore.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                HighScore.COLUMN_NAME + " TEXT NOT NULL, "+ HighScore.COLUMN_SCORE + " INTEGER NOT NULL, "+
                HighScore.COLUMN_LANGUAGE + " INTEGER NOT NULL, "+HighScore.COLUMN_WORDPHRASE + " INTEGER NOT NULL, "+
                HighScore.COLUMN_CATEGORY + " INTEGER NOT NULL, "+ HighScore.COLUMN_DIFFICULTY + " INTEGER NOT NULL, "+
                HighScore.COLUMN_MODE + " INTEGER NOT NULL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql;

        sql = "DROP TABLE IF EXISTS "+ Word.TABLE+";";
        db.execSQL(sql);

        sql = "DROP TABLE IF EXISTS "+HighScore.TABLE+";";
        db.execSQL(sql);

        onCreate(db);
    }

    public Cursor getAllWords(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Word.TABLE, null, null, null, null, null, null);
    }

    public long addWord(Word word){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Word.COLUMN_CHARACTER, word.getCharacter());
        cv.put(Word.COLUMN_PRONUNCIATION, word.getPronunciation());
        cv.put(Word.COLUMN_ENGLISH, word.getEnglish());
        cv.put(Word.COLUMN_LANGUAGE, word.getLanguage());
        cv.put(Word.COLUMN_DIFFICULTY, word.getDifficulty());
        cv.put(Word.COLUMN_CATEGORY, word.getCategory());
        cv.put(Word.COLUMN_WORDPHRASE, word.getWordPhrase());
        long id = db.insert(Word.TABLE, null, cv);
        db.close();
        return id;
    }

    public long addHighScore(HighScore highScore){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HighScore.COLUMN_NAME, highScore.getName());
        cv.put(HighScore.COLUMN_SCORE, highScore.getScore());
        cv.put(HighScore.COLUMN_LANGUAGE, highScore.getLanguage());
        cv.put(HighScore.COLUMN_WORDPHRASE, highScore.getWordphrase());
        cv.put(HighScore.COLUMN_CATEGORY, highScore.getCategory());
        cv.put(HighScore.COLUMN_DIFFICULTY, highScore.getDifficulty());
        cv.put(HighScore.COLUMN_MODE, highScore.getMode());
        long id = db.insert(HighScore.TABLE, null, cv);
        db.close();
        return id;
    }

    public int updateHighScore(HighScore highScore){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HighScore.COLUMN_NAME, highScore.getName());
        cv.put(HighScore.COLUMN_SCORE, highScore.getScore());
        cv.put(HighScore.COLUMN_LANGUAGE, highScore.getLanguage());
        cv.put(HighScore.COLUMN_WORDPHRASE, highScore.getWordphrase());
        cv.put(HighScore.COLUMN_CATEGORY, highScore.getCategory());
        cv.put(HighScore.COLUMN_DIFFICULTY, highScore.getDifficulty());
        cv.put(HighScore.COLUMN_MODE, highScore.getMode());
        int rows = db.update(HighScore.TABLE, cv, HighScore.COLUMN_ID + "=? ", new String[]{highScore.getId()+""});
        db.close();
        return rows;
    }

    public int deleteHighScore(int id){
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(HighScore.TABLE, HighScore.COLUMN_ID + " =? ", new String[]{id+""});
        db.close();
        return rows;
    }

    public Word getWord(int id){
        Word word = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Word.TABLE, null, Word.COLUMN_ID + " =? ", new String[]{id+""}, null, null, null);
        if(cursor.moveToFirst()){
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
            word.setId(id);
        }
        db.close();
        cursor.close();
        return word;
    }

    public Cursor getAllHighScore(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(HighScore.TABLE, null, null, null, null, null, HighScore.COLUMN_SCORE+" DESC");
    }

    public HighScore getHighScore(int id){
        HighScore highScore = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(HighScore.TABLE, null, HighScore.COLUMN_ID + " =? ", new String[]{id+""}, null, null, null);
        if(cursor.moveToFirst()){
            highScore = new HighScore();
            String name = cursor.getString(cursor.getColumnIndex(HighScore.COLUMN_NAME));
            highScore.setName(name);
            int score = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_SCORE));
            highScore.setScore(score);
            int language = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_LANGUAGE));
            highScore.setLanguage(language);
            int wordPhrase = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_WORDPHRASE));
            highScore.setWordphrase(wordPhrase);
            int category = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_CATEGORY));
            highScore.setCategory(category);
            int difficulty = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_DIFFICULTY));
            highScore.setDifficulty(difficulty);
            int mode = cursor.getInt(cursor.getColumnIndex(HighScore.COLUMN_MODE));
            highScore.setMode(mode);
            highScore.setId(id);
        }
        db.close();
        cursor.close();
        return highScore;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + HighScore.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}
