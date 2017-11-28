package case2.iths.com.QuizGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AmountOfStatementsActivity extends AppCompatActivity {

    private Button button5Rounds, button10Rounds, button15Rounds, button20Rounds;
    private int rounds;
    private String chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_of_statements);

        button5Rounds = findViewById(R.id.button_statements_5);
        button10Rounds = findViewById(R.id.button_statements_10);
        button15Rounds = findViewById(R.id.button_statements_15);
        button20Rounds = findViewById(R.id.button_statements_20);

        chosenCategory = getIntent().getStringExtra("genre");
    }

    public void Button5Rounds(View view) {
        rounds = 5;
        changeButtonColor(5);
    }

    public void Button10Rounds(View view) {
        rounds = 10;
        changeButtonColor(10);
    }

    public void Button15Rounds(View view) {
        rounds = 15;
        changeButtonColor(rounds);
    }

    public void Button20Rounds(View view) {
        rounds = 20;
        changeButtonColor(rounds);
    }

    public void RoundsChosen(View view) {
        Intent intent = new Intent(this, SinglePlayerActivity.class);
        intent.putExtra("amountOfStatements", rounds);
        intent.putExtra("genre", chosenCategory);
        startActivity(intent);
    }

    private void changeButtonColor(int rounds) {

        switch (rounds) {
            case 5:
                button5Rounds.setBackgroundResource(R.drawable.pressed_button_shape);
                button10Rounds.setBackgroundResource(R.drawable.button_shape);
                button15Rounds.setBackgroundResource(R.drawable.button_shape);
                button20Rounds.setBackgroundResource(R.drawable.button_shape);
                break;
            case 10:
                button5Rounds.setBackgroundResource(R.drawable.button_shape);
                button10Rounds.setBackgroundResource(R.drawable.pressed_button_shape);
                button15Rounds.setBackgroundResource(R.drawable.button_shape);
                button20Rounds.setBackgroundResource(R.drawable.button_shape);
                break;
            case 15:
                button5Rounds.setBackgroundResource(R.drawable.button_shape);
                button10Rounds.setBackgroundResource(R.drawable.button_shape);
                button15Rounds.setBackgroundResource(R.drawable.pressed_button_shape);
                button20Rounds.setBackgroundResource(R.drawable.button_shape);
                break;
            case 20:
                button5Rounds.setBackgroundResource(R.drawable.button_shape);
                button10Rounds.setBackgroundResource(R.drawable.button_shape);
                button15Rounds.setBackgroundResource(R.drawable.button_shape);
                button20Rounds.setBackgroundResource(R.drawable.pressed_button_shape);
                break;
        }
    }
}

