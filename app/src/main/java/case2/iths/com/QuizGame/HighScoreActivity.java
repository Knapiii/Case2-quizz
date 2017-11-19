package case2.iths.com.QuizGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class HighScoreActivity extends AppCompatActivity {

    private Spinner spinner;
    private String[] cats;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        spinner = findViewById(R.id.spinner);
        cats = getResources().getStringArray(R.array.categories_array);

        //Custom adapter:

        HighscoresAdapter highscoresAdapter = new HighscoresAdapter(this, cats);


       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, R.layout.category_dropdownlist_item);
        // eller:
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cats);
       // adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(highscoresAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void showToast(int i) {
        Toast.makeText(this, cats[i], Toast.LENGTH_SHORT).show();

    }



    // TODO: 2017-11-14 Lägg till:
    // TODO: PLAY HISTORY
    // TODO: STATISTIC FOR ALL CATEGIRES OR A SPESIFIC CAREGORY
    // TODO: RANKING

}

