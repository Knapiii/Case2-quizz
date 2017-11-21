package case2.iths.com.QuizGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SavedSettings savedSettings;
    public static final String PREFS_NAME = "saveSettings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedSettings = new SavedSettings();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);
        savedSettings.setSoundOn(silent);
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
        Intent toHighscores = new Intent(this, HighScoreActivity.class);
        startActivity(toHighscores);

    }

    /**
     * När vi klickar på knappen "Settings" så ska vi komma till SettingsActivity
     */
    public void toTheSettings(View view) {
        savedSettings.giveSound(this);
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }

    /**
     * När vi klickar på knappen "About" så ska vi komma till AboutActivity
     */
    public void toTheAbout(View view) {
        savedSettings.giveSound(this);
        Intent toAbout = new Intent(this, AboutActivity.class);
        startActivity(toAbout);
    }

}
