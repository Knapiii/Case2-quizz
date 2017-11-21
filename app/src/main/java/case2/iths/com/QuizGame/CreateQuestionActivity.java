package case2.iths.com.QuizGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    public Spinner spinner;
    public String[] cats;
    private ArrayList<String> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

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
}
