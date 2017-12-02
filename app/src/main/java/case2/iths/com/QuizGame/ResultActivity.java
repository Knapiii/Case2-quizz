package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private TextView amountOfPoints, playedCategory, textViewAmountOfStatements, textViewCorrectAnswers;
    private String category;
    private int points, amountOfStatements, correctAnswers;
    private EditText insertName;
    private String name;
    private QuizableOpenHelper quizableOpenHelper;

    // spinnerPosition saves the chosen category. We use spinnerPosition to give a default value to our spinner in the HighScore Activity
    private int spinnerPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings();
        initialize();

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: SHOW AMOUNT OF PLAYERS
    // TODO: SHOW TOTAL POINTS
    // TODO: SHOW TIME USED


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initialize(){
        //TextViews
        textViewCorrectAnswers = findViewById(R.id.amount_of_correct_answers);
        amountOfPoints = findViewById(R.id.amount_of_points);
        playedCategory = findViewById(R.id.played_category);
        textViewAmountOfStatements = findViewById(R.id.save_amount_of_statements_result);
        insertName = findViewById(R.id.editText_save_highscore_name);
        //GetTextViewValues
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        points = intent.getIntExtra("points", 0);
        amountOfStatements = intent.getIntExtra("amountOfStatements", 0);
        correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        //SetTextViews
        textViewCorrectAnswers.setText(Integer.toString(correctAnswers));
        amountOfPoints.setText((Integer.toString(points)));
        textViewAmountOfStatements.setText((Integer.toString(amountOfStatements)));
        spinnerPosition(category);
        playedCategory.setText(category);
        category = category.toLowerCase();
        if(category.equals("own"))
            category = "own_statements";
    }

    private int spinnerPosition(String category) {

        switch (category) {
            case "Food":
                spinnerPosition = 1;
                break;
            case "Games":
                spinnerPosition = 2;
                break;
            case "Geography":
                spinnerPosition = 3;
                break;
            case "Science":
                spinnerPosition = 4;
                break;
            case "Sport":
                spinnerPosition = 5;
                break;
            case "Music":
                spinnerPosition = 6;
                break;
            case "Own":
                spinnerPosition = 7;
                break;
            default:
                spinnerPosition = 0;
                break;
        }

        return spinnerPosition;
    }

    /**
     * Låter spelaren spela en gång till
     * @param view
     */
    public void playAgain(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, CategoryWindowActivity.class);
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
     */

    public void onSaveButtonClick(View view) {
        name = insertName.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please insert your name", Toast.LENGTH_LONG).show();
        } else {

            savedSettings.giveSound(this);
            Intent toHighscores = new Intent(this, HighScoreActivity.class);
            toHighscores.putExtra("spinnerPosition", spinnerPosition);
            toHighscores.putExtra("amountOfStatements", amountOfStatements);
            quizableOpenHelper = new QuizableOpenHelper(this);


            quizableOpenHelper.insertHighscore(category, points, amountOfStatements, name);
            startActivity(toHighscores);
        }




    }
}
