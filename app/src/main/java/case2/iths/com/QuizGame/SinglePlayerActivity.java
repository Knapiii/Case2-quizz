package case2.iths.com.QuizGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity {

    private String genre;
    private TextView headLine, points, question;
    private int pointsCount, numDoneQuestions;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<Integer> pastQuestions = new ArrayList<>();
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
        addQuestions();
        question = findViewById(R.id.questionField);
        showRandomQuestion();
    }

    // TODO: 2017-11-14 Lägg till:
    // TODO: CHOOSE BEETWEEN ALL CATEGORIES
    // TODO: TIMER FUNCTION
    // TODO:        - MORE TIME YOU USE

    public void addQuestions(){
        questions.add("Zlatan spelar hockey");
        questions.add("Björn Borg säljer brädspel");
        questions.add("aaaaaa");
        questions.add("bbbbbb");
        questions.add("ccccccccc");
        questions.add("ddddddd");
        questions.add("eeeeeeee");
        questions.add("fffffff");
        questions.add("gggggggg");
        questions.add("hhhhhhhh");
        questions.add("iiiiii");
        questions.add("jjjjjjjj");
        questions.add("kkkkk");
    }

    public void showRandomQuestion(){
        Random rand = new Random();
        int randId = rand.nextInt(questions.size());
        if (isQuestionRepeated(randId)){
            showRandomQuestion();
            return;
        }
        pastQuestions.add(randId);
        String randQuestion = questions.get(randId);
        question.setText(randQuestion);
    }

    public void trueButtonPressed(View view){
        savedSettings.checkSoundOn(this);
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

    public void falseButtonPressed(View view){
        savedSettings.checkSoundOn(this);
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

    public void updatePoints(){
        points.setText("" + pointsCount);
    }

    public boolean isRoundOver(){
        return numDoneQuestions == 4;
    }

    public boolean isQuestionRepeated(int randId){
        for (int i = 0; i < pastQuestions.size(); i++){
            if (pastQuestions.get(i) == randId)
                return true;
        }
        return false;
    }

}
