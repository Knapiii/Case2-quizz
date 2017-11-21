package case2.iths.com.QuizGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    public Spinner spinner;
    public String[] cats;
    private ArrayList<String> categories = new ArrayList<>();
    private ToggleButton buttonTrue, buttonFalse;
    private boolean buttonTrueClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statements);
        buttonTrueClicked = false;
        buttonTrue = findViewById(R.id.togglebutton_add_true);
        buttonFalse = findViewById(R.id.togglebutton_add_false);

        categories.add("Välj kategori: ");
        categories.add("Sport");
        categories.add("Food");
        categories.add("TV");
        categories.add("Games");

        categorySpinner();

    }

    public void categorySpinner(){
        spinner = findViewById(R.id.spinner_add_category);
        HighscoresAdapter addToCategoryAdapter = new HighscoresAdapter(this, categories);
        spinner.setAdapter(addToCategoryAdapter);

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
