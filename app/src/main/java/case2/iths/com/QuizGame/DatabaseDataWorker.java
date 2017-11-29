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

    public void insertCategories() {
        insertCategory("all_categories","All categories");
        insertCategory("food","Food");
        insertCategory("games","Games");
        insertCategory("geography","Geography");
        insertCategory("science","Science");
        insertCategory("sport","Sport");
        insertCategory("music","Music");
        insertCategory("own_statements","Own statements");

    }

    public void insertQuestions() {
        insertQuestions("Zlatan Ibrahimovic är en hockeyspelare1", "true", "sport");

    }

    private void insertQuestions(String statement, String answer, String category) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(OwnStatementsEntry.COLUMN_STATEMENT, statement);
        contentValues.put(OwnStatementsEntry.COLUMN_STATEMENT_ANSWER, answer);
        contentValues.put(OwnStatementsEntry.COLUMN_CATEGORY_ID, category);

        long id = mDb.insert(OwnStatementsEntry.TABLE_NAME, null, contentValues);


    }


    private void insertCategory(String category_id, String category_title) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID, category_id);
   //     contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_ID_SV, category_id_sv);
        contentValues.put(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE, category_title);
        long id = mDb.insert(CategoriesInfoEntry.TABLE_NAME, null, contentValues);

    }

    
}
