package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private TextView amountOfPoints, playedCategory, textViewAmountOfStatements;
    private String category;
    private int points, amountOfStatements;
    private EditText insertName;
    private String name;
    private QuizableOpenHelper quizableOpenHelper;

    // categoryPos saves the chosen category. We use categoryPos to give a default value to our spinner in the HighScore Activity
    private int categoryPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings();

        amountOfPoints = findViewById(R.id.amount_of_points);
        playedCategory = findViewById(R.id.played_category);
        textViewAmountOfStatements = findViewById(R.id.editText_save_amount_of_statements_result);
        insertName = findViewById(R.id.editText_save_highscore_name);

        openHelper();

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: SHOW AMOUNT OF PLAYERS
    // TODO: SHOW TOTAL POINTS
    // TODO: SHOW TIME USED


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openHelper(){
        Intent intent = getIntent();
        category = checkCategory(intent.getStringExtra("category"));
        points = intent.getIntExtra("points", 0);
        amountOfStatements = intent.getIntExtra("amountOfStatements", amountOfStatements);
        amountOfPoints.setText((Integer.toString(points)));
        textViewAmountOfStatements.setText((Integer.toString(amountOfStatements)));
        playedCategory.setText(category);
        category = category.toLowerCase();

    }

    //This methos translates category to english when necessary

    private String checkCategory(String category_id) {

        switch (category_id) {
            case "Food":
                categoryPos = 1;
                break;
            case "Games":
                category_id = "Games";
                categoryPos = 2;
                break;
            case "Geography":
                category_id = "Geography";
                categoryPos = 3;
                break;
            case "Scince":
                category_id = "Science";
                categoryPos = 4;
                break;
            case "Sport":
                category_id = "Sport";
                categoryPos = 5;
                break;
            case "Music":
                category_id = "Music";
                categoryPos = 6;
                break;
            case "Own statements":
                category_id = "Own_statements";
                categoryPos = 7;
                break;
            default:
                category_id = "All categories";
                categoryPos = 0;
                break;
        }
        return category_id;
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
        savedSettings.giveSound(this);
        Intent toHighscores = new Intent(this, HighScoreActivity.class);
        toHighscores.putExtra("categoryPos", categoryPos);
        toHighscores.putExtra("amountOfStatements", amountOfStatements);
        quizableOpenHelper = new QuizableOpenHelper(this);
        name = insertName.getText().toString();
        quizableOpenHelper.insertHighscore(category, points, amountOfStatements, name);


        startActivity(toHighscores);

    }
}
