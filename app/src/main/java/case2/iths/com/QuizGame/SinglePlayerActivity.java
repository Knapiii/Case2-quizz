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
    private String category;
    private int points, numDoneQuestions, seconds, amountOfStatements, updateStatementsLeft;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Integer> pastStatement = new ArrayList<>();
    private SavedSettings savedSettings;
    private String questionString, answerString;
    private QuizableDBHelper quizableDBHelper;
    private CountDownTimer cdTimer;
    private boolean multiplayer;

    private int p1Points;
    private int p2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        savedSettings = new SavedSettings();
        Intent intent = getIntent();
        category = getIntent().getStringExtra("category");
        amountOfStatements = intent.getIntExtra("amountOfStatements", 5);
        multiplayer = getIntent().getBooleanExtra("multiplayer", false);
        quizableDBHelper = new QuizableDBHelper(this);
        initialize();
        statements();
        headLine.setText(category);
        updatePoints();
        cdTimer = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long l) {
                if (l > 4000) {
                    secondsView.setText("5");
                    seconds = 5;
                } else if (l < 1000) {
                    secondsView.setText("1");
                    seconds = 1;
                } else if (l < 4000 && l > 3000){
                    secondsView.setText("4");
                    seconds = 4;
                } else if (l < 3000 && l > 2000){
                    secondsView.setText("3");
                    seconds = 3;
                } else if (l < 2000 && l > 1000){
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

        if (getIntent().getIntExtra("p1points", 100) != 100)
            p1Points = getIntent().getIntExtra("p1points", 0);
        points = 0;
        updateStatementsLeft = amountOfStatements;
        numDoneQuestions = 0;
        questionString = "";
        answerString = "";

    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: FIX MULTIPLAYER
    // TODO:        - MORE TIME YOU USE


    /**
     * Påståenden som ska slumpas i spelet
     */
    public void statements() {
        setStatementsWithCategory(category);
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
        if (answerString.equalsIgnoreCase("True"))
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
        if (answerString.equalsIgnoreCase("False"))
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
     * TODO: Här kan man nog fixa multiplayer funktionen
     * TODO: genom att istället anropa CountDownActivity igen och spara första spelarens värden
     */
    public void startResultActivity() {
        if (multiplayer){
            Intent multiIntent = new Intent(this, CountdownSplashActivity.class);
            multiIntent.putExtra("p1points", points);
            multiIntent.putExtra("p1category", genre);
            multiIntent.putExtra("p1amountStatements", amountOfStatements);
            multiIntent.putExtra("multiplayer", multiplayer);
            startActivity(multiIntent);
            return;
        }
        savedSettings.giveSound(this);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("points", points);
        intent.putExtra("category", category);
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
        if (cat.equals("Own")) {
            Cursor cursor = quizableDBHelper.getUserMadeStatements();
            boolean success = cursor.moveToFirst();
            if (success) {
                while (cursor.moveToNext()) {
                    questions.add(cursor.getString(2));
                    answers.add(cursor.getString(3));
                }
            }
        } else {
            Cursor cursor = quizableDBHelper.getQuestionsFromCategory(cat);
            boolean success = cursor.moveToFirst();
            if (success) {
                while (cursor.moveToNext()) {
                    questions.add(cursor.getString(2));
                    answers.add(cursor.getString(3));
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cdTimer.cancel();

    }
}
