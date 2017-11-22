package case2.iths.com.QuizGame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class CreateQuestionActivity extends AppCompatActivity {

    public Spinner spinner;
    public String[] cats;
    private ArrayList<String> categories = new ArrayList<>();
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




        categories.add("Välj kategori: ");
        categories.add("Sport");
        categories.add("Food");
        categories.add("TV");
        categories.add("Games");

        loadCategoriesData();
/*
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.dropdown_list_item, categories){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.WHITE);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.dropdown_list_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
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
