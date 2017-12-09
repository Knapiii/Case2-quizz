package case2.iths.com.QuizGame.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import case2.iths.com.QuizGame.R;

import static case2.iths.com.QuizGame.Data.QuizableDatabaseContract.CategoriesInfoEntry;


/**
 *  Adapter that exposes data from a Cursor to a ListView widget.
 */
public class CategoriesCursorAdapter extends CursorAdapter {


    /**
     * Constructor
     * @param context - The context
     * @param c- The cursor from which to get the data
     */
    public CategoriesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }


    /**
     * Makes a new view to hold the data pointed to by cursor
     * @param context- Interface to application's global information
     * @param cursor- the cursor from which to get the data. The cursor is already moved to the correct position
     * @param parent- the parent to which the new view is attached to
     * @return- the newly created view
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dropdown_list_item, parent, false);
    }


    /**
     * Binds the existing view to the the data pointed to by cursor
     * @param view- the view to bind the data to
     * @param context- the context
     * @param cursor- the cursor from which to get the data
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.text_all_categories);
        String category = cursor.getString(cursor.getColumnIndex(CategoriesInfoEntry.COLUMN_CATEGORY_TITLE));
        textView.setText(category);
    }

}
