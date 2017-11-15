package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
    }

    /**
     * När vi klickar på "button_handle_question" ska vi komma till HandleQuestionsActivity
     */
    public void toManageQuestions(View view) {
        Intent toManageQuestions = new Intent(this,HandleQuestionsActivity.class);
        startActivity(toManageQuestions);
    }

    /**
     * När vi klickar på "button_singelplayer" ska vi komma till SinglePlayerActivity
     */
    public void toSingelGame(View view) {
        Intent toSingelGame = new Intent(this,SinglePlayerActivity.class);
        startActivity(toSingelGame);
    }

    /**
     * När vi klickar på "button_multiplayer" ska vi komma till toMultiplayerGame
     */
    public void toMultiplayerGame(View view) {
        Intent toMultiplayerGame = new Intent(this,Multiplayer.class);
        startActivity(toMultiplayerGame);
    }

    public void onSinglePlayerButtonClicked(View view){
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }

}
