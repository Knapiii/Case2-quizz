package case2.iths.com.QuizGame.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import case2.iths.com.QuizGame.Data.QuizableDBHelper;
import case2.iths.com.QuizGame.R;

/**
 * Created by alvaro on 2017-11-28.
 * Adapter that provides a binding from a cursor with the statements data to views that are displayed within a RecyclerView
 */


public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int statementPos;
    private int categoryPos;
    private QuizableDBHelper quizableDBHelper;


    /**
     * Constructor
     * @param context- the context
     * @param cursor- the cursor from which to get the profiles data
     */
    public StatementsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();

    }

    private void populateColumnPositions() {

        if(mCursor == null)
            return;

        statementPos = mCursor.getColumnIndex(QuizableDBHelper.QUESTION);
        categoryPos = mCursor.getColumnIndex(QuizableDBHelper.CATEGORY);
    }

    /**
     * Called when Recyclerview needs a new RecyclerView.Viewholder of the given type to repersent an item
     * @param parent- The ViewGroup into which the new View will be added after it is bound to and adapter position
     * @param viewType- The view type of the new view
     * @return- An new ViewHolder that holds a View of the given view type
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_statement_list, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder-The ViewHolder which should be updated
     * to represent the contents fo the item at the given position in the data set
     * @param position- The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String statement = mCursor.getString(statementPos);
        String category = mCursor.getString(categoryPos);
        holder.textStatement.setText(statement);
        holder.textCategory.setText(category);
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     * @return the total number of items in this adapter
     */
    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    /**
     * ViewHolder class describes an item view and metadata about its place within the RecyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textStatement;
        private final TextView textCategory;
        private final ImageButton deleteButton;

        /**
         * @param itemView- the view tho attach the data to
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textStatement = itemView.findViewById(R.id.text_statement);
            textCategory = itemView.findViewById(R.id.text_category);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked
         * @param view- the view that was clicked
         */
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mCursor.moveToPosition(position);
            String id = mCursor.getString(mCursor.getColumnIndex(QuizableDBHelper.KEY_ID));
            Toast.makeText(mContext, "Deleted ", Toast.LENGTH_LONG).show();
            deleteStatement(id, position);
        }
    }

    private void deleteStatement(String id, int position) {
        quizableDBHelper = new QuizableDBHelper(mContext);
        SQLiteDatabase db = quizableDBHelper.getWritableDatabase();
        String[] selectionArgs = {id};
        db.delete(QuizableDBHelper.TABLE, QuizableDBHelper.KEY_ID+"=?", selectionArgs);
        mCursor = quizableDBHelper.getUserMadeStatements();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

}
