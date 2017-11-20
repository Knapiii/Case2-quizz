package case2.iths.com.QuizGame;

import android.provider.BaseColumns;

/**
 * Created by alvaro on 2017-11-19.
 */

public final class QuizableDatabaseContract {

    private QuizableDatabaseContract () {} // makes the class non-creatable


    public static final class HighScoresInfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "highscores_info";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_HIGHSCORE = "highscore";
        public static final String COLUMN_USER_ID = "user_id";

        // CREATE TABLE highscores_info (category_id, highscore_points, user_id)

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
                        COLUMN_HIGHSCORE + " INTEGER NOT NULL, " +
                        COLUMN_USER_ID  + " TEXT)";

    }

    public static final class CategoriesInfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "categories_info";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_CATEGORY_TITLE = "category_title";

        // CREATE TABLE categories_info (category_id, category_text)

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
                        COLUMN_CATEGORY_TITLE + " TEXT NOT NULL)";


    }

}

