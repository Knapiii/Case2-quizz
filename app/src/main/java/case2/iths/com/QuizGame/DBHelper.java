package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna on 2017-11-15.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "MyDataBase", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        String sql = "CREATE TABLE Highscores ( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "points INTEGER);";

        db.execSQL(sql);

        // Create table med SQ
        // Kör kod som lägge till nya rader
        */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addHighScore(String name, int points) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("points", points);
        long id = db.insert("Highscores",null,contentValues);
        db.close();
    }

    public List<HighScore> getAllHighScores() {
        List<HighScore> highScoreList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("Highscores",null,null,null,null,null,null);

        boolean success = c.moveToFirst();
        if( success) {
            //Loop for every row in the table
            do {
                HighScore highScore = new HighScore();
                highScore.id = c.getLong(0);
                highScore.name = c.getString(1);
                highScore.points = c.getInt(2);

                highScoreList.add(highScore);
                Log.d("SQLiteAndroid", highScore.id + ","
                        + highScore.name + "," + highScore.points);
            } while (c.moveToNext());  //Move cursor to next row. Returns false if end of data
        }
        db.close();
        return  highScoreList;
    }

    public boolean updateHighScore(HighScore highScore){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", highScore.name);
        cv.put("points", highScore.points);

        String selection = "id=?";

        String[] selectionArgs = new String[]{Long.toString(highScore.id)};

        int rows = db.update("Highscores",cv, selection, selectionArgs);

        db.close();
        return  rows == 1;
    }

    public boolean deleteHighScore(HighScore highScore) {
        return deleteHighScore(highScore.id);
    }

    public boolean deleteHighScore(long id) {
        SQLiteDatabase db = getWritableDatabase();

        String [] selectionArgs = new String[]{Long.toString(id)};

        //To remove all rows from table, set whereClause to "1"
        int result = db.delete("Highscores","id=?", selectionArgs);

        db.close();

        return result == 1;
    }

    public long getHighScoreCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, "HighScores");
        db.close();
        return cnt;
    }

    public List<Questions> getAllWithCategory(String category){
        List<Questions> questionsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sel = "Category=?";
        String[] selectionArgs = new String[] {category};

        Cursor c = db.query("statements", null,
                sel, selectionArgs, null,
                null, null);

        boolean success = c.moveToFirst();
        if (success){
            do {
                Questions questions = new Questions();
                questions.statement = c.getString(2);
                questions.answer = c.getString(3);

                questionsList.add(questions);
            } while (c.moveToNext());
        }
        db.close();
        return questionsList;
    }


    /**
     * Searches the database for all rows matching name == name
     * @param name
     * @return
     */
    /*
    public List<HighScore> getAllWithName(String name) {
        List<HighScore> highScoreList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = "name=?";
        String[] selectionArgs = new String[] {name};

        Cursor c = db.query("Highscores",null,
                selection, selectionArgs,
                null,null,null);

        boolean success = c.moveToFirst();
        if( success) {
            //Loop for every row in the table
            do {
                HighScore highScore = new HighScore();
                highScore.id = c.getLong(0);
                highScore.name = c.getString(1);
                highScore.points = c.getInt(2);

                highScoreList.add(highScore);
                Log.d("SQLiteAndroid", highScore.id + ","
                        + highScore.name + "," + highScore.points);
            } while (c.moveToNext());  //Move cursor to next row. Returns false if end of data
        }
        db.close();
        return  highScoreList;
    }
     */
}