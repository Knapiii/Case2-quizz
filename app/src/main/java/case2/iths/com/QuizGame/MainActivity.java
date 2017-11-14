package case2.iths.com.QuizGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        textView = findViewById(R.id.textView);
*/
    }

    // public void onHejButtonPressed(View view){
    //    textView.setText("Doing great!");
    //}

    public void toTheGame(View view) {
        Intent toGame = new Intent(MainActivity.this,PlayGameActivity.class);
        startActivity(toGame);
    }

    public void toTheSettings(View view) {
        Intent toSettings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(toSettings);
    }

    public void toTheAbout(View view) {
        Intent toAbout = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(toAbout);
    }

}
