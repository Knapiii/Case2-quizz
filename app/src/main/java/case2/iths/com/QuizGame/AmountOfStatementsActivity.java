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
    private boolean multiplayer;
    private SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_of_statements);
        savedSettings = new SavedSettings();
        initialize();
    }

    /**
     * Knapparna för de olika valen samt sätter och hämtar korrekta värden
     */
    public void initialize() {
        button5Statements = findViewById(R.id.button_statements_5);
        button10Statements = findViewById(R.id.button_statements_10);
        button15Statements = findViewById(R.id.button_statements_15);
        button20Statements = findViewById(R.id.button_statements_20);
        categoryTextView = findViewById(R.id.text_view_category);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        chosenCategory = getIntent().getStringExtra("category");
        categoryTextView.setText(chosenCategory);
    }

    /**
     * Om vi klickar på "5" i amount_of_statements.xml så ska en spelomgång vara med fem
     * påståenden och ett klickljud ska låta om ljudet är på. Även färgen på knappen ändras
     * tillfälligt när vi klickar på knappen.
     */
    public void Button5Rounds(View view) {
        savedSettings.giveSound(this);
        amountOfStatements = 5;
        changeButtonColor(5);
    }

    /**
     * Om vi klickar på "10" i amount_of_statements.xml så ska en spelomgång vara med tio
     * påståenden och ett klickljud ska låta om ljudet är på. Även färgen på knappen ändras
     * tillfälligt när vi klickar på knappen.
     */
    public void Button10Rounds(View view) {
        savedSettings.giveSound(this);
        amountOfStatements = 10;
        changeButtonColor(10);
    }

    /**
     * Om vi klickar på "15" i amount_of_statements.xml så ska en spelomgång vara med 15
     * påståenden och ett klickljud ska låta om ljudet är på. Även färgen på knappen ändras
     * tillfälligt när vi klickar på knappen.
     */
    public void Button15Rounds(View view) {
        savedSettings.giveSound(this);
        amountOfStatements = 15;
        changeButtonColor(amountOfStatements);
    }

    /**
     * Om vi klickar på "20" i amount_of_statements.xml så ska en spelomgång vara med 20
     * påståenden och ett klickljud ska låta om ljudet är på. Även färgen på knappen ändras
     * tillfälligt när vi klickar på knappen.
     */
    public void Button20Rounds(View view) {
        savedSettings.giveSound(this);
        amountOfStatements = 20;
        changeButtonColor(amountOfStatements);
    }

    /**
     * Metoden anropar på CountdownSplashActivity som gör att "3, 2, 1, GO" syns innan spelet
     * startas, samt en snabb förklaring att man ska svara det motsatta. Aktiviteten startas med
     * vald statement och kategori.
     */
    public void RoundsChosen(View view) {
        Intent intent = new Intent(this, CountdownSplashActivity.class);
        intent.putExtra("amountOfStatements", amountOfStatements);
        intent.putExtra("category", chosenCategory);
        intent.putExtra("multiplayer", multiplayer);
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

