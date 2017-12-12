package case2.iths.com.QuizGame.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import case2.iths.com.QuizGame.Adapters.ProfilesCursorAdapter;
import case2.iths.com.QuizGame.Data.JSONTask;
import case2.iths.com.QuizGame.Data.QuizableDBHelper;
import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.UserInfoEntry;
import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.R;

public class CategoryWindowActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private boolean isMultiplayer;
    private Spinner player1Spinner;
    private Cursor allProfiles;
    private QuizableOpenHelper mDbOpenHelper;
    private ProfilesCursorAdapter profilesCursorAdapter;
    private Spinner player2Spinner;
    private String player1;
    private String player2;
    private int spinnerSelectionPosition;
    private SharedPreferences sharedPreferences;
    private QuizableDBHelper db;
    private ImageView expansionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_window);
        savedSettings = new SavedSettings(this);
        Intent intent = getIntent();
        isMultiplayer = intent.getBooleanExtra("multiplayer", false);
        mDbOpenHelper = new QuizableOpenHelper(this);
        displayProfileSpinner1();
        db = new QuizableDBHelper(this);
        if (isMultiplayer)
            displayProfileSpinner2();
        expansionIsDownloaded();
    }

    /**
     * När vi är i category_window.xml och klickar på de olika knapparna så ska
     * olika intents öppnas beroende på vad vi klickade på.
     */
    public void categoryButtonPressed(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_sport:
                Intent sportIntent = makeIntentBasedOnId(R.string.sport);
                spinnerSelectionPosition = 7;
                if (isMultiplayer)
                    sportIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                Intent musicIntent = makeIntentBasedOnId(R.string.music);
                spinnerSelectionPosition = 8;
                if (isMultiplayer)
                    musicIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                Intent geoIntent = makeIntentBasedOnId(R.string.geography);
                spinnerSelectionPosition = 5;
                if (isMultiplayer)
                    geoIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                Intent scienceIntent = makeIntentBasedOnId(R.string.science);
                spinnerSelectionPosition = 6;
                if (isMultiplayer)
                    scienceIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(scienceIntent);
                break;
            case R.id.button_food:
                Intent foodIntent = makeIntentBasedOnId(R.string.food);
                spinnerSelectionPosition = 2;
                if (isMultiplayer)
                    foodIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(foodIntent);
                break;
            case R.id.button_games:
                Intent gamesIntent = makeIntentBasedOnId(R.string.games);
                spinnerSelectionPosition = 3;
                if (isMultiplayer)
                    gamesIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(gamesIntent);
                break;
            case R.id.button_all_categories:
                Intent allIntent = makeIntentBasedOnId(R.string.all_categories);
                spinnerSelectionPosition = 1;
                if (isMultiplayer)
                    allIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(allIntent);
                break;
            case R.id.button_own_questions:
                Intent ownIntent = makeIntentBasedOnId(R.string.own);
                spinnerSelectionPosition = 9;
                if (isMultiplayer)
                    ownIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(ownIntent);
                break;
            case R.id.button_expansion:
                spinnerSelectionPosition = 4;
                Cursor c = db.getStatementsFromCategory("Expansion");

                if (c.moveToNext()) {
                    c.close();
                    Intent expansionIntent = new Intent(this, AmountOfStatementsActivity.class);
                    expansionIntent.putExtra("category", "Expansion");

                    if (isMultiplayer)
                        expansionIntent.putExtra("multiplayer", isMultiplayer);
                    startSingleGame(expansionIntent);

                } else {
                    expansionButton = findViewById(R.id.button_expansion);
                    expansionButton.setImageResource(R.drawable.expansion_downloaded);
                    c.close();
                    new JSONTask(this).execute();
                }
                break;
        }
    }

    private Intent makeIntentBasedOnId(int id) {
        Intent intent = new Intent(this, AmountOfStatementsActivity.class);
        intent.putExtra("category", getResources().getString(id));
        return intent;
    }

    private void expansionIsDownloaded() {
        Cursor c = db.getStatementsFromCategory("expansion");

        if (c.moveToNext()) {
            expansionButton = findViewById(R.id.button_expansion);
            expansionButton.setImageResource(R.drawable.expansion_downloaded);
        }
    }

    private void saveSpinnerSelectionPosition() {
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("spinnerSelection", spinnerSelectionPosition);
        editor.commit();
    }

    private void displayProfileSpinner1() {
        player1Spinner = findViewById(R.id.spinner_player_1);
        allProfiles = mDbOpenHelper.getAllProfiles();
        profilesCursorAdapter = new ProfilesCursorAdapter(this, allProfiles);
        player1Spinner.setAdapter(profilesCursorAdapter);
        player1Spinner.setSelection(0);
        player1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Callback method to be invoked when an item in this view has been selected.
             * @param adapterView The adapterView where the selection happened
             * @param view The view within the AdapterView that was selected
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                allProfiles.moveToPosition(position);
                boolean isProfileChosen = true;
                player1 = allProfiles.getString(allProfiles.getColumnIndex(UserInfoEntry.COLUMN_USERNAME));
                SharedPreferences sp = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("player1", player1);
                editor.putBoolean("profileChoosed", isProfileChosen);
                editor.commit();
            }

            /**
             * Callback method to be invoked when the selection disappears from this view.
             * @param adapterView The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void displayProfileSpinner2() {
        player2Spinner = findViewById(R.id.spinner_player_2);
        player2Spinner.setAdapter(profilesCursorAdapter);
        player2Spinner.setSelection(1);

        player2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Callback method to be invoked when an item in this view has been selected.
             * @param adapterView The adapterView where the selection happened
             * @param view The view within the AdapterView that was selected
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                allProfiles.moveToPosition(position);
                boolean isProfileChosen = true;
                player2 = allProfiles.getString(allProfiles.getColumnIndex(UserInfoEntry.COLUMN_USERNAME));
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("player2", player2);
                editor.putBoolean("isProfileChosen", isProfileChosen);
                editor.commit();
            }

            /**
             * Callback method to be invoked when the selection disappears from this view.
             * @param adapterView The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void startSingleGame(Intent intent) {
        saveSpinnerSelectionPosition();
        savedSettings.giveSound(this);
        startActivity(intent);
    }

}
