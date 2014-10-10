package com.hackathon.inmobi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListView extends ArrayAdapter<String> {
  private final Context context;
  private final String[] ngo_names;
  private final String[] ngo_addresses;
  private final String[] events_numbers;
  private final String[] wishes_numbers;

  public ListView(Context context, String[] ngo_names, String[] ngo_addresses,String[] events_numbers,String[] wishes_numbers) {
    super(context, R.layout.rowlayout, ngo_names);
    this.context = context;
    this.ngo_names=ngo_names;
    this.ngo_addresses=ngo_addresses;
    this.events_numbers=events_numbers;
    this.wishes_numbers=wishes_numbers;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
    TextView ngo_name = (TextView) rowView.findViewById(R.id.ngo_name);
    ngo_name.setText(ngo_names[position]);
    
    TextView ngo_address = (TextView) rowView.findViewById(R.id.ngo_address);
    ngo_address.setText(ngo_addresses[position]);
    
    TextView events_number = (TextView) rowView.findViewById(R.id.events_number);
    events_number.setText(events_numbers[position]);
    
    TextView wishes_number = (TextView) rowView.findViewById(R.id.wishes_number);
    wishes_number.setText(wishes_numbers[position]);
 
 
 
 
    return rowView;
  }
}