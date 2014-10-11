package com.hackathon.inmobi;



import java.util.concurrent.ExecutionException;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {
	
	private static Location MYLOCATION = null;
	private static LatLng MYLATLNG = null;
	
	private ViewPager mPager;	
	
	ActionBar mActionbar;
 
	Double latitude,longitude;
	
	public static Ngo[] ngos;

  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.main);
    
    
    
    /** Getting a reference to action bar of this activity */
	mActionbar = getSupportActionBar();		
	
	/** Set tab navigation mode */
    mActionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    /** Getting a reference to ViewPager from the layout */
    mPager = (ViewPager) findViewById(R.id.pager);

    /** Getting a reference to FragmentManager */
    FragmentManager fm = getSupportFragmentManager();

    /** Defining a listener for pageChange */
    ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    mActionbar.setSelectedNavigationItem(position);
            }

    };

    /** Setting the pageChange listener to the viewPager */
    mPager.setOnPageChangeListener(pageChangeListener);

    /** Creating an instance of FragmentPagerAdapter */
    MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);

    /** Setting the FragmentPagerAdapter object to the viewPager object */
    mPager.setAdapter(fragmentPagerAdapter);

    mActionbar.setDisplayShowTitleEnabled(true);
    
    /** Defining tab listener */
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {

                    @Override
                    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                    }

                    @Override
                    public void onTabSelected(Tab tab, FragmentTransaction ft) {
                            mPager.setCurrentItem(tab.getPosition());

                    }

                    @Override
                    public void onTabReselected(Tab tab, FragmentTransaction ft) {
                    }
            };
            
            /** Creating fragment1 Tab */
            Tab tab = mActionbar.newTab()
                               .setText("MAP")                                   
                               .setTabListener(tabListener);
          

            mActionbar.addTab(tab);

            /** Creating fragment2 Tab */
            tab = mActionbar.newTab()
                           .setText("LIST")                               
                           .setTabListener(tabListener);

            mActionbar.addTab(tab); 
    
    
    
    
   
    
    
    GPSTracker gpstracker = new GPSTracker(MainActivity.this);
    MYLOCATION = gpstracker.getLocation();
    latitude = MYLOCATION.getLatitude();
    longitude = MYLOCATION.getLongitude();
    MYLATLNG= new LatLng(latitude,longitude);
    String cityname = gpstracker.getCityName(latitude,longitude);
    Log.d("cityname", cityname);
    FetchLatandLng task = new FetchLatandLng(MainActivity.this);
    task.execute(cityname);
    
    
    
    
     ngos = null;
    try {
		ngos = task.get();	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    Bundle b=new Bundle();
    b.putDouble("latitude", latitude);
    b.putDouble("longitude", longitude);
    
    Fragment mapView=new MapViewNGO();
    mapView.setArguments(b);
    
    //Intent intent = new Intent(MainActivity.this,MapView.class);
//    intent.putExtra("latitude", latitude);
//    intent.putExtra("longitude",longitude);
    //intent.putExtra("ngos", ngos);
    //Log.d("length is",Integer.toString(ngos.length));
    //startActivity(intent);
 
    
   
  
  }
  
  
  private void setTabs(){
	  
	  
	   
	            
	  
  }
  
  private class MyFragmentPagerAdapter extends FragmentPagerAdapter{
		
		final int PAGE_COUNT = 2;
		
		/** Constructor of the class */
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			
		}

		/** This method will be invoked when a page is requested to create */
		@Override
		public Fragment getItem(int arg0) {
			Bundle data = new Bundle();
			switch(arg0){
			
				/** tab1 is selected */
				case 0:
						
					Bundle b=new Bundle();
				    b.putDouble("latitude", latitude);
				    b.putDouble("longitude", longitude);
				    
				    MapViewNGO fragment1 = new MapViewNGO();
				    fragment1.setArguments(b);
					return fragment1;
					
				/** tab2 is selected */
				case 1:
					Fragment1 fragment2 = new Fragment1();
				    return fragment2;	
			}
			
			return null;
		}

		/** Returns the number of pages */
		@Override
		public int getCount() {		
			return PAGE_COUNT;
		}
		
	}

  
  
  
  
  
  

} 