package case2.iths.com.QuizGame.Adapters;

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

import case2.iths.com.QuizGame.Data.QuizableOpenHelper;
import case2.iths.com.QuizGame.Data.QuizableDatabaseContract.UserInfoEntry;
import case2.iths.com.QuizGame.R;

/**
 * Adapter that provides a binding from a cursor with profiles data to views that are displayed within a RecyclerView
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int userNamePos;

    /**
     * Constructor
     * @param context- the context
     * @param cursor- the cursor from which to get the profiles data
     */
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


    /**
     * Called when Recyclerview needs a new RecyclerView.Viewholder of the given type to repersent an item
     * @param parent- The ViewGroup into which the new View will be added after it is bound to and adapter position
     * @param viewType- The view type of the new view
     * @return- An new ViewHolder that holds a View of the given view type
     */
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_profile_list, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder-The ViewHolder which should be updated
     * to represent the contents fo the item at the given position in the data set
     * @param position- The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String userName = mCursor.getString(userNamePos);
        holder.textUserName.setText(userName);
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

        private final TextView textUserName;
        private final ImageButton deleteButton;
        private final CardView cardView;

        /**
         *
         * @param itemView- the view tho attache the data to
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textUserName = itemView.findViewById(R.id.text_user_name);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            cardView = itemView.findViewById(R.id.card_user);
            deleteButton.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked
         * @param view- the view that was clicked
         */
        @Override
        public void onClick(View view) {

            int idNr = view.getId();

            switch (idNr) {
                case R.id.deleteButton:
                    int position = getAdapterPosition();
                    mCursor.moveToPosition(position);

                    String id = mCursor.getString(mCursor.getColumnIndex(UserInfoEntry._ID));
                    if(getItemCount() > 2)
                        deleteProfile(id, position);
                    else
                        Toast.makeText(mContext, "At least 2 profiles required", Toast.LENGTH_LONG).show();
                    break;

            }
        }
    }

    private void deleteProfile(String id, int position) {
        QuizableOpenHelper mDbOpenHelper = new QuizableOpenHelper(mContext);
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        String[] selectionArgs = {id};
        db.delete(UserInfoEntry.TABLE_NAME, UserInfoEntry._ID+"=?", selectionArgs);
        mCursor = mDbOpenHelper.getAllProfiles();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        mDbOpenHelper.close();
    }
}
