package case2.iths.com.QuizGame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    private boolean soundOn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void toTheProfile(View view) {
        isSoundOn();
        Intent toProfile = new Intent(this, ProfileActivity.class);
        startActivity(toProfile);
    }

    public void testSoundButton(View view) {
        isSoundOn();
    }

    public void toggleSoundOnClick(View view) {
        ImageButton button = (ImageButton) view;
        int icon;

        if (soundOn) {
            soundOn = false;
            icon = R.drawable.muted_sound;
            //Ljud av
        } else {
            soundOn = true;
            icon = R.drawable.unmuted_sound;
        }
        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));
    }

    public boolean isSoundOn() {
        if (!soundOn) {
            MediaPlayer mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.button_click_sound);
            mMediaPlayer.start();
        }
        return soundOn;
    }

}

