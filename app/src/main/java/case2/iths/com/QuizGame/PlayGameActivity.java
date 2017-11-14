package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayGameActivity extends AppCompatActivity {

    private Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        toMain();
    }

    public void toMain() {
        goBack = (Button)findViewById(R.id.goBackToMainButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(PlayGameActivity.this,MainActivity.class);
                startActivity(toMain);
            }
        });
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE
}
