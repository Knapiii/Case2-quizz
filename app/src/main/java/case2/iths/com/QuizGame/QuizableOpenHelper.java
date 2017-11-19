package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        db.execSQL(QuizableDatabaseContract.CategoriesInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(QuizableDatabaseContract.HighScoresInfoEntry.SQL_CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
