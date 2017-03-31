package com.example.msi.languagemasterapp;

/**
 * Created by msi on 3/15/2017.
 */

public class HighScore {

    public static final String TABLE = "highscore";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_WORDPHRASE = "wordphrase";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_MODE = "mode";

    public static final int QUIZMODE_NORMAL = 0;
    public static final int QUIZMODE_TIMED = 1;
    public static final int QUIZMODE_SURVIVAL = 2;

    private int id;
    private String name;
    private int score;
    private int language;
    private int wordphrase;
    private int category;
    private int difficulty;
    private int mode;

    public HighScore(){}

    public HighScore(String name, int score, int language, int wordphrase, int category, int difficulty, int mode) {
        this.name = name;
        this.score = score;
        this.language = language;
        this.wordphrase = wordphrase;
        this.category = category;
        this.difficulty = difficulty;
        this.mode = mode;
    }

    public HighScore(int id, String name, int score, int language, int wordphrase, int category, int difficulty, int mode) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.language = language;
        this.wordphrase = wordphrase;
        this.category = category;
        this.difficulty = difficulty;
        this.mode = mode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWordphrase() {
        return wordphrase;
    }

    public void setWordphrase(int wordphrase) {
        this.wordphrase = wordphrase;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
