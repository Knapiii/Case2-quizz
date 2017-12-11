package case2.iths.com.QuizGame.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.HighScoresInfoEntry;
import case2.iths.com.QuizGame.R;


/**
 * Adapter that provides a binding from a cursor with highscores data to views that are displayed within a RecyclerView
 */
public class HighscoresAdapter extends  RecyclerView.Adapter<HighscoresAdapter.ViewHolder>  {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int namePos;
    private int scorePos;
    private int idPos;
    private int amountOfStatementsPos;
    private int categoryPos;

    /**
     * Constructor
     * @param context- the context
     * @param cursor- the cursor from which to get the highscores data
     */
    public HighscoresAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();
    }

    private void populateColumnPositions() {

        if (mCursor == null)
            return;

        namePos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_USER_ID);
        scorePos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_HIGHSCORE);
        idPos = mCursor.getColumnIndex(HighScoresInfoEntry._ID);
        amountOfStatementsPos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_AMOUNT_OF_STATEMENTS);
        categoryPos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_CATEGORY_TITLE);

    }

    /**
     * Called when Recyclerview needs a new RecyclerView.Viewholder of the given type to repersent an item
     * @param parent- The ViewGroup into which the new View will be added after it is bound to and adapter position
     * @param viewType- The view type of the new view
     * @return- An new ViewHolder that holds a View of the given view type
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_highscore_list, parent, false);
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
        String name = mCursor.getString(namePos);
        String score = mCursor.getString(scorePos);
        int id = mCursor.getInt(idPos);
        int amountOfStatements = mCursor.getInt(amountOfStatementsPos);
        String category = mCursor.getString(categoryPos);
        holder.textName.setText(name);
        holder.textScore.setText(score);
        holder.textPoints.setText(R.string.points);
        holder.textRounds.setText(String.valueOf(amountOfStatements));
        holder.amountOfStatements.setText(R.string.statements);
        holder.id = id;
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textName;
        public final TextView textScore;
        public final TextView textPoints;
        public final TextView textRounds;
        public final TextView amountOfStatements;
        public final TextView textCategory;

        public int id;
        /**
         * @param itemView- the view tho attach the data to
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textScore = itemView.findViewById(R.id.text_score);
            textPoints = itemView.findViewById(R.id.text_points);
            textRounds = itemView.findViewById(R.id.text_rounds);
            textCategory = itemView.findViewById(R.id.text_category);
            amountOfStatements = itemView.findViewById(R.id.text_amount_of_statements);
        }
    }

}
