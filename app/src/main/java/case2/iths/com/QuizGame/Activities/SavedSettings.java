package case2.iths.com.QuizGame.Activities;

import android.content.Context;
import android.media.MediaPlayer;

import case2.iths.com.QuizGame.R;

public class SavedSettings {

    private static boolean soundOn;
    private static MediaPlayer mMediaPlayer;

    /**
     * Initializes the MediaPlayer
     * @param context
     */
    public SavedSettings(Context context){
        mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_sound);
    }

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
            final MediaPlayer mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_sound);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mMediaPlayer.release();
                }
            });
        }
        return soundOn;
    }
}

