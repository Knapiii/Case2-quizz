package case2.iths.com.QuizGame;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner mSpinnerCategories;
    QuizableOpenHelper mDbOpenHelper;
    private ListView mListViewHighscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mDbOpenHelper = new QuizableOpenHelper(this);


        loadCategoriesSpinner();


    }

    private void loadCategoriesSpinner() {

        mSpinnerCategories = findViewById(R.id.spinner);
        Cursor cursor = mDbOpenHelper.loadCategoriesData();
        CategoriesCursorAdapter CategoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);
        mSpinnerCategories.setAdapter(CategoriesCursorAdapter);

        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) mSpinnerCategories.getItemAtPosition(position);

                String category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
                String category_id = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID));

                showToast(category);

                Cursor highScores = mDbOpenHelper.getHighScores(category_id);

                loadHighScoresData(highScores);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadHighScoresData(Cursor cursor) {

        mListViewHighscores = findViewById(R.id.listView_highscores);
        HighscoresAdapter highscoresAdapter = new HighscoresAdapter(this, cursor);

        mListViewHighscores.setAdapter(highscoresAdapter);

    }

    private void showToast(String category) {
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {

        mDbOpenHelper.close();
        super.onDestroy();
    }
}


// TODO: 2017-11-14 Lägg till:
// TODO: PLAY HISTORY
// TODO: STATISTIC FOR ALL CATEGIRES OR A SPESIFIC CAREGORY
// TODO: RANKING