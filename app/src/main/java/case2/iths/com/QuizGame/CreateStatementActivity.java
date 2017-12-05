package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class CreateStatementActivity extends AppCompatActivity {

    public Spinner spinner;
    private Button buttonTrue, buttonFalse;
    private boolean buttonTrueClicked;
    private EditText newStatement;
    private String statement;
    private String category;
    private String answer = "";
    private QuizableOpenHelper mDbOpenHelper;
    private QuizableDBHelper dbHelper;
    private Cursor categoriesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statements);
        buttonTrueClicked = false;
        initialize();
        mDbOpenHelper = new QuizableOpenHelper(this);
        dbHelper = new QuizableDBHelper(this);
        loadCategoriesData();
    }

    /**
     * TextViews
     */
    public void initialize() {
        buttonTrue = findViewById(R.id.togglebutton_add_true);
        buttonFalse = findViewById(R.id.togglebutton_add_false);
    }

    private void loadCategoriesData() {
        spinner = findViewById(R.id.spinner_add_category);
        categoriesCursor = mDbOpenHelper.getCategoriesCreateStatement();
        CategoriesCursorAdapter categoriesCursorAdapter = new CategoriesCursorAdapter(this, categoriesCursor);
        spinner.setAdapter(categoriesCursorAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                categoriesCursor.moveToPosition(position);
                category = categoriesCursor.getString(categoriesCursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * Möjliggör en ändrad färg när vi klickar på en knapp.
     */
    public void onButtonTrueClicked(View view){
        buttonTrueClicked = true;
        changeButtonColor();
        answer = "true";
    }

    /**
     * Möjliggör en ändrad färg när vi klickar på en knapp.
     */
    public void onButtonFalseClicked(View view){
        buttonTrueClicked = false;
        changeButtonColor();
        answer = "false";
    }

    private void changeButtonColor() {

        if (buttonTrueClicked){
            buttonTrue.setBackgroundResource(R.drawable.pressed_button_shape);
            buttonFalse.setBackgroundResource(R.drawable.button_shape);
        } else{
            buttonTrue.setBackgroundResource(R.drawable.button_shape);
            buttonFalse.setBackgroundResource(R.drawable.pressed_button_shape);
        }
    }

    /**
     * Calles addStatement method which adds statement to the database + starts
     * HandleStatements activity.
     */
    public void onButtonAddClicked(View view) {
        //  Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();

        if (!addStatement()){
            Intent intent = new Intent(this, HandleStatementsActivity.class);
            startActivity(intent);
        }
    }

    private boolean addStatement() {
        newStatement = findViewById(R.id.editText_add_statement);
        statement = newStatement.getText().toString();

        if (statement.isEmpty()) {
            Toast.makeText(this, "Statement cannot be empty", Toast.LENGTH_LONG).show();
            return true;
        }

        if (answer.isEmpty()) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return true;
        }

        String input = "SAVED: Category: " + category + " Statement: " + statement + "Answer: " + answer;
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        dbHelper.insertStatement(category, statement, answer);

    return false;
    }

}
