package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText insertUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        insertUsername = findViewById(R.id.text_username);


    }

    public void onClickSaveButton(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        QuizableOpenHelper mDbOpenHelper = new QuizableOpenHelper(this);
        String input = insertUsername.getText().toString();
        mDbOpenHelper.addUser(input);
        startActivity(intent);
    }



}
