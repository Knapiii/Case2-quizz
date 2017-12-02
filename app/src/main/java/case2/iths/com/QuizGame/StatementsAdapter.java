package case2.iths.com.QuizGame;

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

/**
 * Created by alvaro on 2017-11-28.
 */

public class StatementsAdapter extends RecyclerView.Adapter<StatementsAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int statementPos;
    private int categoryPos;
    private QuizableDBHelper quizableDBHelper;


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
        holder.textCategory.setText(category);

    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private final TextView textStatement;
        private final TextView textCategory;
        private final ImageButton deleteButton;


        public ViewHolder(View itemView) {
            super(itemView);
            textStatement = itemView.findViewById(R.id.text_statement);
            textCategory = itemView.findViewById(R.id.text_category);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);


        }

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

        String[] selectionArgs = new String[]{id};
        db.delete(QuizableDBHelper.TABLE, QuizableDBHelper.KEY_ID+"=?", selectionArgs);

        mCursor = quizableDBHelper.getUserMadeStatements();

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

    }

}
