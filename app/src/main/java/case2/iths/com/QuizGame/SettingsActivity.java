package case2.iths.com.QuizGame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    private boolean soundOn = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void testSoundButton(View view) {
        isSoundOn();
        Intent testSoundButton = new Intent(this,SettingsActivity.class);
        startActivity(testSoundButton);
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
            //Ljud på

        }
        button.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));
    }

    public boolean isSoundOn() {


        MediaPlayer mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.button_click_sound);
        mMediaPlayer.start();
        return soundOn;
    }

}

