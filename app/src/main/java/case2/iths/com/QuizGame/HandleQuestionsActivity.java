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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class HandleQuestionsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_questions);
        listView = findViewById(R.id.listView);

        //Data källa för vår listview
        list = new ArrayList<>();
        list.add("Text 1");
        list.add("Text 2");
        list.add("Text 3");

        //Färdig adapter för ListView
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        //Connect listView to arrayAdapter
        listView.setAdapter(arrayAdapter);


    }

    public void onButtonClick(View v) {

        arrayAdapter.add("Text");

    }
}