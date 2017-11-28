package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class SinglePlayerActivity extends AppCompatActivity {

    private TextView pointsView, question, secondsView, headLine, statementsLeftView;
    private String genre;
    private int points, numDoneQuestions, seconds, amountOfStatements, updateStatementsLeft;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Integer> pastStatement = new ArrayList<>();
    private SavedSettings savedSettings;
    private String questionString, answerString;
    private QuizableDBHelper quizableDBHelper;
    private CountDownTimer cdTimer;
    private boolean multiplayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        savedSettings = new SavedSettings();
        Intent intent = getIntent();
        amountOfStatements = intent.getIntExtra("amountOfStatements", 5);
        initialize();
        genre = getIntent().getStringExtra("genre");
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        headLine.setText(genre);
        quizableDBHelper = new QuizableDBHelper(this);
        updatePoints();
        statements();
        cdTimer = new CountDownTimer(3000, 100) {
            @Override
            public void onTick(long l) {
                if (l > 2000) {
                    secondsView.setText("3");
                    seconds = 3;
                } else if (l < 1000) {
                    secondsView.setText("1");
                    seconds = 1;
                } else {
                    secondsView.setText("2");
                    seconds = 2;
                }
            }
            @Override
            public void onFinish() {
                points--;
                updatePoints();
                showRandomQuestion();
            }
        }.start();
        showRandomQuestion();
    }

    public void initialize() {
        question = findViewById(R.id.questionField);
        secondsView = findViewById(R.id.display_seconds);
        pointsView = findViewById(R.id.points);
        headLine = findViewById(R.id.top_text_category);
        statementsLeftView = findViewById(R.id.text_statements_left);

        points = 0;
        updateStatementsLeft = amountOfStatements;
        numDoneQuestions = 0;
        questionString = "";
        answerString = "";

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE


    /**
     * Påståenden som ska slumpas i spelet
     */
    public void statements() {
        setStatementsWithCategory(genre);
    }

    /**
     * Gör så att alla påståenden slumpas
     */
    public void showRandomQuestion() {
        cdTimer.cancel();
        if (!isRoundOver()) {
            cdTimer.start();
            Random rand = new Random();
            int randId = rand.nextInt(questions.size());
            if (isStatementRepeated(randId)) {
                showRandomQuestion();
                return;
            }
            pastStatement.add(randId);
            questionString = questions.get(randId);
            answerString = answers.get(randId);
            question.setText(questionString);
            numDoneQuestions++;
            updateStatementsLeft--;
        } else
            startResultActivity();
    }

    /**
     * När vi klickar på "sant" under spelet så ska man få ökad poäng om det stämmer eller minskad poäng om det är fel svar.
     */
    public void trueButtonPressed(View view) {
        savedSettings.giveSound(this);
        if (answerString.equalsIgnoreCase("Sant"))
            points += seconds;
        if (isRoundOver()) {
            cdTimer.cancel();
            startResultActivity();
            return;
        }
        updatePoints();
        showRandomQuestion();
    }

    /**
     * När vi klickar på "falskt" under spelet så ska man få ökad poäng om det stämmer eller minskad poäng om det är fel svar.
     */
    public void falseButtonPressed(View view) {
        savedSettings.giveSound(this);
        if (answerString.equalsIgnoreCase("Falskt"))
            points += seconds;
        if (isRoundOver()) {
            cdTimer.cancel();
            startResultActivity();
            return;
        }
        updatePoints();
        showRandomQuestion();
    }

    /**
     * Startar ResultActivity efter fem frågor har visats
     */
    public void startResultActivity() {
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("points", points);
        intent.putExtra("category", genre);
        intent.putExtra("amountOfStatements", amountOfStatements);
        startActivity(intent);
    }

    /**
     * Gör så att poängen uppdateras under spelets gång
     */
    public void updatePoints() {
        if (points < 0) {
            points = 0;
        }
        pointsView.setText("" + points);
        statementsLeftView.setText("" + updateStatementsLeft);
    }

    /**
     * Kontrollerar om rundan är över eller inte
     */
    public boolean isRoundOver() {
        return numDoneQuestions == amountOfStatements;
    }


    /**
     * Gör så att inte samma påstående kan upprepas under en runda
     */
    public boolean isStatementRepeated(int randId) {
        for (int i = 0; i < pastStatement.size(); i++) {
            if (pastStatement.get(i) == randId)
                return true;
        }
        return false;
    }

    public void setStatementsWithCategory(String cat) {
        Cursor cursor = quizableDBHelper.getQuestionsFromCategory(cat);
        boolean success = cursor.moveToFirst();
        if (success) {
            while (cursor.moveToNext()) {
                questions.add(cursor.getString(2));
                answers.add(cursor.getString(3));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();

    }
}
