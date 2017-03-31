package com.example.msi.languagemasterapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GestureDetector.OnGestureListener {

    public static DBHelper dbHelper;
    public static ArrayList<Integer> idList;
    private GestureDetectorCompat gestureDetectorCompat;
    public static SharedPreferences sp;
    private ScrollInterface scrollInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.gestureDetectorCompat = new GestureDetectorCompat(this,this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialize shared preference
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(!sp.contains(Word.COLUMN_WORDPHRASE)){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(Word.COLUMN_WORDPHRASE,Word.WORDPHRASE_WORD);
            editor.commit();
        }
        if(!sp.contains(Word.COLUMN_CATEGORY)){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(Word.COLUMN_CATEGORY,Word.CATEGORY_ALL);
            editor.commit();
        }
        if(!sp.contains(Word.COLUMN_DIFFICULTY)){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(Word.COLUMN_DIFFICULTY,Word.DIFFICULTY_EASY);
            editor.commit();
        }
        if(!sp.contains(Word.COLUMN_LANGUAGE)){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(Word.COLUMN_LANGUAGE,Word.LANGUAGE_CHINESE);
            editor.commit();
        }

        insertWords();
        if(idList.isEmpty())
            insertIds();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_fragment,new LearnActivity()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_learn) {
            fm.beginTransaction().replace(R.id.fl_fragment,new LearnActivity()).commit();
        } else if (id == R.id.nav_normal) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(HighScore.COLUMN_MODE,HighScore.QUIZMODE_NORMAL);
            editor.commit();
            fm.beginTransaction().replace(R.id.fl_fragment,new QuizFlashCard()).commit();
        } else if (id == R.id.nav_timed) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(HighScore.COLUMN_MODE,HighScore.QUIZMODE_TIMED);
            editor.commit();
            fm.beginTransaction().replace(R.id.fl_fragment,new QuizFlashCard()).commit();
        } else if (id == R.id.nav_survival) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(HighScore.COLUMN_MODE,HighScore.QUIZMODE_SURVIVAL);
            editor.commit();
            fm.beginTransaction().replace(R.id.fl_fragment,new QuizFlashCard()).commit();
        } else if (id == R.id.nav_highscore) {
            fm.beginTransaction().replace(R.id.fl_fragment,new HighScoreActivity()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void insertWords(){
        dbHelper = new DBHelper(getBaseContext());
        if(idList == null)
            idList = new ArrayList<>();
        Word word;
        word = new Word("芒果", "máng guǒ", "Mango", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("蘋果", "píng guǒ", "Apple", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("牛肉", "niú ròu", "Beef", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("牛奶", "niú nǎi", "Milk", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("雞蛋", "jī dàn", "Chicken Egg", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("麵包", "miàn bāo", "Bread", Word.LANGUAGE_CHINESE, Word.CATEGORY_FOOD, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("書包", "shū bāo", "Bag", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("手機", "shǒu jī", "Mobile Phone", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("電腦", "diàn nǎo", "Computer", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("衣服", "yī fu", "Clothes", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("褲子", "kù zi", "Pants", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);
        word = new Word("鞋子", "xié zi", "Shoes", Word.LANGUAGE_CHINESE, Word.CATEGORY_ITEM, Word.DIFFICULTY_EASY, Word.WORDPHRASE_WORD);
        if(checkWord(word))
            dbHelper.addWord(word);

    }

    public boolean checkWord(Word word){
        boolean check = true;
        Cursor cursor = dbHelper.getAllWords();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false && check == true)
        {
            if(cursor.getString(cursor.getColumnIndex(Word.COLUMN_ENGLISH)).contains(word.getEnglish()))
                check = false;
            cursor.moveToNext();
        }
        return check;
    }

    public void insertIds(){
        Cursor cursor = dbHelper.getAllWords();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            idList.add(cursor.getInt(cursor.getColumnIndex(Word.COLUMN_ID)));
            cursor.moveToNext();
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        scrollInterface.scrollCard();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        scrollInterface.scrollCard();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        scrollInterface.scrollCard();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrollInterface.scrollCard();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        scrollInterface.scrollCard();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrollInterface.scrollCard();
        return true;
    }

    public interface ScrollInterface{
        public void scrollCard();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
