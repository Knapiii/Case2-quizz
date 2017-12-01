package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class QuizableDBHelper extends SQLiteAssetHelper {

    public static final String TABLE = "Questions";
    public static final String KEY_ID = "id";
    public static final String CATEGORY = "Category";
    public static final String QUESTION = "Question";
    public static final String ANSWER = "Answer";
    private static final String OWN_STATEMENTS = "Own_Statements";
    private static final int USER_STATEMENTS = 1;
    private static final String DATABASE_NAME = "QuizableDB.db";
    private static final int DATABASE_VERSION = 1;


    public QuizableDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Returns all questions from Quizable.db
    public Cursor getQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    // Returns all user-made statements from Quizable.db
    public Cursor getUserMadeStatements() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + OWN_STATEMENTS + " = '" + USER_STATEMENTS + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    // Returns all questions from specified category
    public Cursor getQuestionsFromCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + CATEGORY + " = '" + category + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    // Adds user-made statements to Quizable.db
    public long insertStatement(String category, String statement, String answer) {
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY, category);
        cv.put(QUESTION, statement);
        cv.put(ANSWER, answer);
        cv.put(OWN_STATEMENTS, 1);
        return getWritableDatabase().insert(TABLE, null, cv);
    }





}