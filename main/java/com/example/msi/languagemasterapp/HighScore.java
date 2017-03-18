package com.example.msi.languagemasterapp;

/**
 * Created by msi on 3/15/2017.
 */

public class HighScore {

    public static final String TABLE = "highscore";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_WORDPHRASE = "wordphrase";
    public static final String COLUMN_LEVELCATEGORY = "levelCategory";
    public static final String COLUMN_LEVELCATEGORYNUM = "levelCategoryNum";

    //wordphrase = normal, survival, timed
    //levelCategory = levels, category
    //levelCategoryNum = level1, level2, food, greetings, level3
    private int id;
    private String name;
    private int score;
    private String wordphrase;
    private String levelCategory;
    private String levelCategoryNum;

    public HighScore(){}

    public HighScore(String name, int score, String wordphrase, String levelCategory, String levelCategoryNum) {
        this.name = name;
        this.score = score;
        this.wordphrase = wordphrase;
        this.levelCategory = levelCategory;
        this.levelCategoryNum = levelCategoryNum;
    }

    public HighScore(int id, String name, int score, String wordphrase, String levelCategory, String levelCategoryNum) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.wordphrase = wordphrase;
        this.levelCategory = levelCategory;
        this.levelCategoryNum = levelCategoryNum;
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

    public String getWordphrase() {
        return wordphrase;
    }

    public void setWordphrase(String wordphrase) {
        this.wordphrase = wordphrase;
    }

    public String getLevelCategory() {
        return levelCategory;
    }

    public void setLevelCategory(String levelCategory) {
        this.levelCategory = levelCategory;
    }

    public String getLevelCategoryNum() {
        return levelCategoryNum;
    }

    public void setLevelCategoryNum(String levelCategoryNum) {
        this.levelCategoryNum = levelCategoryNum;
    }
}
