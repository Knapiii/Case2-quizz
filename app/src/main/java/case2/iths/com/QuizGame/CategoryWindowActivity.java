package case2.iths.com.QuizGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import case2.iths.com.QuizGame.Adapters.ProfilesCursorAdapter;
import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.UserInfoEntry;

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
    private String statement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_window);
        savedSettings = new SavedSettings();
        Intent intent = getIntent();
        isMultiplayer = intent.getBooleanExtra("multiplayer", false);
        mDbOpenHelper = new QuizableOpenHelper(this);
        displayProfileSpinner1();

        if (isMultiplayer)
            displayProfileSpinner2();


    }


    // TODO: FUTURE FEATURES:
    // TODO: Enable the choice of multiple categories

    /**
     * När vi är i category_window.xml och klickar på de olika knapparna så ska
     * olika intents öppnas beroende på vad vi klickade på.
     */
    public void categoryButtonPressed(View view){
        int id = view.getId();

        switch (id){
            case R.id.button_sport:
                savedSettings.giveSound(this);
                Intent sportIntent = new Intent(this, AmountOfStatementsActivity.class);
                sportIntent.putExtra("category", getResources().getString(R.string.sport));
                spinnerSelectionPosition = 5;
                if (isMultiplayer)
                    sportIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                savedSettings.giveSound(this);
                Intent musicIntent = new Intent(this, AmountOfStatementsActivity.class);
                musicIntent.putExtra("category", getResources().getString(R.string.music));
                spinnerSelectionPosition= 6;
                if (isMultiplayer)
                    musicIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                savedSettings.giveSound(this);
                Intent geoIntent = new Intent(this, AmountOfStatementsActivity.class);
                geoIntent.putExtra("category", getResources().getString(R.string.geography));
                spinnerSelectionPosition= 3;
                if (isMultiplayer)
                    geoIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                savedSettings.giveSound(this);
                Intent scienceIntent = new Intent(this, AmountOfStatementsActivity.class);
                scienceIntent.putExtra("category", getResources().getString(R.string.science));
                spinnerSelectionPosition= 4;
                if (isMultiplayer)
                    scienceIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(scienceIntent);
                break;
            case R.id.button_food:
                savedSettings.giveSound(this);
                Intent foodIntent = new Intent(this, AmountOfStatementsActivity.class);
                foodIntent.putExtra("category", getResources().getString(R.string.food));
                spinnerSelectionPosition= 1;
                if (isMultiplayer)
                    foodIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(foodIntent);
                break;
            case R.id.button_games:
                savedSettings.giveSound(this);
                Intent gamesIntent = new Intent(this, AmountOfStatementsActivity.class);
                gamesIntent.putExtra("category", getResources().getString(R.string.games));
                spinnerSelectionPosition= 2;
                if (isMultiplayer)
                    gamesIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(gamesIntent);
                break;
            case R.id.button_all_categories:
                savedSettings.giveSound(this);
                Intent allIntent = new Intent(this, AmountOfStatementsActivity.class);
                allIntent.putExtra("category", getResources().getString(R.string.all_categories));
                spinnerSelectionPosition= 0;
                if (isMultiplayer)
                    allIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(allIntent);
                break;
            case R.id.button_own_questions:
                savedSettings.giveSound(this);
                Intent ownIntent = new Intent(this, AmountOfStatementsActivity.class);
                ownIntent.putExtra("category", getResources().getString(R.string.own));
                spinnerSelectionPosition= 7;
                if (isMultiplayer)
                    ownIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(ownIntent);
                break;
            case R.id.button_expansion:
                savedSettings.giveSound(this);
                // Utremmat då det inte funkar än
                //Intent expansionIntent = new Intent(this, AmountOfStatementsActivity.class);
                //expansionIntent.putExtra("category", getResources().getString(R.string.expansion));
                //if (isMultiplayer)
                //    expansionIntent.putExtra("multiplayer", isMultiplayer);
                //startSingleGame(expansionIntent);
                Toast.makeText(this, "There is no expansion at the moment", Toast.LENGTH_LONG).show();
                break;
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
                editor.putBoolean("profileChosen", isProfileChosen);
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

    private void startSingleGame(Intent intent){
        saveSpinnerSelectionPosition();
        savedSettings.giveSound(this);
        startActivity(intent);
    }

}
