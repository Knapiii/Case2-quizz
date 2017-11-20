package case2.iths.com.QuizGame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alvaro on 2017-11-17.
 */

public class HighscoresAdapter extends BaseAdapter {

    private ArrayList<String> dataSource;
    private LayoutInflater inflater;

    public HighscoresAdapter(Activity activity, ArrayList<String> dataSource) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.dataSource= dataSource;


    }
    //Returns amount of elements in datasource. Our datasource is a string array and therefor I write datasource.length.
    // If datasource would have been an arraylist then i would have used datasource.size();
    @Override
    public int getCount() {

        return dataSource.size();
    }

    //Takes a position in a our view as a parameter
    //Returns the value on that position

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    //Takes a position in a our view as a parameter
    // returns an positions id
    @Override
    public long getItemId(int position) {
        return position;
    }



    //TODO: FIND OUT HOW TO USE 2 DIFFERENT LAYOUTS using only this adapter class
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        if(convertView == null) {
            v = inflater.inflate(R.layout.dropdown_list_item, parent, false);
       //    v = inflater.inflate(R.layout.highscore_list_item, parent, false);
        }
        else {
            v = convertView;
        }

        TextView tv = v.findViewById(R.id.textView);
     //   TextView textView = v.findViewById(R.id.textView2);

        tv.setText(dataSource.get(position));
     //   textView.setText(dataSource[position]);

        return v;
    }

}
