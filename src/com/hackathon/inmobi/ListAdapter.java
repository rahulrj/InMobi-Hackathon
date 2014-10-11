package com.hackathon.inmobi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Rahul Raja on 9/25/2014.
 */
public class ListAdapter extends BaseAdapter{

	private final Context context;
	  private final String[] ngo_names;
	  private final String[] ngo_addresses;
	  private final int[] events_numbers;
	  private final int[] wishes_numbers;
	  private static LayoutInflater inflater = null;

    public ListAdapter(Context context, String[] ngo_names, String[] ngo_addresses,int[] events_numbers,int[] wishes_numbers) {
        super();
        this.context = context;
        this.ngo_names=ngo_names;
        this.ngo_addresses=ngo_addresses;
        this.events_numbers=events_numbers;
        this.wishes_numbers=wishes_numbers;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      }

    

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ngo_names.length;
    }

    @Override
    public Object getItem(int position) {
    	
        return ngo_names[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.rowlayout, null);
        
        TextView ngoName=(TextView)vi.findViewById(R.id.ngo_name);
        TextView ngoAddress=(TextView)vi.findViewById(R.id.ngo_address);
        TextView wishCount=(TextView)vi.findViewById(R.id.wishes_count_no);
        TextView eventsCount=(TextView)vi.findViewById(R.id.events_count_no);
        
        ngoName.setText(ngo_names[position]);
        ngoAddress.setText(ngo_addresses[position]);
        wishCount.setText(String.valueOf(wishes_numbers[position]));
        eventsCount.setText(String.valueOf(events_numbers[position]));
               
       


        return vi;
    }
}


