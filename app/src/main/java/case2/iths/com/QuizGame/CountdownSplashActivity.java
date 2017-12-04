package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_splash);
        initialize();

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

    public void initialize(){
        //TextViews
        getReady = findViewById(R.id.text_get_ready);
        reversed = findViewById(R.id.text_reversed);
        countdown = findViewById(R.id.text_countdown_splash);
        headLine = findViewById(R.id.text_countdown_genre);
        //Get Values
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        p2sTurn = getIntent().getBooleanExtra("p2sTurn", false);

        //Om det är andra spelarens tur ska värden från första spelet hämtas
        if (p2sTurn){
            p1Points = getIntent().getIntExtra("p1points", 0);
            chosenCategory = getIntent().getStringExtra("category");
            amountOfStatements = getIntent().getIntExtra("amountOfStatements", 5);
            correctAnswers = getIntent().getIntExtra("p1correctAnswers", 0);
        }
        //Annars hämtas värden som behövs för första spelet
        else{
            chosenCategory = getIntent().getStringExtra("category");
            amountOfStatements = getIntent().getIntExtra("amountOfStatements", 5);
        }
        //Set values
        headLine.setText(chosenCategory);

    }

    private void decideGetReadyText(){
        if (p2sTurn && multiplayer)
            getReady.setText(R.string.p2_get_ready);
        else if (!p2sTurn && multiplayer)
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
        //Om det är andra spelarens tur skickas relevanta värden vidare
        if (p2sTurn && multiplayer){
            Intent multiIntent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
            multiIntent.putExtra("p1points", p1Points);
            multiIntent.putExtra("p1correctAnswers", correctAnswers);
            multiIntent.putExtra("category", chosenCategory);
            multiIntent.putExtra("amountOfStatements", amountOfStatements);
            multiIntent.putExtra("multiplayer", multiplayer);
            multiIntent.putExtra("p2sTurn", p2sTurn);
            startActivity(multiIntent);
            finish();
        }
        //Annars skickad relevanta värden som är nödvändiga för första rundan
        else{
            Intent intent = new Intent(CountdownSplashActivity.this, SinglePlayerActivity.class);
            intent.putExtra("amountOfStatements", amountOfStatements);
            intent.putExtra("category", chosenCategory);
            intent.putExtra("multiplayer", multiplayer);
            startActivity(intent);
            finish();
        }
    }

    //Stops the timer when the back button is pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();
    }
}
