package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class PlayGameActivity extends AppCompatActivity {

    SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singelplayer);
        savedSettings = new SavedSettings();
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE

    /**
     * När vi är i Singelplayer-mode och klickar på de olika knapparna så ska olika Intents öppnar beroende på vad vi klickar på
     */
    public void categoryButtonPressed(View view){
        int id = view.getId();

        switch (id){
            case R.id.button_sport:
                savedSettings.giveSound(this);
                Intent sportIntent = new Intent(this, SinglePlayerActivity.class);
                sportIntent.putExtra("genre", "Sport");
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                savedSettings.giveSound(this);
                Intent musicIntent = new Intent(this, SinglePlayerActivity.class);
                musicIntent.putExtra("genre", "Musik");
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                savedSettings.giveSound(this);
                Intent geoIntent = new Intent(this, SinglePlayerActivity.class);
                geoIntent.putExtra("genre", "Geografi");
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                savedSettings.giveSound(this);
                Intent scienceIntent = new Intent(this, SinglePlayerActivity.class);
                scienceIntent.putExtra("genre", "Vetenskap");
                startSingleGame(scienceIntent);
                break;
            case R.id.button_culture:
                savedSettings.giveSound(this);
                Intent cultureIntent = new Intent(this, SinglePlayerActivity.class);
                cultureIntent.putExtra("genre", "Mat");
                startSingleGame(cultureIntent);
                break;
            case R.id.button_games:
                savedSettings.giveSound(this);
                Intent gamesIntent = new Intent(this, SinglePlayerActivity.class);
                gamesIntent.putExtra("genre", "Spel");
                startSingleGame(gamesIntent);
                break;
            case R.id.button_own_questions:
                savedSettings.giveSound(this);
                Intent ownIntent = new Intent(this, SinglePlayerActivity.class);
                ownIntent.putExtra("genre", "Own");
                Toast toast = Toast.makeText(this, "no questions", Toast.LENGTH_LONG);
                // toast.show();
                startSingleGame(ownIntent);
                break;
        }
    }

    private void startSingleGame(Intent intent){
        savedSettings.giveSound(this);
        startActivity(intent);
    }
}
