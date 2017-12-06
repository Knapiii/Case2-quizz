package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.*;

public class DatabaseDataWorker {

    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;

    }

    /**
     * Möjliggör olika kategorier i databasen
     */
    public void insertCategories() {
        insertCategory("all_categories","All categories");
        insertCategory("food","Food");
        insertCategory("games","Games");
        insertCategory("geography","Geography");
        insertCategory("science","Science");
        insertCategory("sport","Sport");
        insertCategory("music","Music");
        insertCategory("own","Own statements");
    }

    private void insertCategory(String category_id, String category_title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID, category_id);
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
