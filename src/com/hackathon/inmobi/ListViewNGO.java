package com.hackathon.inmobi;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewNGO extends Fragment {
	
	
  private  Context context;
  private  String[] ngo_names;
  private  String[] ngo_addresses;
  private  String[] events_numbers;
  private  String[] wishes_numbers;
  
  public ListViewNGO(){
	  
	  
  }

//  public ListViewNGO(Context context, String[] ngo_names, String[] ngo_addresses,String[] events_numbers,String[] wishes_numbers) {
//    //super(context, R.layout.rowlayout, ngo_names);
//    this.context = context;
//    this.ngo_names=ngo_names;
//    this.ngo_addresses=ngo_addresses;
//    this.events_numbers=events_numbers;
//    this.wishes_numbers=wishes_numbers;
//  }

  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
      
  	View view = inflater.inflate(R.layout.listview_ngo, null);
  	
  	ListView lv=(ListView)view.findViewById(R.id.ngo_list);
  	ListAdapter lad=new ListAdapter(getActivity(), ngo_names, ngo_addresses, events_numbers, wishes_numbers);
  	lv.setAdapter(lad);
      
      return view;
      
  }
}