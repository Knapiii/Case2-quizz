package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    public void initialize(){
        //TextViews
        buttonTrue = findViewById(R.id.togglebutton_add_true);
        buttonFalse = findViewById(R.id.togglebutton_add_false);
    }

    private void loadCategoriesData() {
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        spinner = findViewById(R.id.spinner_add_category);
        final String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry._ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,
                null, null, null, null, CategoriesInfoEntry.COLUMN_CATEGORY_TITLE);

        CategoriesCursorAdapter CategoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);

        spinner.setAdapter(CategoriesCursorAdapter);
        spinner.setSelection(5);
     //   spinner.setEnabled(false);




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
      //  Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        addStatement();

        Intent intent = new Intent(this, HandleStatementsActivity.class);
        startActivity(intent);





    }
    private void addStatement() {

        newStatement = findViewById(R.id.editText_add_statement);
        statement = newStatement.getText().toString();

        String input = "SAVED: Category: " + category + " Statement: " + statement + "Answer: " + answer;

       // Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

    //    mDbOpenHelper.insertStatement(category,statement,answer);

        dbHelper.insertStatement(category, statement, answer);


    }

}
