package comhelpingandchanging.facebook.httpswww.changetogether.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comhelpingandchanging.facebook.httpswww.changetogether.R;

/**
 * Created by Yannick on 18.10.2016.
 */

public class CustomAdapterSearch extends BaseAdapter {
    Context context;
    ArrayList<String[]> data;
    private static LayoutInflater inflater = null;

    public CustomAdapterSearch(Context context, ArrayList<String[]> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.search_list_item, null);

        TextView profileName = (TextView) vi.findViewById(R.id.profileNameSearch);
        TextView text = (TextView) vi.findViewById(R.id.searchTag);
        TextView location = (TextView) vi.findViewById(R.id.searchLocation);

        profileName.setText("Von: " + data.get(position)[1]);
        text.setText("Bietet: " + data.get(position)[2]);
        location.setText("In: " + data.get(position)[4]);

        return vi;
    }
}