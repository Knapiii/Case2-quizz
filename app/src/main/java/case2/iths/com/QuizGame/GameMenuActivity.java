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

    public void toHandleQuestions(View view) {
        Intent toHandleQuestions = new Intent(this,HandleQuestionsActivity.class);
        startActivity(toHandleQuestions);
    }

    public void toGame(View view) {
        Intent toGame = new Intent(this,PlayGameActivity.class);
        startActivity(toGame);
    }

}
