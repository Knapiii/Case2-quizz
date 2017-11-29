package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CountdownSplashActivity extends AppCompatActivity {

    private TextView countdown, getReady,reversed;
    private CountDownTimer cdTimer;
    private int amountOfStatements;
    private TextView headLine;
    private String chosenCategory;
    private boolean multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_splash);
        getReady = findViewById(R.id.text_get_ready);
        reversed = findViewById(R.id.text_reversed);

        countdown = findViewById(R.id.text_countdown_splash);
        headLine = findViewById(R.id.text_countdown_genre);
        chosenCategory = getIntent().getStringExtra("genre");
        amountOfStatements = getIntent().getIntExtra("amountOfStatements", amountOfStatements);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        headLine.setText(chosenCategory);

        cdTimer = new CountDownTimer(4000, 100) {
            @Override
            public void onTick(long l) {
                if (l > 3000) {
                    countdown.setText("3");
                    //runAnimation();
                    reversed.setText(R.string.opposite);
                } else if (l < 2000 && l > 1000) {
                    countdown.setText("1");
                    //runAnimation();
                    getReady.setText(R.string.get_ready);
                    reversed.setText(R.string.opposite);
                } else if (l < 3000 && l > 2000) {
                    countdown.setText("2");
                    //runAnimation();
                } else {
                    countdown.setText(R.string.go);
                    //runAnimation();
                    getReady.setText(R.string.get_ready);
                    reversed.setText(R.string.opposite);
                }
            }

            @Override
            public void onFinish() {
                toSinglePlayer();
            }
        }.start();

    }

    public void toSinglePlayer() {
        cdTimer.cancel();
        Intent intent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
        intent.putExtra("amountOfStatements", amountOfStatements);
        intent.putExtra("genre", chosenCategory);
        intent.putExtra("multiplayer", multiplayer);
        startActivity(intent);
    }

    //Stops the timer when the back button is pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();
    }

    private void runAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        a.reset();
        countdown.clearAnimation();
        countdown.startAnimation(a);
    }
}
