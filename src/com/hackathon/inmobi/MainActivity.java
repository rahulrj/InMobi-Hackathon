package com.hackathon.inmobi;

import java.util.concurrent.ExecutionException;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	
	private static Location MYLOCATION = null;
	private static LatLng MYLATLNG = null;


  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.main);
   
    Button maps = (Button) findViewById(R.id.maps);
    Button list = (Button) findViewById(R.id.list);
    
    list.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	
		   	 }
    });
    
    maps.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        
		   	 }
    });
    
    GPSTracker gpstracker = new GPSTracker(MainActivity.this);
    MYLOCATION = gpstracker.getLocation();
    Double latitude = MYLOCATION.getLatitude();
    Double longitude = MYLOCATION.getLongitude();
    MYLATLNG= new LatLng(latitude,longitude);
    String cityname = gpstracker.getCityName(latitude,longitude);
    Log.d("cityname", cityname);
    FetchLatandLng task = new FetchLatandLng(MainActivity.this);
    task.execute(cityname);
  /*  Ngo[] ngos = null;
    try {
		ngos = task.get();	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    Intent intent = new Intent(MainActivity.this,MapView.class);
    intent.putExtra("latitude", latitude);
    intent.putExtra("longitude",longitude);
    intent.putExtra("ngos", ngos);
    Log.d("length is",Integer.toString(ngos.length));*/
   // startActivity(intent);
 
    
   
  
  }

} 