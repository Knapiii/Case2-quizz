package case2.iths.com.QuizGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2moon);
        textView = findViewById(R.id.textView);
    }

    public void onHejButtonPressed(View view){
        textView.setText("Doing great!");
    }
}