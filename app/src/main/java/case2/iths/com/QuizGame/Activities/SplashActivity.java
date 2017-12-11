package case2.iths.com.QuizGame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import case2.iths.com.QuizGame.R;

public class SplashActivity extends AppCompatActivity {

    /**
     * Set splash for 2 seconds.
     */
    private static int SPLASH_TIME_OUT = 2000;

    /**
     * Anropar på metoden som möjliggör Splash med layout "activity_splash"
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /**
             * När vi öppnar appen ska det vara en layout som visas i några sekunder innan man kommer till MainActivity.
             */
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        } ,SPLASH_TIME_OUT);
    }

}