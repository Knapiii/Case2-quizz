package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

/**
 * Created by alvaro on 2017-11-17.
 */

public class HighscoresAdapter extends CursorAdapter {


    public HighscoresAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dropdown_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView = view.findViewById(R.id.textView);
        String highscore = cursor.getString(cursor.getColumnIndex(HighScoresInfoEntry.COLUMN_USER_ID))
                +" "+ cursor.getString(cursor.getColumnIndex(HighScoresInfoEntry.COLUMN_HIGHSCORE)) + " points";
        textView.setText(highscore);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
