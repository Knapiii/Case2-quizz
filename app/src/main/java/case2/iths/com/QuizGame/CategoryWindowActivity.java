package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CategoryWindowActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private boolean isMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_window);
        savedSettings = new SavedSettings();
        Intent intent = getIntent();
        isMultiplayer = intent.getBooleanExtra("multiplayer", false);
    }

    // TODO: FUTURE FEATURES:
    // TODO: Enable the choice of multiple categories

    /**
     * När vi är i Singelplayer-mode och klickar på de olika knapparna så ska olika Intents öppnar beroende på vad vi klickar på
     */
    public void categoryButtonPressed(View view){
        int id = view.getId();

        switch (id){
            case R.id.button_sport:
                savedSettings.giveSound(this);
                Intent sportIntent = new Intent(this, AmountOfStatementsActivity.class);
                sportIntent.putExtra("genre", "Sport");
                if (isMultiplayer)
                    sportIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                savedSettings.giveSound(this);
                Intent musicIntent = new Intent(this, AmountOfStatementsActivity.class);
                musicIntent.putExtra("genre", "Musik");
                if (isMultiplayer)
                    musicIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                savedSettings.giveSound(this);
                Intent geoIntent = new Intent(this, AmountOfStatementsActivity.class);
                geoIntent.putExtra("genre", "Geografi");
                if (isMultiplayer)
                    geoIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                savedSettings.giveSound(this);
                Intent scienceIntent = new Intent(this, AmountOfStatementsActivity.class);
                scienceIntent.putExtra("genre", "Vetenskap");
                if (isMultiplayer)
                    scienceIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(scienceIntent);
                break;
            case R.id.button_food:
                savedSettings.giveSound(this);
                Intent cultureIntent = new Intent(this, AmountOfStatementsActivity.class);
                cultureIntent.putExtra("genre", "Mat");
                if (isMultiplayer)
                    cultureIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(cultureIntent);
                break;
            case R.id.button_games:
                savedSettings.giveSound(this);
                Intent gamesIntent = new Intent(this, AmountOfStatementsActivity.class);
                gamesIntent.putExtra("genre", "Spel");
                if (isMultiplayer)
                    gamesIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(gamesIntent);
                break;
            case R.id.button_own_questions:
                savedSettings.giveSound(this);
                Intent ownIntent = new Intent(this, AmountOfStatementsActivity.class);
                ownIntent.putExtra("genre", "Own");
                if (isMultiplayer)
                    ownIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(ownIntent);
                break;
        }
    }

    private void startSingleGame(Intent intent){
        savedSettings.giveSound(this);
        startActivity(intent);
    }
}
