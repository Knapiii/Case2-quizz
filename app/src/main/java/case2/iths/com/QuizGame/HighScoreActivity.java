package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.*;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    private String[] cats;
    private ArrayList<String> categories = new ArrayList<>();

 //   private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
   // inserted default data to database.
   // insertCategories();
   // insertHighscores();
        QuizableOpenHelper mDbOpenHelper = new QuizableOpenHelper(this);

        //fffff
        loadFromDatabase(mDbOpenHelper);

        spinner = findViewById(R.id.spinner);
     //   listView = findViewById(R.id.highscore_list);
       // cats = getResources().getStringArray(R.array.categories_array);
    //    names = getResources().getStringArray(R.array.top5);

        //Custom adapter:

        HighscoresAdapter highscoresAdapter = new HighscoresAdapter(this, categories);
    //    HighscoresAdapter highscoresAdapter1 = new HighscoresAdapter(this, names);

        spinner.setAdapter(highscoresAdapter);
    //    listView.setAdapter(highscoresAdapter1);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private void showToast(int i) {
        Toast.makeText(this, categories.get(i), Toast.LENGTH_SHORT).show();

    }


    public void insertCategories() {
        insertCategory("games", "Games");
        insertCategory("geography", "Geography");
        insertCategory("music", "Music");
        insertCategory("own_questions", "Own questions");
        insertCategory("sport", "Sport");
        insertCategory("science", "Science");
        insertCategory("test", "test");
    }


    private void insertCategory(String category_id, String category_text) {
        QuizableOpenHelper helper = new QuizableOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID, category_id );
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE, category_text);
        long id = db.insert(CategoriesInfoEntry.TABLE_NAME, null, contentValues);

        db.close();

    }


    public void insertHighscores() {
        insertHighscore("sport", 300, "daniel");
        insertHighscore("sport", 400, "alvar");
        insertHighscore("sport", 100, "kristoffer");
        insertHighscore("sport", 2200, "hanna");
        insertHighscore("sport", 5400, "hanna");
        insertHighscore("sport", 10, "ibrahim");
        insertHighscore("sport", 90000, "alvar");

    }



    private void insertHighscore(String category_id, int score, String user_id) {
        QuizableOpenHelper helper = new QuizableOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        contentValues.put(HighScoresInfoEntry.COLUMN_CATEGORY_ID, category_id);
        contentValues.put(HighScoresInfoEntry.COLUMN_HIGHSCORE, score);
        contentValues.put(HighScoresInfoEntry.COLUMN_USER_ID, user_id);

        long newRowId = db.insert(HighScoresInfoEntry.TABLE_NAME, null, contentValues);

        db.close();


    }

    //Takes data from highscore table in Quizable database.

    private void loadFromDatabase(QuizableOpenHelper dbHelper) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE};
        Cursor categoryCursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns, null, null, null, null, null);

        loadCategoriesFromDatabase(categoryCursor);
        //String[] highscoreColumns = {HighScoresInfoEntry.COLUMN_CATEGORY_ID, HighScoresInfoEntry.COLUMN_HIGHSCORE, HighScoresInfoEntry.COLUMN_USER_ID};
       //Cursor highscoreCursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoreColumns, null, null, null, null, null);


        db.close();
    }

    //This method add data from the database to our categories array-list
    private void loadCategoriesFromDatabase(Cursor cursor) {

        int categoryIdPos = cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID);
        int categoryTitlePos = cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE);

        while(cursor.moveToNext()) {
            String categoryId = cursor.getString(categoryIdPos);
            String categoryTitle = cursor.getString(categoryTitlePos);

            categories.add(categoryTitle);

        }

        cursor.close();
    }


    // TODO: 2017-11-14 Lägg till:
    // TODO: PLAY HISTORY
    // TODO: STATISTIC FOR ALL CATEGIRES OR A SPESIFIC CAREGORY
    // TODO: RANKING

}

