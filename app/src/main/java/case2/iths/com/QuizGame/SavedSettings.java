package case2.iths.com.QuizGame;


import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by kristofferknape on 2017-11-19.
 */

public class SavedSettings{

    private static boolean soundOn;


    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public boolean giveSound(Context context) {
        if (!soundOn) {
            MediaPlayer mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_sound);
            mMediaPlayer.start();
        }
        return soundOn;

    }



}
