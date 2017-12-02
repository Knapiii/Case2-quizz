package case2.iths.com.QuizGame;

import android.content.ContentValues;
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
    public static final int DATABASE_VERSION = 14;

    public QuizableOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creates 3 database tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CategoriesInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(HighScoresInfoEntry.SQL_CREATE_TABLE);

        // Adds default categories to the categories table in the database
        DatabaseDataWorker databaseDataWorker = new DatabaseDataWorker(db);
        databaseDataWorker.insertCategories();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {

            db.execSQL(HighScoresInfoEntry.SQL_DELETE_TABLE + HighScoresInfoEntry.TABLE_NAME);
            db.execSQL(CategoriesInfoEntry.SQL_DELETE_TABLE + CategoriesInfoEntry.TABLE_NAME);

            onCreate(db);

        }
    }

    // returns highscores for the specific category
    public Cursor getHighScoresByCategory(String categoryId) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = HighScoresInfoEntry.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = {categoryId};
        String[] highscoresColumns = {
                HighScoresInfoEntry.COLUMN_CATEGORY_ID,
                HighScoresInfoEntry.COLUMN_HIGHSCORE,
                HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS,
                HighScoresInfoEntry.COLUMN_USER_ID,
                HighScoresInfoEntry._ID
        };
        Cursor cursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoresColumns, selection, selectionArgs, null, null, HighScoresInfoEntry.COLUMN_HIGHSCORE + " DESC");
        return cursor;

    }

    public Cursor getAllHighScores() {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] highscoresColumns = {
                HighScoresInfoEntry.COLUMN_CATEGORY_ID,
                HighScoresInfoEntry.COLUMN_HIGHSCORE,
                HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS,
                HighScoresInfoEntry.COLUMN_USER_ID,
                HighScoresInfoEntry._ID
        };

        Cursor cursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoresColumns, null, null, null, null, HighScoresInfoEntry.COLUMN_HIGHSCORE + " DESC");
        return cursor;

    }

    // Use this method to add a new highscore to the database highscore table;

    public void insertHighscore(String categoryId, int score, int amountOfStatements, String userId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HighScoresInfoEntry.COLUMN_CATEGORY_ID, categoryId);
        contentValues.put(HighScoresInfoEntry.COLUMN_HIGHSCORE, score);
        contentValues.put(HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS, amountOfStatements);
        contentValues.put(HighScoresInfoEntry.COLUMN_USER_ID, userId);

        long newRowId = db.insert(HighScoresInfoEntry.TABLE_NAME, null, contentValues);

    }

    // returns all categeories

    public Cursor loadCategoriesData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry.COLUMN_CATEGORY_ID,
                CategoriesInfoEntry._ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,
                null, null, null, null, null);

        return cursor;

    }

    public Cursor getCategoriesCreateStatement() {
        SQLiteDatabase db = getReadableDatabase();
        String selection = CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ? OR " +
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ? OR " +
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ? OR " +
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ? OR " +
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ? OR " +
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE + " = ?";

        String[] selectionArgs = {"Food", "Games", "Geography", "Science", "Sport", "Music" };

        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry._ID,
                CategoriesInfoEntry.COLUMN_CATEGORY_ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,selection, selectionArgs, null, null, null);


        return cursor;

    }

}