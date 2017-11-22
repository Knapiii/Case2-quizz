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

import case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner mSpinnerCategories;
    QuizableOpenHelper mDbOpenHelper;
    private ListView mListViewHighscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mDbOpenHelper = new QuizableOpenHelper(this);


        loadCategoriesData();


        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) mSpinnerCategories.getItemAtPosition(position);

                String category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
                String category_id = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID));

                showToast(category);

                Cursor cursor1 = mDbOpenHelper.getHighScores(category_id);

                loadHighScoresData(cursor1);


                //loadHighScoresData(category_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadHighScoresData(Cursor cursor) {
//        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
//        String[] highscoresColumns = {
//                HighScoresInfoEntry.COLUMN_CATEGORY_ID,
//                HighScoresInfoEntry.COLUMN_HIGHSCORE,
//                HighScoresInfoEntry.COLUMN_USER_ID,
//                HighScoresInfoEntry._ID
//        };

        mListViewHighscores = findViewById(R.id.listView_highscores);

        //Cursor cursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoresColumns, null, null, null, null, HighScoresInfoEntry.COLUMN_CATEGORY_ID + " DESC");
        HighscoresAdapter highscoresAdapter = new HighscoresAdapter(this, cursor);

        mListViewHighscores.setAdapter(highscoresAdapter);



    }

    private void loadCategoriesData() {
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry._ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,
                null, null, null, null, CategoriesInfoEntry.COLUMN_CATEGORY_TITLE);

        mSpinnerCategories = findViewById(R.id.spinner);
        CategoriesCursorAdapter CategoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);

        mSpinnerCategories.setAdapter(CategoriesCursorAdapter);


    }


    private void showToast(String category) {
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

    }

    //This method adds a new category into the database category_info table

    private void insertCategory(String category_id, String category_text) {
        QuizableOpenHelper helper = new QuizableOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID, category_id);
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE, category_text);
        long id = db.insert(CategoriesInfoEntry.TABLE_NAME, null, contentValues);

        db.close();

    }


    //This method adds a new score into the database highscore_info table

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

}


// TODO: 2017-11-14 Lägg till:
// TODO: PLAY HISTORY
// TODO: STATISTIC FOR ALL CATEGIRES OR A SPESIFIC CAREGORY
// TODO: RANKING