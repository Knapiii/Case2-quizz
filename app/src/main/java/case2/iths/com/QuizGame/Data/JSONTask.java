package case2.iths.com.QuizGame.Data;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import case2.iths.com.QuizGame.Activities.AmountOfStatementsActivity;
import case2.iths.com.QuizGame.R;

public class JSONTask extends AsyncTask <Void, Void, String> {

    private QuizableDBHelper s;
    private Context context;

    /**
     * Context
     */
    public JSONTask (Context context) {
        this.context = context;
        s = new QuizableDBHelper(context);
    }

    /**
     * Hämtar datan, ansluter till servern
     */
    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL("https://knapiii.github.io/Case2-quizz/app/src/main/res/Expansion.JSON");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return getStringFromBuffer(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }

    }

    /**
     * Konverterar till sträng
     */
    private String getStringFromBuffer(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        String str = "";
        while (sc.hasNext()) {
            str += sc.nextLine();
        }
        return str;
    }

    /**
     * Skickar tillbaka resultatet
     */
     @Override
     protected void onPostExecute(String s) {

        try {
            JSONArray array = new JSONArray(s);
            onJSONResult(array);
            Toast.makeText(context, "Expansion download complete.", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
    * Tittar på resultatet och kopplar till databasen
    */
    public void onJSONResult (JSONArray json) {

        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                String category = jsonObject.getString("category");
                String statement = jsonObject.getString("statement");
                String answer = String.valueOf(jsonObject.getBoolean("answer"));
                s.insertStatement(category, statement, answer);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

}
