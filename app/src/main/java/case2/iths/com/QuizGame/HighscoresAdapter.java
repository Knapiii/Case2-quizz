package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import case2.iths.com.QuizGame.QuizableDatabaseContract.HighScoresInfoEntry;

/**
 * Created by alvaro on 2017-11-17.
 */

public class HighscoresAdapter extends  RecyclerView.Adapter<HighscoresAdapter.ViewHolder>  {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int namePos;
    private int scorePos;
    private int idPos;

    public HighscoresAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();

    }

    private void populateColumnPositions() {
        if(mCursor == null)
            return;

        namePos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_USER_ID);
        scorePos = mCursor.getColumnIndex(HighScoresInfoEntry.COLUMN_HIGHSCORE);
        idPos = mCursor.getColumnIndex(HighScoresInfoEntry._ID);


        //Get column indexes from mCursors
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
        View itemView = mLayoutInflater.inflate(R.layout.item_highscore_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mCursor.moveToPosition(position);
        String name = mCursor.getString(namePos);
        String score = mCursor.getString(scorePos);
        int id = mCursor.getInt(idPos);

        holder.textName.setText(name);
        holder.textScore.setText(score);
        holder.textPoints.setText(R.string.points);
        holder.id = id;

    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textName;
        public final TextView textScore;
        public final TextView textPoints;
        public int id;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textScore = itemView.findViewById(R.id.text_score);
            textPoints = itemView.findViewById(R.id.text_points);
        }
    }

}
