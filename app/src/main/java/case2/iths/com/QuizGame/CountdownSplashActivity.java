package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CountdownSplashActivity extends AppCompatActivity {

    private TextView countdown;
    private CountDownTimer cdTimer;
    private int amountOfStatements;
    private TextView headLine;
    private String chosenCategory;
    private boolean multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_splash);
        countdown = findViewById(R.id.text_countdown_splash);
        headLine = findViewById(R.id.text_countdown_genre);
        chosenCategory = getIntent().getStringExtra("genre");
        amountOfStatements = getIntent().getIntExtra("amountOfStatements", 0);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        headLine.setText(chosenCategory);



        cdTimer = new CountDownTimer(4000, 100) {
            @Override
            public void onTick(long l) {
                if (l > 3000) {
                    countdown.setText("3");
                } else if (l < 2000 && l > 1000) {
                    countdown.setText("1");
                } else if (l < 3000 && l > 2000) {
                    countdown.setText("2");
                } else
                    countdown.setText(R.string.go);
            }
            @Override
            public void onFinish() {
                toSinglePlayer();
            }
        }.start();

    }

    public void toSinglePlayer(){
        Intent intent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
        intent.putExtra("amountOfStatements", amountOfStatements);
        intent.putExtra("genre", chosenCategory);
        intent.putExtra("multiplayer", multiplayer);
        startActivity(intent);
    }

}
