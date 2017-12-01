package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CountdownSplashActivity extends AppCompatActivity {

    private TextView countdown, getReady, reversed;
    private CountDownTimer cdTimer;
    private int amountOfStatements;
    private TextView headLine;
    private String chosenCategory;
    private boolean multiplayer;

    private int p1Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_splash);
        getReady = findViewById(R.id.text_get_ready);
        reversed = findViewById(R.id.text_reversed);

        countdown = findViewById(R.id.text_countdown_splash);
        headLine = findViewById(R.id.text_countdown_genre);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);

        //Om första spelarens antal påståenden inte är noll ska värden från förra spelet hämtas
        if (getIntent().getIntExtra("p1amountStatements", 0) != 0){
            p1Points = getIntent().getIntExtra("p1points", 0);
            chosenCategory = getIntent().getStringExtra("p1category");
            amountOfStatements = getIntent().getIntExtra("p1amountStatements", 5);
        }
        else{
            chosenCategory = getIntent().getStringExtra("category");
            amountOfStatements = getIntent().getIntExtra("amountOfStatements", amountOfStatements);
        }
        headLine.setText(chosenCategory);

        cdTimer = new CountDownTimer(4000, 100) {
            @Override
            public void onTick(long l) {
                if (l > 3000) {
                    countdown.setText("3");
                    reversed.setText(R.string.opposite);
                } else if (l < 2000 && l > 1000) {
                    countdown.setText("1");
                    decideGetReadyText();
                    reversed.setText(R.string.opposite);
                } else if (l < 3000 && l > 2000) {
                    countdown.setText("2");
                    decideGetReadyText();
                } else {
                    countdown.setText(R.string.go);
                    decideGetReadyText();
                    reversed.setText(R.string.opposite);
                }
            }

            @Override
            public void onFinish() {
                toSinglePlayer();
            }
        }.start();
    }

    private void decideGetReadyText(){
        if (getIntent().getIntExtra("p1amountStatements", 0) != 0 &&
                multiplayer)
            getReady.setText(R.string.p2_get_ready);
        else if (getIntent().getIntExtra("p1amountStatements", 0) == 0 &&
                multiplayer)
            getReady.setText(R.string.p1_get_ready);
        else
            getReady.setText(R.string.get_ready);
    }

    /**
     * Startar SingleActivity och skicka mer lämpliga värden beroende på om en spelare
     * har spelat en runda eller inte
     */
    public void toSinglePlayer(){
        cdTimer.cancel();
        if (getIntent().getIntExtra("p1amountStatements", 0) != 0 &&
                multiplayer){
            Intent multiIntent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
            multiIntent.putExtra("p1points", p1Points);
            multiIntent.putExtra("genre", chosenCategory);
            multiIntent.putExtra("amountOfStatements", amountOfStatements);
            multiIntent.putExtra("multiplayer", multiplayer);
            startActivity(multiIntent);
        }
        Intent intent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
        intent.putExtra("amountOfStatements", amountOfStatements);
        intent.putExtra("category", chosenCategory);
        intent.putExtra("multiplayer", multiplayer);
        startActivity(intent);
    }

    //Stops the timer when the back button is pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();
    }
}
