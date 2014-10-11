package com.hackathon.inmobi;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
  private  int[] events_numbers;
  private  int[] wishes_numbers;
  
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
  	
  	Ngo[] ngos=MainActivity.ngos;
  	
  	ngo_names=new String[ngos.length];
  	ngo_addresses=new String[ngos.length];
  	events_numbers=new int[ngos.length];
  	wishes_numbers=new int[ngos.length];
  	
  	for(int i=0;i<ngos.length;i++){
  		
  		ngo_names[i]=ngos[i].name;
  		ngo_addresses[i]=ngos[i].address;
  		events_numbers[i]=ngos[i].events.size();
  		wishes_numbers[i]=ngos[i].wishes.size();
  		
  		
  	}
  	
  	ListView lv=(ListView)view.findViewById(R.id.ngo_list);
  	ListAdapter lad=new ListAdapter(getActivity(), ngo_names, ngo_addresses, events_numbers, wishes_numbers);
  	lv.setAdapter(lad);
      
      return view;
      
  }
}