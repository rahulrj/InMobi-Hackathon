package com.hackathon.inmobi;

import android.graphics.Color;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class DraggableCircle {
     private final Marker centerMarker;
     private final Marker radiusMarker;
     private final Circle circle;
     private GoogleMap mMap;
     private int mStrokeColor = Color.BLACK;
     private int mFillColor = Color.RED;
     private  double radius = 15000;
     public static final double RADIUS_OF_EARTH_METERS = 99710;


     
     public DraggableCircle(LatLng center, GoogleMap mMap) {
         
    	 Log.d("inside", "dag");
        centerMarker=null;
         this.mMap = mMap;
        
         radiusMarker = mMap.addMarker(new MarkerOptions()
                 .position(toRadiusLatLng(center, radius))
                 .draggable(true)
                 .icon(BitmapDescriptorFactory.defaultMarker(
                         BitmapDescriptorFactory.HUE_AZURE)));
         circle = mMap.addCircle(new CircleOptions()
                 .center(center)
                 .radius(radius)
               //  .strokeWidth(mWidthBar.getProgress())
                 .strokeColor(mStrokeColor)
                 .fillColor(mFillColor));
     }
  
     public boolean onMarkerMoved(Marker marker) {
         if (marker.equals(centerMarker)) {
             circle.setCenter(marker.getPosition());
             radiusMarker.setPosition(toRadiusLatLng(marker.getPosition(), radius));
             return true;
         }
         if (marker.equals(radiusMarker)) {
              radius = toRadiusMeters(centerMarker.getPosition(), radiusMarker.getPosition());
              circle.setRadius(radius);
              return true;
         }
         return false;
     }
     public void onStyleChange() {
         //circle.setStrokeWidth(mWidthBar.getProgress());
         circle.setFillColor(mFillColor);
         circle.setStrokeColor(mStrokeColor);
     }
     
     /** Generate LatLng of radius marker */
     private static LatLng toRadiusLatLng(LatLng center, double radius) {
         double radiusAngle = Math.toDegrees(radius / RADIUS_OF_EARTH_METERS) /
                 Math.cos(Math.toRadians(center.latitude));
         return new LatLng(center.latitude, center.longitude + radiusAngle);
     }

     private static double toRadiusMeters(LatLng center, LatLng radius) {
         float[] result = new float[1];
         Location.distanceBetween(center.latitude, center.longitude,
                 radius.latitude, radius.longitude, result);
         return result[0];
     }
 }