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

import case2.iths.com.QuizGame.Adapters.StatementsAdapter;
import case2.iths.com.QuizGame.Data.QuizableDBHelper;

public class HandleStatementsActivity extends AppCompatActivity {

    private SavedSettings savedSettings;
    private RecyclerView recyclerView;
    private Cursor cursor;
    private StatementsAdapter statementsAdapter;
    private QuizableDBHelper quizableDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_questions);
        savedSettings = new SavedSettings();
        quizableDBHelper = new QuizableDBHelper(this);
        cursor = quizableDBHelper.getUserMadeStatements();
        displayStatements(cursor);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void displayStatements(Cursor cursor) {
        recyclerView = findViewById(R.id.list_statements);
        LinearLayoutManager statementsLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(statementsLayoutManager);
        statementsAdapter = new StatementsAdapter(this, cursor);
        recyclerView.setAdapter(statementsAdapter);
    }

    /**
     * När vi klickar på knappen så ska vi komma till CreateStatementActivity
     * Och klickljud ska låta om det är på.
     */
    public void onButtonClick(View v) {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, CreateStatementActivity.class);
        startActivity(intent);
    }

}