package case2.iths.com.QuizGame.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import case2.iths.com.QuizGame.Adapters.CategoriesCursorAdapter;
import case2.iths.com.QuizGame.Adapters.HighscoresAdapter;
import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.CategoriesInfoEntry;
import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.R;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner mSpinnerCategories;
    private QuizableOpenHelper mDbOpenHelper;
    private RecyclerView recyclerView;
    private LinearLayoutManager highscoresLayoutManager;
    private String categoryTitle;
    private HighscoresAdapter highscoresAdapter;
    private Cursor highScoresByCategory, allHighscores, allCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        mDbOpenHelper = new QuizableOpenHelper(this);
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


        if(setSpinnerSelection() > -1)
            mSpinnerCategories.setSelection(setSpinnerSelection());

        mSpinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Callback method to be invoked when an item in this view has been selected.
             * @param parent The adapterView where the selection happened
             * @param view The view within the AdapterView that was selected
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                allCategories.moveToPosition(position);
                categoryTitle = allCategories.getString(allCategories.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
                highScoresByCategory = mDbOpenHelper.getHighScoresByCategory(categoryTitle);
                allHighscores = mDbOpenHelper.getAllHighScores();

                if (position == 0)
                    showHighscores(allHighscores);
                else
                    showHighscores(highScoresByCategory);
            }

            /**
             * Callback method to be invoked when the selection disappears from this view.
             * @param parent The AdapterView that now contains no selected item.
             */

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private int setSpinnerSelection() {

        Intent intent = getIntent();
        int defaultSelection = intent.getIntExtra("defaultSelection", -1);
        SharedPreferences sp = getSharedPreferences("user_prefs", 0);
        int spinnerSelection = sp.getInt("spinnerSelection", 0);

        if(defaultSelection == 0)
            return defaultSelection;
        else
            return spinnerSelection;

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