package case2.iths.com.QuizGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textview = (TextView) findViewById(R.id.about_text);
        textview.setMovementMethod(new ScrollingMovementMethod());
    }

}


