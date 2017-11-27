package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private TextView amountOfPoints, playedCategory;
    private String category;
    private int points;
    private EditText insertName;
    private String name;
    private QuizableOpenHelper quizableOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings();

        amountOfPoints = findViewById(R.id.amount_of_points);
        playedCategory = findViewById(R.id.played_category);
        insertName = findViewById(R.id.editText_save_highscore_name);

        openHelper();

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: SHOW AMOUNT OF PLAYERS
    // TODO: SHOW TOTAL POINTS
    // TODO: SHOW TIME USED


    @Override
    protected void onDestroy() {
        quizableOpenHelper.close();
        super.onDestroy();
    }

    public void openHelper(){
        Intent intent = getIntent();

        category = intent.getStringExtra("category");
        points = intent.getIntExtra("points", 0);
        name = insertName.getText().toString();
        amountOfPoints.setText((Integer.toString(points)));
        playedCategory.setText(category);

        quizableOpenHelper = new QuizableOpenHelper(this);

        quizableOpenHelper.insertHighscore("all_categories", points,"Knape");

    }

    public void playAgain(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }

    public void backToMainMenu(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * When SAVE button is clicked the Highscore activity will be started
     */
    public void toTheHighscores(View view) {
        savedSettings.giveSound(this);
        Intent toHighscores = new Intent(this, HighScoreActivity.class);
        startActivity(toHighscores);

    }
}
