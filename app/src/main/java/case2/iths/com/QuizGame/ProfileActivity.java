package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private QuizableOpenHelper mDbOpenHelper;
    private Cursor profilesCursor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        savedSettings = new SavedSettings();

        mDbOpenHelper = new QuizableOpenHelper(this);
        profilesCursor = mDbOpenHelper.getAllProfiles();

        displayProfiles(profilesCursor);

    }

    private void displayProfiles(Cursor profilesCursor) {

        recyclerView = findViewById(R.id.list_profiles);
        LinearLayoutManager profilesLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(profilesLayoutManager);

        ProfileAdapter profileAdapter = new ProfileAdapter(this, profilesCursor);
        recyclerView.setAdapter(profileAdapter);


    }

    public void onAddButtonClick(View view) {

        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbOpenHelper.close();
    }
// TODO: 2017-11-14 Lägg till:
    // TODO:
    // TODO:
    // TODO:
}
