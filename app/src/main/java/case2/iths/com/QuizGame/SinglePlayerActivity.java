package case2.iths.com.QuizGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klassen SplashActivity ärver AppCompatActivity som innebär att klassen ärver
 * funktioner som möjliggör att appen stödjer flera android-versioner.
 */
public class SinglePlayerActivity extends AppCompatActivity {

    private String genre;
    private TextView headLine, points, question;
    private int pointsCount, numDoneQuestions;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<Integer> pastStatement = new ArrayList<>();
    private SavedSettings savedSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplayer_game);
        savedSettings = new SavedSettings();
        genre = getIntent().getStringExtra("genre");
        headLine = findViewById(R.id.top_text_category);
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
        questions.add("aaa");
        questions.add("bbb");
        questions.add("ccc");
        questions.add("ddd");
        questions.add("eee");
        questions.add("fff");
        questions.add("ggg");
        questions.add("hhh");
        questions.add("iii");
        questions.add("jjj");
        questions.add("kkk");
        questions.add("lll");
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

}
