package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Daniel on 2017-11-20.
 */

public class QuizableDBHelper extends SQLiteAssetHelper {

    private static final String TABLE = "Questions";
    private static final String KEY_ID = "id";
    private static final String CATEGORY = "Category";
    private static final String QUESTION = "Question";
    private static final String ANSWER = "Answer";
    private static final String DATABASE_NAME = "QuizableDB.db";
    private static final int DATABASE_VERSION = 1;


    public QuizableDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getQuestions() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor getQuestionsFromCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + CATEGORY + " = '" + category + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}