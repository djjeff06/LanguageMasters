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
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_WORDPHRASE = "wordphrase";

    public static final int CATEGORY_ALL = 0;
    public static final int CATEGORY_FOOD = 1;
    public static final int CATEGORY_ITEM = 2;
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_HARD = 1;
    public static final int LANGUAGE_CHINESE = 0;
    public static final int LANGUAGE_JAPANESE = 1;
    public static final int WORDPHRASE_WORD = 0;
    public static final int WORDPHRASE_PHRASE = 1;
    public static final String LEARNCURRENTID = "learncurrentid";

    private int id;
    private String character;
    private String pronunciation;
    private String english;
    private int language;
    private int category;
    private int difficulty;
    private int wordPhrase;

    public Word(){};

    public Word(String character, String pronunciation, String english, int language, int category, int difficulty, int wordPhrase) {
        this.character = character;
        this.pronunciation = pronunciation;
        this.english = english;
        this.language = language;
        this.category = category;
        this.difficulty = difficulty;
        this.wordPhrase = wordPhrase;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getWordPhrase() {
        return wordPhrase;
    }

    public void setWordPhrase(int wordPhrase) {
        this.wordPhrase = wordPhrase;
    }
}
