package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.CategoriesInfoEntry;

public class CategoriesCursorAdapter extends CursorAdapter {

    /**
     * Använder oss av cursor c
     */
    public CategoriesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    /**
     * Returnerar layout "dropdown_list_item"
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dropdown_list_item, parent, false);
    }

    /**
     * Texten ändras beroende på vald kategori
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.text_all_categories);
        String category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
        textView.setText(category);
    }

}
