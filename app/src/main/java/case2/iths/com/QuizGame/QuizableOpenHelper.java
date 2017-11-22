package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;
import static case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

/**
 * Created by alvaro on 2017-11-19.
 */

public class QuizableOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Quizable.db";
    public static final int DATABASE_VERSION = 1;

    public QuizableOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CategoriesInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(HighScoresInfoEntry.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getHighScores(String category_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + HighScoresInfoEntry.TABLE_NAME + " WHERE " + HighScoresInfoEntry.COLUMN_CATEGORY_ID + " = '" + category_id + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;


    }


    /*  //Takes data from highscore_info table in the Quizable database.

    private void loadFromDatabase(QuizableOpenHelper dbHelper) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE};
        Cursor categoryCursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns, null, null, null, null, null);

      //  loadCategoriesFromDatabase(categoryCursor);

      //  String[] highscoreColumns = {HighScoresInfoEntry.COLUMN_CATEGORY_ID, HighScoresInfoEntry.COLUMN_HIGHSCORE, HighScoresInfoEntry.COLUMN_USER_ID};
       // Cursor highscoreCursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoreColumns, null, null, null, null, null);

      //  loadHighscoresFromDatabase(highscoreCursor);
        db.close();

    }*/

  /*  //This method add data from the database to our categories array-list
    private ArrayList<String> loadCategoriesFromDatabase(Cursor cursor) {

        //int categoryIdPos = cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_ID);
        int categoryTitlePos = cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE);

        boolean success = cursor.moveToFirst();
        if (!success)
            return null;

        do{
            //String categoryId = cursor.getString(categoryIdPos);
            String categoryTitle = cursor.getString(categoryTitlePos);

   //         categories.add(categoryTitle);

        }while(cursor.moveToNext());

        return null;
    }*/

 /*   private ArrayList<Integer> loadHighscoresFromDatabase(Cursor cursor) {

        int categoryIdPos = cursor.getColumnIndex(HighScoresInfoEntry.COLUMN_CATEGORY_ID);
        int highscorePos = cursor.getColumnIndex(HighScoresInfoEntry.COLUMN_HIGHSCORE);
        int userPos = cursor.getColumnIndex(HighScoresInfoEntry.COLUMN_USER_ID);

        boolean success = cursor.moveToFirst();
        if(!success)
            return null;

        do {
            String categoryId = cursor.getString(categoryIdPos);
            int highscore = cursor.getInt(highscorePos);
            String userId = cursor.getString(userPos);

            highscores.add(highscore);
            users.add(userId);

        } while (cursor.moveToNext());

        return highscores;
    }*/

}