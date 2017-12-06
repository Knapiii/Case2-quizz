package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import case2.iths.com.QuizGame.QuizableDatabaseContract.UserInfoEntry;

/**
 * Created by alvaro on 2017-12-03.
 */

public class ProfilesCursorAdapter extends CursorAdapter {
    public ProfilesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dropdown_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.text_all_categories);
        String userName = cursor.getString(cursor.getColumnIndex(UserInfoEntry.COLUMN_USERNAME));
        textView.setText(userName);

    }
}
