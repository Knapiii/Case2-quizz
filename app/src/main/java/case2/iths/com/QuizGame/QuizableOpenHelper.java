package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import case2.iths.com.QuizGame.QuizableDatabaseContract.UserInfoEntry;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;
import static case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

/**
 * Created by alvaro on 2017-11-19.
 */

public class QuizableOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Quizable.db";
    public static final int DATABASE_VERSION = 21;

    public QuizableOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates 3 database tables
     * Adds default categories to the categories table in the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(HighScoresInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(UserInfoEntry.SQL_CREATE_TABLE);
        DatabaseDataWorker databaseDataWorker = new DatabaseDataWorker(db);
        databaseDataWorker.insertCategories();
        databaseDataWorker.insertDefaultPlayers();
    }

    /**
     * onUpgrade if new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            db.execSQL(HighScoresInfoEntry.SQL_DELETE_TABLE + HighScoresInfoEntry.TABLE_NAME);
            db.execSQL(CategoriesInfoEntry.SQL_DELETE_TABLE + CategoriesInfoEntry.TABLE_NAME);
            db.execSQL(UserInfoEntry.SQL_DELETE_TABLE + UserInfoEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    /**
     * Returns highscores for the specific category.
     */
    public Cursor getHighScoresByCategory(String categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = HighScoresInfoEntry.COLUMN_CATEGORY_TITLE + " = ?";
        String[] selectionArgs = {categoryId};
        String[] highscoresColumns = {
                HighScoresInfoEntry.COLUMN_HIGHSCORE,
                HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS,
                HighScoresInfoEntry.COLUMN_CATEGORY_TITLE,
                HighScoresInfoEntry.COLUMN_USER_ID,
                HighScoresInfoEntry._ID
        };
        Cursor cursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoresColumns, selection, selectionArgs, null, null, HighScoresInfoEntry.COLUMN_HIGHSCORE + " DESC");
        return cursor;
    }

    /**
     * Get all highscores
     */
    public Cursor getAllHighScores() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] highscoresColumns = {
                HighScoresInfoEntry.COLUMN_HIGHSCORE,
                HighScoresInfoEntry.COLUMN_CATEGORY_TITLE,
                HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS,
                HighScoresInfoEntry.COLUMN_USER_ID,
                HighScoresInfoEntry._ID
        };

        Cursor cursor = db.query(HighScoresInfoEntry.TABLE_NAME, highscoresColumns, null, null, null, null, HighScoresInfoEntry.COLUMN_HIGHSCORE + " DESC");
        return cursor;
    }

    /**
     * Use this method to add a new highscore to the database highscore table
     */
    public void insertHighscore(String categoryTitle, int score, int amountOfStatements, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HighScoresInfoEntry.COLUMN_HIGHSCORE, score);
        contentValues.put(HighScoresInfoEntry.COLUMN_CATEGORY_TITLE, categoryTitle);
        contentValues.put(HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS, amountOfStatements);
        contentValues.put(HighScoresInfoEntry.COLUMN_USER_ID, userId);
        long newRowId = db.insert(HighScoresInfoEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * Returns all categories
     */
    public Cursor loadCategoriesData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] categoryColumns = {
                CategoriesInfoEntry.COLUMN_CATEGORY_TITLE,
                CategoriesInfoEntry._ID
        };

        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,
                null, null, null, null, null);

        return cursor;
    }

    /**
     * Cursur for getCategoriesCreateStatement
     */
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
                CategoriesInfoEntry._ID
        };



        Cursor cursor = db.query(CategoriesInfoEntry.TABLE_NAME, categoryColumns,selection, selectionArgs, null, null, null);

        return cursor;
    }

    /**
     * Adds a user to the databse
     */
    public void addUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfoEntry.COLUMN_USERNAME, username);
        long newRowId = db.insert(UserInfoEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * Cursur get all profiles
     */
    public Cursor getAllProfiles() {
        SQLiteDatabase db = getReadableDatabase();
        String[] profileColumns = {
                UserInfoEntry.COLUMN_USERNAME,
                UserInfoEntry._ID
        };

        Cursor cursor = db.query(UserInfoEntry.TABLE_NAME,
                profileColumns,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    public Cursor getChooseProfile() {
        SQLiteDatabase db = getReadableDatabase();
        String selection = UserInfoEntry.COLUMN_USERNAME;
        String[] selectionArgs = {"Choose profile"};

        String[] userColumns = {
                UserInfoEntry.COLUMN_USERNAME,
                UserInfoEntry._ID
        };

        Cursor cursor = db.query(UserInfoEntry.TABLE_NAME, userColumns, selection, selectionArgs, null, null, null);
        cursor.moveToNext();

        return cursor;

    }

}