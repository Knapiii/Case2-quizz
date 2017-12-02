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

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner mSpinnerCategories;
    private QuizableOpenHelper mDbOpenHelper;
    private RecyclerView recyclerView;
    private LinearLayoutManager highscoresLayoutManager;
    private String categoryId;
    private String categoryTitle;
    private HighscoresAdapter highscoresAdapter;
    private Cursor highScoresByCategory;
    private int categoryPosition, amountOfStatements;
    private Cursor allHighscores;
    private Cursor allCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mDbOpenHelper = new QuizableOpenHelper(this);
        Intent intent = getIntent();
        categoryPosition = intent.getIntExtra("spinnerPosition", 0);
        amountOfStatements = intent.getIntExtra("amountOfStatements", 0);
        displayCategoriesSpinner();

    }

    private void loadHighscores() {

    showHighscores(highScoresByCategory);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadHighscores();
    }

    private void displayCategoriesSpinner() {

        mSpinnerCategories = findViewById(R.id.spinner);
        allCategories = mDbOpenHelper.loadCategoriesData();
        CategoriesCursorAdapter categoriesCursorAdapter = new CategoriesCursorAdapter(this, allCategories);
        mSpinnerCategories.setAdapter(categoriesCursorAdapter);

        if(categoryPosition > -1)
            mSpinnerCategories.setSelection(categoryPosition);

        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                allCategories.moveToPosition(position);

                categoryTitle = allCategories.getString(allCategories.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
                categoryId = allCategories.getString(allCategories.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID));


                highScoresByCategory = mDbOpenHelper.getHighScoresByCategory(categoryId);
                allHighscores = mDbOpenHelper.getAllHighScores();

                if(position == 0)
                    showHighscores(allHighscores);
                else
                    showHighscores(highScoresByCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showHighscores(Cursor cursor) {

        recyclerView = findViewById(R.id.highscores_list);
        highscoresLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(highscoresLayoutManager);

        highscoresAdapter = new HighscoresAdapter(this, cursor);

        recyclerView.setAdapter(highscoresAdapter);

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