package case2.iths.com.QuizGame.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import case2.iths.com.QuizGame.R;

public class CountdownSplashActivity extends AppCompatActivity {

    private TextView countdown, getReady, reversed;
    private CountDownTimer cdTimer;
    private int amountOfStatements;
    private TextView headLine;
    private String chosenCategory;
    private boolean multiplayer;
    private int p1Points;
    private int correctAnswers;
    private boolean p2sTurn;
    private String player1, player2;
    private Button readyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_splash);
        initializePlayers();
        initialize();
        setupTimer();
    }

    private void setupTimer(){
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

            /**
             * After timer is finished
             * Runs the method toSinglePlayer()
             */
            @Override
            public void onFinish() {
                toSinglePlayer();
            }
        };
        if (!p2sTurn)
            cdTimer.start();
    }

    private void initializePlayers() {

        SharedPreferences sp = getSharedPreferences("user_prefs", 0);
        player1 = sp.getString("player1", "");
        player2 = sp.getString("player2", "");

    }

    /**
     * Textviews
     * Hämtar värden
     * If-sats: om det är andra spelarens tur ska värden från första spelet hämtas,
     * annars ska värden hämtas som behövs för första spelet.
     * Sätter värden
     */
    public void initialize() {
        getReady = findViewById(R.id.text_get_ready);
        reversed = findViewById(R.id.text_reversed);
        countdown = findViewById(R.id.text_countdown_splash);
        headLine = findViewById(R.id.text_countdown_genre);
        readyBtn = findViewById(R.id.ready_button);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        p2sTurn = getIntent().getBooleanExtra("p2sTurn", false);

        if (p2sTurn) {
            p1Points = getIntent().getIntExtra("p1points", 0);
            chosenCategory = getIntent().getStringExtra("category");
            amountOfStatements = getIntent().getIntExtra("amountOfStatements", 5);
            correctAnswers = getIntent().getIntExtra("p1correctAnswers", 0);
            SharedPreferences sp = getSharedPreferences("user_prefs", 0);
            getReady.setText(sp.getString("player2", "") + "\n" + getResources().getString(R.string.are_you_ready));
        } else {
            chosenCategory = getIntent().getStringExtra("category");
            amountOfStatements = getIntent().getIntExtra("amountOfStatements", 5);
            readyBtn.setVisibility(readyBtn.GONE);
        }

        headLine.setText(chosenCategory);
    }

    private void decideGetReadyText() {
        if (p2sTurn && multiplayer)
            getReady.setText(player2 + "\n get ready!");
        else if (!p2sTurn && multiplayer)
            getReady.setText(player1 + "\n get ready!");
        else
            getReady.setText(player1 + "\n get ready");
    }

    /**
     * Startar SingleActivity och skicka mer lämpliga värden beroende på om en spelare har spelat
     * en runda eller inte. If-sats om vi kör i multiplayer så ska relevanta värden skickas vidare, annars
     * skickas relevanta värden vidare som är nödvändiga för första rundan.
     */
    public void toSinglePlayer() {
        cdTimer.cancel();

        if (p2sTurn && multiplayer) {
            Intent multiIntent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
            multiIntent.putExtra("p1points", p1Points);
            multiIntent.putExtra("p1correctAnswers", correctAnswers);
            multiIntent.putExtra("category", chosenCategory);
            multiIntent.putExtra("amountOfStatements", amountOfStatements);
            multiIntent.putExtra("multiplayer", multiplayer);
            multiIntent.putExtra("p2sTurn", p2sTurn);
            startActivity(multiIntent);
            finish();
        } else {
            Intent intent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
            intent.putExtra("amountOfStatements", amountOfStatements);
            intent.putExtra("category", chosenCategory);
            intent.putExtra("multiplayer", multiplayer);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Starts the timer for player 2 and hides the button
     * @param view
     */
    public void onReadyButtonPressed(View view){
        cdTimer.start();
        readyBtn.setVisibility(readyBtn.GONE);
    }

    /**
     * Stops the timer when the back button is pressed.
     * Stannar timern när vi klickar på bakåt-knappen.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();
    }

}
