package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toTheGame(View view) {
        Intent toGame = new Intent(this,GameMenuActivity.class);
        startActivity(toGame);
    }

    public void toTheSettings(View view) {
        Intent toSettings = new Intent(this, SettingsActivity.class);
        startActivity(toSettings);
    }

    public void toTheAbout(View view) {
        Intent toAbout = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(toAbout);
    }

}
