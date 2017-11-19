package case2.iths.com.QuizGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayerActivity extends AppCompatActivity {

    private String genre;
    private TextView textView;
    public TextView points;
    public int pointsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        genre = getIntent().getStringExtra("genre");
        textView = findViewById(R.id.top_text_category);
        textView.setText(genre);
        pointsCount = 0;
        points = findViewById(R.id.points);
        points.setText("" + pointsCount);
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE

}
