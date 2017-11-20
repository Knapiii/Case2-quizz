package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    SavedSettings savedSettings;
    ImageButton imageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        savedSettings = new SavedSettings();

        // recovering the instance state
        if (savedInstanceState != null) {
            savedSettings.setSoundOn(savedInstanceState.getBoolean("soundOnKey"));
           // drawSoundButton();
        } else {
            savedSettings.setSoundOn(false);
           // drawSoundButton();
        }
        imageButton = findViewById(R.id.toggleSoundImageButton);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("soundOnKey", savedSettings.isSoundOn());
    }

    @Override
    protected void onStop() {
        super.onStop();
        ContentValues values = new ContentValues();

        values.put("soundOnKey", savedSettings.isSoundOn());
    }

    public void toTheProfile(View view) {
        savedSettings.checkSoundOn(this);
        Intent toProfile = new Intent(this, ProfileActivity.class);
        startActivity(toProfile);
    }

    public void testSoundButton(View view) {
        savedSettings.checkSoundOn(this);
    }

    public void toggleSoundOnClick(View view) {
        int icon;

        if (savedSettings.isSoundOn()) {
            savedSettings.setSoundOn(false);
            icon = R.drawable.muted_sound;
        } else {
            savedSettings.setSoundOn(true);
            icon = R.drawable.unmuted_sound;
        }
        imageButton.setImageDrawable
                (ContextCompat.getDrawable(this, icon));
    }
/*

    public void drawSoundButton() {
        int icon = 0;
        if (savedSettings.isSoundOn()) {
            icon = R.drawable.unmuted_sound;
        }
        imageButton.setImageDrawable
                (ContextCompat.getDrawable(this, icon));
    }*/
}

