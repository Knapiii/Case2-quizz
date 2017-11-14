package case2.iths.com.QuizGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button_game;
    private Button button_settings;
    private Button button_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2moon);
        textView = findViewById(R.id.textView);
        toTheGame();
        toTheSettings();
        toTheAbout();
    }

    // public void onHejButtonPressed(View view){
    //    textView.setText("Doing great!");
    //}

    public void toTheGame() {
        button_game = (Button)findViewById(R.id.button_game);
        button_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGame = new Intent(MainActivity.this,PlayGameActivity.class);
                startActivity(toGame);
            }
        });
    }

    public void toTheSettings() {
        button_settings = (Button)findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettings = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(toSettings);
            }
        });
    }

    public void toTheAbout() {
        button_about = (Button)findViewById(R.id.button_about);
        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAbout = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(toAbout);
            }
        });
    }

}
