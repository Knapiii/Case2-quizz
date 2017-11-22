package case2.iths.com.QuizGame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SinglePlayerActivity extends AppCompatActivity {

    private TextView points;
    private TextView question;
    private int pointsCount, numDoneQuestions;
    private List<String> questions = new ArrayList<>();
    private ArrayList<Integer> pastStatement = new ArrayList<>();
    private SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        savedSettings = new SavedSettings();
        String genre = getIntent().getStringExtra("genre");
        TextView headLine = findViewById(R.id.top_text_category);
        headLine.setText(genre);
        pointsCount = 0;
        numDoneQuestions = 0;
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
        questions.add("Jesus Kristus");
        questions.add("Vad trött jag blir");
        questions.add("På allt");
        questions.add("Skit som går fel hela tiden");
        questions.add("Går och lägger mig");
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
        String randQuestion = questions.get(randId);
        question.setText(randQuestion);
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
/*
    public List<Questions> getAllWithCategory(){
        List<Questions> questionsList = new ArrayList<>();
        QuizableDBHelper helper = new QuizableDBHelper(this);
        helper.getQuestions();

    }*/
}
