package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AmountOfStatementsActivity extends AppCompatActivity {

    private Button button5Statements, button10Statements, button15Statements, button20Statements;
    private int amountOfStatements;
    private String chosenCategory;
    private TextView categoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_of_statements);

        button5Statements = findViewById(R.id.button_statements_5);
        button10Statements = findViewById(R.id.button_statements_10);
        button15Statements = findViewById(R.id.button_statements_15);
        button20Statements = findViewById(R.id.button_statements_20);

        chosenCategory = getIntent().getStringExtra("genre");
        categoryTextView = findViewById(R.id.category_text_view);
        categoryTextView.setText(getString(R.string.chosen_category) + chosenCategory);
    }

    public void Button5Rounds(View view) {
        amountOfStatements = 5;
        changeButtonColor(5);
    }

    public void Button10Rounds(View view) {
        amountOfStatements = 10;
        changeButtonColor(10);
    }

    public void Button15Rounds(View view) {
        amountOfStatements = 15;
        changeButtonColor(amountOfStatements);
    }

    public void Button20Rounds(View view) {
        amountOfStatements = 20;
        changeButtonColor(amountOfStatements);
    }

    public void RoundsChosen(View view) {
        Intent intent = new Intent(this, SinglePlayerActivity.class);
        intent.putExtra("amountOfStatements", amountOfStatements);
        intent.putExtra("genre", chosenCategory);
        startActivity(intent);
    }

    private void changeButtonColor(int rounds) {

        switch (rounds) {
            case 5:
                button5Statements.setBackgroundResource(R.drawable.pressed_button_rectangle);
                button10Statements.setBackgroundResource(R.drawable.button_rectangle);
                button15Statements.setBackgroundResource(R.drawable.button_rectangle);
                button20Statements.setBackgroundResource(R.drawable.button_rectangle);
                break;
            case 10:
                button5Statements.setBackgroundResource(R.drawable.button_rectangle);
                button10Statements.setBackgroundResource(R.drawable.pressed_button_rectangle);
                button15Statements.setBackgroundResource(R.drawable.button_rectangle);
                button20Statements.setBackgroundResource(R.drawable.button_rectangle);
                break;
            case 15:
                button5Statements.setBackgroundResource(R.drawable.button_rectangle);
                button10Statements.setBackgroundResource(R.drawable.button_rectangle);
                button15Statements.setBackgroundResource(R.drawable.pressed_button_rectangle);
                button20Statements.setBackgroundResource(R.drawable.button_rectangle);
                break;
            case 20:
                button5Statements.setBackgroundResource(R.drawable.button_rectangle);
                button10Statements.setBackgroundResource(R.drawable.button_rectangle);
                button15Statements.setBackgroundResource(R.drawable.button_rectangle);
                button20Statements.setBackgroundResource(R.drawable.pressed_button_rectangle);
                break;
        }
    }
}

