package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner mSpinnerCategories;
    private QuizableOpenHelper mDbOpenHelper;
    private RecyclerView recyclerView;
    private LinearLayoutManager highscoresLayoutManager;
    private String category_id;
    private String category;
    private HighscoresAdapter highscoresAdapter;
    private Cursor highScores;
    private int categoryPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mDbOpenHelper = new QuizableOpenHelper(this);

        Intent intent = getIntent();
        categoryPosition = intent.getIntExtra("categoryPos", 0);
        displayCategoriesSpinner();




    }

    private void loadHighscores() {

    displayHighScoresByCategory(highScores);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadHighscores();
    }

    private void displayCategoriesSpinner() {

        mSpinnerCategories = findViewById(R.id.spinner);
        Cursor cursor = mDbOpenHelper.loadCategoriesData();
        CategoriesCursorAdapter categoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);
        mSpinnerCategories.setAdapter(categoriesCursorAdapter);

        if(categoryPosition > -1)
            mSpinnerCategories.setSelection(categoryPosition);

        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) mSpinnerCategories.getItemAtPosition(position);

                category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
                category_id = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID));

                showToast(category);

                highScores = mDbOpenHelper.getHighScores(category_id);

                displayHighScoresByCategory(highScores);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void displayHighScoresByCategory(Cursor cursor) {

        recyclerView = findViewById(R.id.highscores_list);
        highscoresLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(highscoresLayoutManager);

        highscoresAdapter = new HighscoresAdapter(this, cursor);

        recyclerView.setAdapter(highscoresAdapter);

    }

    private void showToast(String category) {
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

    }

}


// TODO: 2017-11-14 Lägg till:
// TODO: PLAY HISTORY
// TODO: STATISTIC FOR ALL CATEGIRES OR A SPESIFIC CAREGORY
// TODO: RANKING