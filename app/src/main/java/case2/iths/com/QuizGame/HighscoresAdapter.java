package case2.iths.com.QuizGame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by alvaro on 2017-11-17.
 */

public class HighscoresAdapter extends BaseAdapter {

    private String[] dataSource;
    private LayoutInflater inflater;

    public HighscoresAdapter(Activity activity, String[] categories) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        dataSource = categories;


    }
    //Returns amount of elements in datasource. Our datasource is a string array and therefor I write datasource.length.
    // If datasource would have been an arraylist the i would have used datasource.size();
    @Override
    public int getCount() {

        return dataSource.length;
    }

    //Takes a position in a our view as a parameter
    //Return the value in that position

    @Override
    public Object getItem(int position) {
        return dataSource[position];
    }

    //Takes a position in a our view as a parameter
    // return an id for this position
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if(convertView == null)
            v = inflater.inflate(R.layout.dropdown_list_item, parent, false);
        else
            v = convertView;

        TextView tv = v.findViewById(R.id.textView);
        tv.setText(dataSource[position]);

        return v;
    }

}
