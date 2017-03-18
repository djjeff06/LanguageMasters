package com.example.msi.languagemasterapp;

/**
 * Created by msi on 3/15/2017.
 */

public class Word {

    public static final String TABLE = "words";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CHARACTER = "character";
    public static final String COLUMN_PRONUNCIATION = "pronunciation";
    public static final String COLUMN_ENGLISH = "english";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_LEVEL = "level";
    public static final String CATEGORY_TRAITS = "trait";
    public static final String CATEGORY_FOOD = "food";

    private int id;
    private String character;
    private String pronunciation;
    private String english;
    private String category;
    private int level;

    public Word(){};

    public Word(int id, String character, String pronunciation, String english, String category, int level) {
        this.id = id;
        this.character = character;
        this.pronunciation = pronunciation;
        this.english = english;
        this.category = category;
        this.level = level;
    }

    public Word(String character, String pronunciation, String english, String category, int level) {
        this.character = character;
        this.pronunciation = pronunciation;
        this.english = english;
        this.category = category;
        this.level = level;
    }

    public String getCharacter() {
        return character;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getEnglish() {
        return english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
