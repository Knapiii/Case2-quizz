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

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class CreateStatementActivity extends AppCompatActivity {

    public Spinner spinner;
    private Button buttonTrue, buttonFalse;
    private boolean buttonTrueClicked;
    private EditText newStatement;
    private String statement;
    private String category;
    private String answer;
    private QuizableOpenHelper mDbOpenHelper;
    private QuizableDBHelper dbHelper;
    private CategoriesCursorAdapter categoriesCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statements);
        buttonTrueClicked = false;
        buttonTrue = findViewById(R.id.togglebutton_add_true);
        buttonFalse = findViewById(R.id.togglebutton_add_false);

        mDbOpenHelper = new QuizableOpenHelper(this);
        dbHelper = new QuizableDBHelper(this);

        loadCategoriesData();
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        dbHelper.close();
        super.onDestroy();
    }

    private void loadCategoriesData() {

        Cursor cursor = mDbOpenHelper.loadCategoriesData();
        spinner = findViewById(R.id.spinner_add_category);

        categoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);

        spinner.setAdapter(categoriesCursorAdapter);
        spinner.setSelection(7);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                Cursor cursor = (Cursor) spinner.getItemAtPosition(position);
                category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onButtonTrueClicked(View view){
        buttonTrueClicked = true;
        changeButtonColor();
        answer = "true";
    }

    public void onButtonFalseClicked(View view){
        buttonTrueClicked = false;
        changeButtonColor();
        answer = "false";
    }

    private void changeButtonColor(){
        if (buttonTrueClicked){
            buttonTrue.setBackgroundResource(R.drawable.pressed_button_shape);
            buttonFalse.setBackgroundResource(R.drawable.button_shape);
        }
        else{
            buttonTrue.setBackgroundResource(R.drawable.button_shape);
            buttonFalse.setBackgroundResource(R.drawable.pressed_button_shape);
        }
    }

    // Calles addStatement method which adds statement to the database + starts HandleStatements activity.

    public void onButtonAddClicked(View view) {
        addStatement();

        Intent intent = new Intent(this, HandleStatementsActivity.class);
        startActivity(intent);

    }
    private void addStatement() {

        newStatement = findViewById(R.id.editText_add_statement);
        statement = newStatement.getText().toString();


        dbHelper.insertStatement(category, statement, answer);


    }

}
