package case2.iths.com.QuizGame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class CreateQuestionActivity extends AppCompatActivity {

    public Spinner spinner;
    private Button buttonTrue, buttonFalse;
    private boolean buttonTrueClicked;
    private SQLiteOpenHelper mDbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statements);
        buttonTrueClicked = false;
        buttonTrue = findViewById(R.id.togglebutton_add_true);
        buttonFalse = findViewById(R.id.togglebutton_add_false);

        mDbOpenHelper = new QuizableOpenHelper(this);


        loadCategoriesData();
    }

    private void loadCategoriesData() {
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        spinner = findViewById(R.id.spinner_add_category);
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry._ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,
                null, null, null, null, CategoriesInfoEntry.COLUMN_CATEGORY_TITLE);

        CategoriesCursorAdapter CategoriesCursorAdapter = new CategoriesCursorAdapter(this, cursor);

        spinner.setAdapter(CategoriesCursorAdapter);


    }

    public void onButtonTrueClicked(View view){
        buttonTrueClicked = true;
        changeButtonColor();
    }

    public void onButtonFalseClicked(View view){
        buttonTrueClicked = false;
        changeButtonColor();
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
}
