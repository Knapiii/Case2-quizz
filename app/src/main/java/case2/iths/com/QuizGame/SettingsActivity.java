package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button goBack;
    private Button goProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toMain();
        toProfile();
    }

    public void toMain() {
        goBack = (Button)findViewById(R.id.goBackToMainButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(toMain);
            }
        });
    }

    public void toProfile() {
        goProfile = (Button)findViewById(R.id.goBackToMainButton);
        goProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(toProfile);
            }
        });
    }
}
