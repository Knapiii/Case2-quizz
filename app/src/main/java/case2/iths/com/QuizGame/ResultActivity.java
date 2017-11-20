package case2.iths.com.QuizGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        savedSettings = new SavedSettings();
    }


    // TODO: 2017-11-14 Lägg till:
    // TODO: SHOW AMOUNT OF PLAYERS
    // TODO: SHOW TOTAL POINTS
    // TODO: SHOW TIME USED

}
