package case2.iths.com.QuizGame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import case2.iths.com.QuizGame.QuizableDatabaseContract.UserInfoEntry;

/**
 * Created by alvaro on 2017-12-03.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int userNamePos;

    public ProfileAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();
    }

    private void populateColumnPositions() {

        if(mCursor == null)
            return;
        userNamePos = mCursor.getColumnIndex(UserInfoEntry.COLUMN_USERNAME);
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_profile_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String userName = mCursor.getString(userNamePos);
        holder.textUserName.setText(userName);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView textUserName;
        private final ImageButton deleteButton;
        private final CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            textUserName = itemView.findViewById(R.id.text_user_name);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            cardView = itemView.findViewById(R.id.card_user);
            // textUserName.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int idNr = view.getId();

            switch (idNr) {
                case R.id.deleteButton:
                    int position = getAdapterPosition();
                    mCursor.moveToPosition(position);

                    String id = mCursor.getString(mCursor.getColumnIndex(UserInfoEntry._ID));
                    if(getItemCount() > 2)
                        deleteStatement(id, position);
                    else
                        Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
                    break;
                case R.id.card_user:
                    Toast.makeText(mContext, "CardView", Toast.LENGTH_LONG).show();
                    break;

            }

            /*int position = getAdapterPosition();
            mCursor.moveToPosition(position);
            String id = mCursor.getString(mCursor.getColumnIndex(UserInfoEntry._ID));
            deleteStatement(id, position);*/
        }
    }

    private void deleteStatement(String id, int position) {
        QuizableOpenHelper mDbOpenHelper = new QuizableOpenHelper(mContext);
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        String[] selectionArgs = {id};
        db.delete(UserInfoEntry.TABLE_NAME, UserInfoEntry._ID+"=?", selectionArgs);
        mCursor = mDbOpenHelper.getAllProfiles();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
