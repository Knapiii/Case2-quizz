package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import case2.iths.com.QuizGame.Data.QuizableOpenHelper;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText insertUsername;
    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        insertUsername = findViewById(R.id.text_username);
    }

    /**
     * När vi klickar på spara i acitivty_create_profile.xml så ska namnet vi angav
     * sparas i den lokala databasen.
     */
    public void onClickSaveButton(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        QuizableOpenHelper mDbOpenHelper = new QuizableOpenHelper(this);
        input = insertUsername.getText().toString();
        if (!input.isEmpty()) {
            mDbOpenHelper.addUser(input);
            startActivity(intent);
        }

    }

}
