package case2.iths.com.QuizGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "saveSettings";
    private SavedSettings savedSettings;
    private ImageButton imageButton;

    /**
     * När appen startas ska de senaste ändringarna vara sparade
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        savedSettings = new SavedSettings();
        imageButton = findViewById(R.id.imagebutton_mute);
        storeSettings();
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
            imageButton.setImageResource(R.drawable.muted_sound);
            savedSettings.setSoundOn(false);
        } else {
            imageButton.setImageResource(R.drawable.unmuted_sound);
            savedSettings.setSoundOn(true);
        }

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", savedSettings.isSoundOn());
        editor.apply();
    }

    private void storeSettings() {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);
        savedSettings.setSoundOn(silent);

        if (savedSettings.isSoundOn())
            imageButton.setImageResource(R.drawable.unmuted_sound);
        else
            imageButton.setImageResource(R.drawable.muted_sound);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", savedSettings.isSoundOn());
        editor.apply();
    }

}

