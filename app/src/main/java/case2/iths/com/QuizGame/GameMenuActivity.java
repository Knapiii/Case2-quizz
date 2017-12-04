package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

public class GameMenuActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private Spinner mProfilesSpinner;
    private QuizableOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        savedSettings = new SavedSettings();

        mDbOpenHelper = new QuizableOpenHelper(this);
        displayProfilesSpinner();


    }

    private void displayProfilesSpinner() {

        mProfilesSpinner = findViewById(R.id.spinner_profiles);
        Cursor allProfiles = mDbOpenHelper.getAllProfiles();
        ProfilesCursorAdapter profilesCursorAdapter = new ProfilesCursorAdapter(this, allProfiles, 0);
        mProfilesSpinner.setAdapter(profilesCursorAdapter);
    }


    /**
     * När vi klickar på knappen "Manage Statements" så ska vi komma till HandleStatementsActivity
     */
    public void toManagestatements(View view) {
        savedSettings.giveSound(this);
        Intent toManageStatements = new Intent(this, HandleStatementsActivity.class);
        startActivity(toManageStatements);
    }

    /**
     * När vi klickar på knappen "Singelplayer" så ska vi komma till
     * PlayGameActivity där kategori väljs för ett singelplayer spel
     */
    public void toSingelGame(View view) {
        savedSettings.giveSound(this);
        Intent toSingelGame = new Intent(this, CategoryWindowActivity.class);
        startActivity(toSingelGame);
    }

    /**
     * När vi klickar på knappen "MultiplayerActivity" så ska vi komma till
     * PlayGameActivity där kategori väljs för ett multiplayer spel
     */
    public void toMultiplayerGame(View view) {
        savedSettings.giveSound(this);
        Intent toMultiplayerGame = new Intent(this, CategoryWindowActivity.class);
        toMultiplayerGame.putExtra("multiplayer", true);
        startActivity(toMultiplayerGame);
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }
}
