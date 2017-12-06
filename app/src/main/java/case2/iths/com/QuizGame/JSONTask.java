package case2.iths.com.QuizGame;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JSONTask extends AsyncTask <Void, Void, String> {

    // private JSONTask listener;

    /**
     * Hämta datan
     */
    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL(https:knapiii.guthub.io/Case2-quizz);
            return getStringFromBuffer(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }

    }

    /**
     * Konverterar till sträng
     */
    private String getStringFromBuffer(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        String str = "";
        while (sc.hasNext()) {
            str += sc.next();
        }
        return str;
    }

    /**
     * Skicka tillbaka resultatet
     */
    @Override
    protected void onPostExecute(String s) {

        try {
            JSONArray array = new JSONArray(s); this.listener.onJSONResult(array);
        } catch (JSONException e) {
            e.printStackTrace();
            this.listener.onJSONResult(null);
        }
    }

    /**
     * Titta på resultatet
     */
    @Override
    public void onJSONResult(JSONArray json) {
        try {
            for(int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                System.out.println(jsonObject.getString("name")); }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

}