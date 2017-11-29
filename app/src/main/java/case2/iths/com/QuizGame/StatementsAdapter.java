package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static case2.iths.com.QuizGame.QuizableDatabaseContract.OwnStatementsEntry;

/**
 * Created by alvaro on 2017-11-28.
 */

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int statementPos;
    private int answerPos;
    private int categoryPos;
    private QuizableOpenHelper mDbOpenHelper;


    public StatementsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();

    }

    private void populateColumnPositions() {
        if(mCursor == null)
            return;

        statementPos = mCursor.getColumnIndex(OwnStatementsEntry.COLUMN_STATEMENT);
        answerPos = mCursor.getColumnIndex(OwnStatementsEntry.COLUMN_STATEMENT_ANSWER);
        categoryPos = mCursor.getColumnIndex(OwnStatementsEntry.COLUMN_CATEGORY_ID);
    }

    public void changeCursor(Cursor cursor) {
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnPositions();
        notifyDataSetChanged();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_statement_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mCursor.moveToPosition(position);
        String statement = mCursor.getString(statementPos);
        String category = mCursor.getString(categoryPos);

        holder.textStatement.setText(statement);
        holder.textCategory.setText(category.toUpperCase());





    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private final TextView textStatement;
        private final TextView textCategory;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);


            textStatement = itemView.findViewById(R.id.text_statement);
            textCategory = itemView.findViewById(R.id.text_category);


        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            mCursor.moveToPosition(position);

            String answer = mCursor.getString(mCursor.getColumnIndex(OwnStatementsEntry.COLUMN_STATEMENT_ANSWER));
            String id = mCursor.getString(mCursor.getColumnIndex(OwnStatementsEntry._ID));

            Toast.makeText(mContext, "Position: "+answer, Toast.LENGTH_LONG).show();

            deleteStatement(id, position);


        }


    }


    private void deleteStatement(String id, int position) {
        mDbOpenHelper = new QuizableOpenHelper(mContext);
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();


        String[] selectionArgs = new String[]{id};
        db.delete(OwnStatementsEntry.TABLE_NAME, OwnStatementsEntry._ID+"=?", selectionArgs);


        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());



    }



}
