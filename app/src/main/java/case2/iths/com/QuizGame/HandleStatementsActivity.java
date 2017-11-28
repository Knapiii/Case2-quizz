package case2.iths.com.QuizGame;

// TODO: 2017-11-14 Lägg till:
// TODO: CREATE QUESTION-FUNCTION
// TODO:        ADD QUESTION
// TODO:        ADD ALTERNITIVES
// TODO:           - WRONG ANSWERS
// TODO:           - RIGHT ANSWERS
// TODO:        ADD CATEGORY
// TODO: DELETE QUESTION-FUNCTION
// TODO: RANKING

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HandleStatementsActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private RecyclerView recyclerView;
    private QuizableOpenHelper mDbOpenHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_questions);
        savedSettings = new SavedSettings();

        mDbOpenHelper = new QuizableOpenHelper(this);

        cursor = mDbOpenHelper.loadStatementsData();

        displayStatements(cursor);


    }

    private void displayStatements(Cursor cursor) {

        recyclerView = findViewById(R.id.list_statements);
        LinearLayoutManager statementsLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(statementsLayoutManager);

        StatementsAdapter statementsAdapter = new StatementsAdapter(this, cursor);

        recyclerView.setAdapter(statementsAdapter);

    }

    public void onButtonClick(View v) {
        savedSettings.giveSound(this);

        Intent intent = new Intent(this, CreateStatementActivity.class);
        startActivity(intent);
    }


    public void editStatement(View view) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, EditStatementActivity.class);
        startActivity(intent);
    }




}