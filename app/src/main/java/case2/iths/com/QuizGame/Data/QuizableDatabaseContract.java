package case2.iths.com.QuizGame.Data;

import android.provider.BaseColumns;

/**
 * A helper class that defines the Quizable database.
 */
public final class QuizableDatabaseContract {

    private QuizableDatabaseContract () {} // makes the class non-creatable

    /**
     * Defines highscores database table
     */
    public static final class HighScoresInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "highscores_info";
        public static final String COLUMN_CATEGORY_TITLE = "category_title";
        public static final String COLUMN_HIGHSCORE = "highscore";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_AMOUNT_OF_STATEMENTS = "amount_of_statements";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ";


        // CREATE TABLE highscores_info (category_id, highscore_points, user_id)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_TITLE + " TEXT NOT NULL, " +
                        COLUMN_HIGHSCORE + " INTEGER NOT NULL, " +
                        COLUMN_AMOUNT_OF_STATEMENTS + " INTEGER NOT NULL, " +
                        COLUMN_USER_ID  + " TEXT)";
    }

    /**
     * Defines categories database table
     */

    public static final class CategoriesInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "categories_info";
        public static final String COLUMN_CATEGORY_TITLE = "category_title";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ";
        // CREATE TABLE categories_info (category_id, category_text)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_TITLE + " TEXT UNIQUE NOT NULL)";
    }

    /**
     * Defines profiles database table
     */

    public static final class UserInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_USERNAME = "username";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ";
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_USERNAME + " TEXT UNIQUE NOT NULL)";
    }

}

