package case2.iths.com.QuizGame.Data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.CategoriesInfoEntry;

import static case2.iths.com.QuizGame.Data.QuizableDatabaseContract.*;

/**
 * Helper class that adds defaut categories and players to the database
 */
public class DatabaseDataWorker {

    private SQLiteDatabase mDb;


    /**
     * Constructor
     * @param db the database
     */
    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;

    }

    /**
     * Adds default categories to the database
     */
    public void insertCategories() {
        insertCategory("All categories");
        insertCategory("Food");
        insertCategory("Games");
        insertCategory("Geography");
        insertCategory("Science");
        insertCategory("Sport");
        insertCategory("Music");
        insertCategory("Own statements");
    }

    private void insertCategory(String category_title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE, category_title);
        long id = mDb.insert(CategoriesInfoEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * Adds 2 default players to the database
     */
    public void insertDefaultPlayers() {
        insertPlayer("Player 1");
        insertPlayer("Player 2");
    }

    private void insertPlayer(String player) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfoEntry.COLUMN_USERNAME, player);

        long id = mDb.insert(UserInfoEntry.TABLE_NAME, null, contentValues);


    }

}
