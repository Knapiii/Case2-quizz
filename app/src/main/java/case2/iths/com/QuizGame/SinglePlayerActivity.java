package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


public class SinglePlayerActivity extends AppCompatActivity {

    private TextView points;
    private String genre;
    private TextView question;
    private int pointsCount, numDoneQuestions;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Integer> pastStatement = new ArrayList<>();
    private SavedSettings savedSettings;
    private String questionString, answerString;
    private QuizableDBHelper quizableDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        savedSettings = new SavedSettings();
        genre = getIntent().getStringExtra("genre");
        TextView headLine = findViewById(R.id.top_text_category);
        headLine.setText(genre);
        pointsCount = 0;
        numDoneQuestions = 0;
        questionString = "";
        answerString = "";
        quizableDBHelper = new QuizableDBHelper(this);
        points = findViewById(R.id.points);
        updatePoints();
        statements();
        question = findViewById(R.id.questionField);
        showRandomQuestion();
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE


    /**
     * Påståenden som ska slumpas i spelet
     */
    public void statements(){
        //questions.addAll(getAllWithCategory());
        setStatementsWithCategory(genre);
        //questions.add("Jesus Kristus");
    }

    /**
     * Gör så att alla påståenden slumpas
     */
    public void showRandomQuestion(){
        Random rand = new Random();
        int randId = rand.nextInt(questions.size());
        if (isStatementRepeated(randId)){
            showRandomQuestion();
            return;
        }
        pastStatement.add(randId);
        questionString = questions.get(randId);
        answerString = answers.get(randId);
        question.setText(questionString);
    }

    /**
     * När vi klickar på "sant" under spelet så ska man få ökad poäng om det stämmer
     */
    public void trueButtonPressed(View view){
        savedSettings.giveSound(this);
        if (isRoundOver()){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
            return;
        }

        numDoneQuestions++;
        pointsCount++;
        updatePoints();
        showRandomQuestion();
    }

    /**
     * När vi klickar på "falskt" under spelet så ska man få ökad poäng om det stämmer
     */
    public void falseButtonPressed(View view){
        savedSettings.giveSound(this);
        if (isRoundOver()){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
            return;
        }
        numDoneQuestions++;
        pointsCount++;
        updatePoints();
        showRandomQuestion();
    }

    /**
     * Gör så att poängen uppdateras under spelets gång
     */
    public void updatePoints(){
        points.setText("" + pointsCount);
    }

    /**
     * Kontrollerar om rundan är över eller inte
     */
    public boolean isRoundOver(){
        return numDoneQuestions == 4;
    }

    /**
     * Gör så att inte samma påstående kan upprepas under en runda
     */
    public boolean isStatementRepeated(int randId){
        for (int i = 0; i < pastStatement.size(); i++){
            if (pastStatement.get(i) == randId)
                return true;
        }
        return false;
    }

    public void setStatementsWithCategory(String cat){
        Cursor cursor = quizableDBHelper.getQuestionsFromCategory(cat);
        boolean success = cursor.moveToFirst();
        if (success){
            while (cursor.moveToNext()) {
                questions.add(cursor.getString(2));
                answers.add(cursor.getString(3));
            }
        }
    }
}
