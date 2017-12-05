package case2.iths.com.QuizGame;

import android.provider.BaseColumns;

public final class QuizableDatabaseContract {

    private QuizableDatabaseContract () {} // makes the class non-creatable

    public static final class HighScoresInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "highscores_info";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_HIGHSCORE = "highscore";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_AMOUNT_OF_STATEMENTS = "amount_of_statements";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ";
        // CREATE TABLE highscores_info (category_id, highscore_points, user_id)

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
                        COLUMN_HIGHSCORE + " INTEGER NOT NULL, " +
                        COLUMN_AMOUNT_OF_STATEMENTS + " INTEGER NOT NULL, " +
                        COLUMN_USER_ID  + " TEXT)";
    }

    public static final class CategoriesInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "categories_info";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_CATEGORY_TITLE = "category_title";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ";
        // CREATE TABLE categories_info (category_id, category_text)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_ID + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_CATEGORY_TITLE + " TEXT UNIQUE NOT NULL)";
    }

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

