package case2.iths.com.QuizGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private TextView amountOfPoints, playedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings();

        amountOfPoints = findViewById(R.id.amount_of_points);
        playedCategory = findViewById(R.id.played_category);

        Intent intent = getIntent();
        amountOfPoints.setText((Integer.toString(intent.getIntExtra("points", 0))));
        playedCategory.setText(intent.getStringExtra("category"));
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: SHOW AMOUNT OF PLAYERS
    // TODO: SHOW TOTAL POINTS
    // TODO: SHOW TIME USED

}
