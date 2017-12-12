package case2.iths.com.QuizGame.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.R;

public class ResultActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private TextView amountOfPoints, textViewAmountOfStatements, textViewCorrectAnswers;
    private String category;
    private int points, amountOfStatements, correctAnswers;
    private TextView playerName;
    private String name, name2;
    private QuizableOpenHelper quizableOpenHelper;
    //multiplayer variables
    private boolean p2sTurn, multiplayer;
    private int p1Points, p1CorrectAnswers, p2Points, p2CorrectAnswers;
    private String player1, player2;
    private boolean isProfileChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings(this);
        initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * TextViews
     * If: Get values relevant if multiplayer is true
     * Else: Get values relevant for singleplayer
     * Second if-else is for set Textviews
     */
    public void initialize() {

        SharedPreferences sp = getSharedPreferences("user_prefs", 0);
        player1 = sp.getString("player1", "");
        player2 = sp.getString("player2", "");

        textViewCorrectAnswers = findViewById(R.id.amount_of_correct_answers);
        amountOfPoints = findViewById(R.id.amount_of_points);
        playerName = findViewById(R.id.played_category);
        textViewAmountOfStatements = findViewById(R.id.save_amount_of_statements_result);
        //playerName = findViewById(R.id.player_name);

        if (getIntent().getBooleanExtra("multiplayer", false)) {
            Intent intent = getIntent();
            multiplayer = intent.getBooleanExtra("multiplayer", false);
            category = intent.getStringExtra("category");
            if(category.equals("Own"))
                category = "Own statements";
            amountOfStatements = intent.getIntExtra("amountOfStatements", 5);
            p1Points = intent.getIntExtra("p1points", 0);
            p1CorrectAnswers = intent.getIntExtra("p1correctAnswers", 0);
            p2Points = intent.getIntExtra("p2points", 0);
            p2CorrectAnswers = intent.getIntExtra("p2correctAnswers", 0);
            p2sTurn = intent.getBooleanExtra("p2sTurn", false);
        } else {
            Intent intent = getIntent();
            category = intent.getStringExtra("category");
            if(category.equals("Own"))
                category = "Own statements";
            points = intent.getIntExtra("points", 0);
            amountOfStatements = intent.getIntExtra("amountOfStatements", 5);
            correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        }

        if (multiplayer && !p2sTurn) {
            textViewCorrectAnswers.setText(Integer.toString(p1CorrectAnswers));
            amountOfPoints.setText((Integer.toString(p1Points)));
            showPlayerName(player1);
        } else if (multiplayer && p2sTurn) {
            textViewCorrectAnswers.setText(Integer.toString(p2CorrectAnswers));
            amountOfPoints.setText((Integer.toString(p2Points)));
            showPlayerName(player2);
        } else {
            textViewCorrectAnswers.setText(Integer.toString(correctAnswers));
            amountOfPoints.setText((Integer.toString(points)));
            showPlayerName(player1);
        }

        textViewAmountOfStatements.setText((Integer.toString(amountOfStatements)));


        isProfileChosen = sp.getBoolean("isProfileChosen", false);

        if (multiplayer) {
            name = player1;
            name2 = player2;
        } else {
            name = player1;
        }


    }


    /**
     * Låter spelaren spela en gång till
     * @param view
     */
    public void playAgain(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, CategoryWindowActivity.class);
        if (multiplayer)
            intent.putExtra("multiplayer", multiplayer);
        startActivity(intent);
    }

    /**
     * Skickar tillbaka spelaren till huvudmenyn
     * @param view
     */
    public void backToMainMenu(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * When SAVE button is clicked the Highscore activity will be started
     * Save first players score to highscore before showing results for second player
     * Saves second players score and starts HighscoreActivity
     * Saves the score from a single player game
     */
    public void onSaveButtonClick(View view) {
        savedSettings.giveSound(this);
            if (multiplayer && !p2sTurn) {
                Intent toNextResult = new Intent(this, ResultActivity.class);
                toNextResult.putExtra("multiplayer", multiplayer);
                toNextResult.putExtra("category", category);
                toNextResult.putExtra("p2points", p2Points);
                toNextResult.putExtra("p2correctAnswers", p2CorrectAnswers);
                toNextResult.putExtra("p2sTurn", true);
                toNextResult.putExtra("amountOfStatements", amountOfStatements);
                quizableOpenHelper = new QuizableOpenHelper(this);
                quizableOpenHelper.insertHighscore(category, p1Points, amountOfStatements, name);
                startActivity(toNextResult);
            } else if (multiplayer && p2sTurn) {
                Intent toHighscore = new Intent(this, HighScoreActivity.class);
                toHighscore.putExtra("amountOfStatements", amountOfStatements);
                quizableOpenHelper = new QuizableOpenHelper(this);
                quizableOpenHelper.insertHighscore(category, p2Points, amountOfStatements, name2);
                startActivity(toHighscore);
            } else {
                showPlayerName(player1);
                Intent toHighscores = new Intent(this, HighScoreActivity.class);
                toHighscores.putExtra("amountOfStatements", amountOfStatements);
                quizableOpenHelper = new QuizableOpenHelper(this);
                quizableOpenHelper.insertHighscore(category, points, amountOfStatements, name);
                startActivity(toHighscores);
            }
        }

    private void showPlayerName(String name) {
        playerName.setText(name);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, GameMenuActivity.class);
        startActivity(intent);
    }

}
