package case2.iths.com.QuizGame.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.HighScoreActivity;
import case2.iths.com.QuizGame.R;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "saveSettings";
    private QuizableOpenHelper mDbOpenHelper;
    private SavedSettings savedSettings;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedSettings = new SavedSettings();
        mDbOpenHelper = new QuizableOpenHelper(this);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);
        savedSettings.setSoundOn(silent);
        initialize();
        storeSettings();
    }

    private void initialize(){
        imageButton = findViewById(R.id.imagebutton_mute);
    }

    /**
     * När vi klickar på knappen "Play" så ska vi komma till GameMenuActivity
     */
    public void toTheGame(View view) {
        savedSettings.giveSound(this);
        Intent toGame = new Intent(this, GameMenuActivity.class);
        startActivity(toGame);
    }

    /**
     * När vi klickar på knappen "Highscore" så ska vi komma till HighScoreActivity
     */
    public void toTheHighscores(View view) {
        savedSettings.giveSound(this);
        int spinnerSelection = 0;
        Intent toHighscores = new Intent(this, HighScoreActivity.class);
        toHighscores.putExtra("defaultSelection", spinnerSelection);
        startActivity(toHighscores);

    }

    /**
     * När vi klickar på knappen "About" så ska vi komma till AboutActivity
     */
    public void toTheAbout(View view) {
        savedSettings.giveSound(this);
        Intent toAbout = new Intent(this, AboutActivity.class);
        startActivity(toAbout);
    }

    /**
     * Tar oss till ProfileActivity
     */
    public void toTheProfile(View view) {
        savedSettings.giveSound(this);
        Intent toProfile = new Intent(this, ProfileActivity.class);
        startActivity(toProfile);
    }

    /**
     * Olika bilder beroende om ljuder är på eller inte
     */
    public void toggleSoundOnClick(View view) {

        if (savedSettings.isSoundOn()) {
            imageButton.setImageResource(R.drawable.sound_switcher);
            savedSettings.setSoundOn(false);
        } else {
            imageButton.setImageResource(R.drawable.sound_unmuted);
            savedSettings.setSoundOn(true);
        }

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", savedSettings.isSoundOn());
        editor.apply();
    }

    private void storeSettings() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);
        savedSettings.setSoundOn(silent);

        if (savedSettings.isSoundOn())
            imageButton.setImageResource(R.drawable.sound_unmuted);
        else
            imageButton.setImageResource(R.drawable.sound_switcher);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", savedSettings.isSoundOn());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }
}
