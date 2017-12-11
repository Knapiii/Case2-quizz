package case2.iths.com.QuizGame.Data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.CategoriesInfoEntry;

import static case2.iths.com.QuizGame.Data.QuizableDatabaseContract.*;

public class DatabaseDataWorker {

    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;

    }

    /**
     * Möjliggör olika kategorier i databasen
     */
    public void insertCategories() {
        insertCategory("All highscores");
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
