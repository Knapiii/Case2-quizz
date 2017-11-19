package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singelplayer);

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE

    public void categoryButtonPressed(View view){
        int id = view.getId();

        switch (id){
            case R.id.button_sport:
                Intent sportIntent = new Intent(this, SinglePlayerActivity.class);
                sportIntent.putExtra("genre", "Sport");
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                Intent musicIntent = new Intent(this, SinglePlayerActivity.class);
                musicIntent.putExtra("genre", "Music");
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                Intent geoIntent = new Intent(this, SinglePlayerActivity.class);
                geoIntent.putExtra("genre", "Geography");
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                Intent scienceIntent = new Intent(this, SinglePlayerActivity.class);
                scienceIntent.putExtra("genre", "Science");
                startSingleGame(scienceIntent);
                break;
            case R.id.button_culture:
                Intent cultureIntent = new Intent(this, SinglePlayerActivity.class);
                cultureIntent.putExtra("genre", "Culture");
                startSingleGame(cultureIntent);
                break;
            case R.id.button_games:
                Intent gamesIntent = new Intent(this, SinglePlayerActivity.class);
                gamesIntent.putExtra("genre", "Games");
                startSingleGame(gamesIntent);
                break;
            case R.id.button_own_questions:
                Intent ownIntent = new Intent(this, SinglePlayerActivity.class);
                ownIntent.putExtra("genre", "Own");
                Toast toast = Toast.makeText(this,getString(R.string.no_questions), Toast.LENGTH_LONG);
                // toast.show();
                startSingleGame(ownIntent);
                break;
        }
    }

    private void startSingleGame(Intent intent){
        startActivity(intent);
    }
}
