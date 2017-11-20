package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameMenuActivity extends AppCompatActivity {

    SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        savedSettings = new SavedSettings();
    }

    /**
     * När vi klickar på "button_handle_question" ska vi komma till HandleQuestionsActivity
     */
    public void toManageQuestions(View view) {
        savedSettings.checkSoundOn(this);
        Intent toManageQuestions = new Intent(this,HandleQuestionsActivity.class);
        startActivity(toManageQuestions);
    }

    /**
     * När vi klickar på "button_singelplayer" ska vi komma till SinglePlayerActivity
     */
    public void toSingelGame(View view) {
        savedSettings.checkSoundOn(this);
        Intent toSingelGame = new Intent(this, PlayGameActivity.class);
        startActivity(toSingelGame);
    }

    /**
     * När vi klickar på "button_multiplayer" ska vi komma till toMultiplayerGame
     */
    public void toMultiplayerGame(View view) {
        savedSettings.checkSoundOn(this);
        Intent toMultiplayerGame = new Intent(this,Multiplayer.class);
        startActivity(toMultiplayerGame);
    }
}
