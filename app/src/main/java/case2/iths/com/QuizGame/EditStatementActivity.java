package case2.iths.com.QuizGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditStatementActivity extends AppCompatActivity {
    public Spinner spinner;
    private Button buttonTrue, buttonFalse;
    private boolean buttonTrueClicked;
    private EditText editStatement;
    private String statement;
    private String category;
    private String answer;
    private QuizableOpenHelper mDbOpenHelper;
    private SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_statement);
        buttonTrueClicked = false;
        initialize();
    }

    public void initialize(){
        //TextViews
        buttonTrue = findViewById(R.id.togglebutton_edit_true);
        buttonFalse = findViewById(R.id.togglebutton_edit_false);
    }

    public void editButtonTrueClicked(View view) {
        savedSettings.giveSound(this);

        buttonTrueClicked = true;
        changeButtonColor();
        answer = "true";
    }

    public void editButtonFalseClicked(View view) {
        savedSettings.giveSound(this);
        buttonTrueClicked = false;
        changeButtonColor();
        answer = "false";
    }

    public void editButtonEditClicked(View view) {
        savedSettings.giveSound(this);

    }

    private void editStatement(){

        editStatement = findViewById(R.id.editText_edit_statement);

    }


    private void changeButtonColor() {
        if (buttonTrueClicked) {
            buttonTrue.setBackgroundResource(R.drawable.pressed_button_shape);
            buttonFalse.setBackgroundResource(R.drawable.button_shape);
        } else {
            buttonTrue.setBackgroundResource(R.drawable.button_shape);
            buttonFalse.setBackgroundResource(R.drawable.pressed_button_shape);
        }
    }
}

