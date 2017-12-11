package case2.iths.com.QuizGame;

import android.content.Context;
import android.media.MediaPlayer;

public class SavedSettings {

    private static boolean soundOn;

    /**
     * Gör så att vi kan sätta på ljudet.
     */
    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    /**
     * Kontrollerar om ljudet är på eller inte.
     */
    public boolean isSoundOn() {
        return soundOn;
    }

    /**
     * Möjliggör ljud till appen.
     */
    public boolean giveSound(Context context) {

        if (!soundOn) {
            MediaPlayer mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_sound);
            mMediaPlayer.start();
        }
        return soundOn;
    }

}

