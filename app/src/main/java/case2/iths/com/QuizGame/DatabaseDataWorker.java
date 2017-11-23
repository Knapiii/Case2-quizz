package case2.iths.com.QuizGame;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

/**
 * Created by alvaro on 2017-11-23.
 */

public class DatabaseDataWorker {

    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;

    }

    public void insertCategories() {
        insertCategory("all_categories", "All categories");
        insertCategory("sport", "Sport");
        insertCategory("culture", "Culture");
        insertCategory("geography", "Geography");
        insertCategory("games", "Games");
        insertCategory("science", "Science");
        insertCategory("music", "Music");
        insertCategory("own_questions", "Own questions");

    }


    private void insertCategory(String category_id, String category_title) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID, category_id);
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE, category_title);
        long id = mDb.insert(CategoriesInfoEntry.TABLE_NAME, null, contentValues);

    }
}
