package com.hackathon.inmobi;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewNGO extends Fragment {
	
	
  private  Context context;
  private  String[] ngo_names;
  private  String[] ngo_addresses;
  private  int[] events_numbers;
  private  int[] wishes_numbers;
  
  public static ListAdapter lad;
  View view;
  
  public ListViewNGO(){
	  
	  //populateList();
	  
  }

//  public ListViewNGO(Context context, String[] ngo_names, String[] ngo_addresses,String[] events_numbers,String[] wishes_numbers) {
//    //super(context, R.layout.rowlayout, ngo_names);
//    this.context = context;
//    this.ngo_names=ngo_names;
//    this.ngo_addresses=ngo_addresses;
//    this.events_numbers=events_numbers;
//    this.wishes_numbers=wishes_numbers;
//  }
  
  
  public void populateList(){
	  
	  Ngo[] ngos=MainActivity.globalNgoArray;
	  	
	  	//Toast.makeText(getActivity(),""+ngos.length,Toast.LENGTH_LONG).show();
	  	
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
	  	
	  	final ListView lv=(ListView)view.findViewById(R.id.ngo_list);
	  	lad=new ListAdapter(getActivity(), ngo_names, ngo_addresses, events_numbers, wishes_numbers);
	  	lv.setAdapter(lad);
	  
	  	lv.setClickable(true);
	  	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	  	  @Override
	  	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

	  	    Object o = lv.getItemAtPosition(position);
	  	    
	  	      
	  	  }
	  	});
	  	
  }

  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
      
  	    view = inflater.inflate(R.layout.listview_ngo, null);
  	
  		populateList();
      
      return view;
      
  }
  
  
  @Override
  public void onResume(){
	  
	  super.onResume();
	  //Toast.makeText(getActivity(),"called",Toast.LENGTH_LONG).show();
	  
  }
  
  
  
  
}