/* Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hackathon.inmobi;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


/**
 * This shows how to draw circles on a map.
 */
public class MapViewNGO extends Fragment implements OnSeekBarChangeListener,
        OnInfoWindowClickListener, OnMarkerDragListener, OnMapLongClickListener {
	
    private static GoogleMap mMap;
    private List<DraggableCircle> mCircles = new ArrayList<DraggableCircle>(1);
    private LatLng latlng;
    //private Ngo[] ngos;
   
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        
    	View view = inflater.inflate(R.layout.map_view, null);
    	//Bundle bundle = getIntent().getExtras();
    	Double latitude = getArguments().getDouble("latitude");
    	Double longitude = getArguments().getDouble("longitude");
    	//ngos = (Ngo[]) getIntent().getSerializableExtra("Editing");
    	latlng = new LatLng(latitude,longitude);
        Log.d("latitude is",Double.toString(latitude));
        Log.d("longitude is",Double.toString(longitude));
        Log.d("length of ngos is",Integer.toString(MainActivity.ngos.length));
        setUpMap();
        addMarkersToMap(MainActivity.ngos);
        mMap.setOnInfoWindowClickListener(this);
        
        return view;
        
    }

    
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		
		
    }

	@Override
	public void onResume() {
        super.onResume();
        //setUpMap();
    }

   private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
            	      	
            	 mMap.setOnMarkerDragListener(this);
                 mMap.setOnMapLongClickListener(this);
               
                 DraggableCircle circle = new DraggableCircle(latlng,mMap);
                 mCircles.add(circle);

                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10.5f));
            }
        }
    }

   
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
       
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    private void onMarkerMoved(Marker marker) {
      
    }

	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void onInfoWindowClick(Marker marker) {
		
		 
		
		//Toast.makeText(getApplicationContext(), marker.getTitle().toString(), Toast.LENGTH_LONG).show();
		// TODO Auto-generated method stub
		
	}
	
	 public static void addMarkersToMap(Ngo[] ngos) {
	        // Uses a colored icon.
		 
		
		 mMap.clear();
				 for(int i =0;i<ngos.length;i++) {
			
			 
			 LatLng latlng = new LatLng(ngos[i].latitude,ngos[i].longitude);
			 
	         mMap.addMarker(new MarkerOptions()
	                .position(latlng)
	                .title(ngos[i].name)
	                .snippet(ngos[i].events.size()+" new wishes, "+ngos[i].wishes.size()+"new events")
	                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		 }
}
	
	

 
}