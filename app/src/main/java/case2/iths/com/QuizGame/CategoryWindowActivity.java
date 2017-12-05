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
     * När vi är i category_window.xml och klickar på de olika knapparna så ska
     * olika intents öppnas beroende på vad vi klickade på.
     */
    public void categoryButtonPressed(View view){
        int id = view.getId();

        switch (id){
            case R.id.button_sport:
                savedSettings.giveSound(this);
                Intent sportIntent = new Intent(this, AmountOfStatementsActivity.class);
                sportIntent.putExtra("category", getResources().getString(R.string.sport));
                if (isMultiplayer)
                    sportIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(sportIntent);
                break;
            case R.id.button_music:
                savedSettings.giveSound(this);
                Intent musicIntent = new Intent(this, AmountOfStatementsActivity.class);
                musicIntent.putExtra("category", getResources().getString(R.string.music));
                if (isMultiplayer)
                    musicIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(musicIntent);
                break;
            case R.id.button_geography:
                savedSettings.giveSound(this);
                Intent geoIntent = new Intent(this, AmountOfStatementsActivity.class);
                geoIntent.putExtra("category", getResources().getString(R.string.geography));
                if (isMultiplayer)
                    geoIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(geoIntent);
                break;
            case R.id.button_science:
                savedSettings.giveSound(this);
                Intent scienceIntent = new Intent(this, AmountOfStatementsActivity.class);
                scienceIntent.putExtra("category", getResources().getString(R.string.science));
                if (isMultiplayer)
                    scienceIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(scienceIntent);
                break;
            case R.id.button_food:
                savedSettings.giveSound(this);
                Intent foodIntent = new Intent(this, AmountOfStatementsActivity.class);
                foodIntent.putExtra("category", getResources().getString(R.string.food));
                if (isMultiplayer)
                    foodIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(foodIntent);
                break;
            case R.id.button_games:
                savedSettings.giveSound(this);
                Intent gamesIntent = new Intent(this, AmountOfStatementsActivity.class);
                gamesIntent.putExtra("category", getResources().getString(R.string.games));
                if (isMultiplayer)
                    gamesIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(gamesIntent);
                break;
            case R.id.button_all_categories:
                savedSettings.giveSound(this);
                Intent allIntent = new Intent(this, AmountOfStatementsActivity.class);
                allIntent.putExtra("category", getResources().getString(R.string.all_categories));
                if (isMultiplayer)
                    allIntent.putExtra("multiplayer", isMultiplayer);
                startSingleGame(allIntent);
                break;
            case R.id.button_own_questions:
                savedSettings.giveSound(this);
                Intent ownIntent = new Intent(this, AmountOfStatementsActivity.class);
                ownIntent.putExtra("category", getResources().getString(R.string.own));
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
