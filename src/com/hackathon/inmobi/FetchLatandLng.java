package com.hackathon.inmobi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class FetchLatandLng extends AsyncTask<String, String, Ngo[]> {
	
	ProgressDialog pDialog = null;
	Context mcontext;
	String url= "http://inmobihackathon.in/Retrieve_Data.php";
	InputStream inputStream = null;
	static Ngo[] ngos;
	
	
	FetchLatandLng(Context applicationContext) {
		this.mcontext = applicationContext;
	;
	}
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mcontext);
        pDialog.setMessage("Fetching data. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    protected Ngo[] doInBackground(String... args) {
    	String city = args[0];
    	
         try {
        	
        	 List<NameValuePair> params = new ArrayList<NameValuePair>();
             params.add(new BasicNameValuePair("city", city));
            
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost(url);
             httppost.setEntity(new UrlEncodedFormEntity(params));
             HttpResponse httpResponse = httpclient.execute(httppost);
             inputStream = httpResponse.getEntity().getContent();
             
         } catch (Exception e) {
             Log.d("InputStream", e.getLocalizedMessage());
         }
         return ngos;
         
         
    }
    protected void onPostExecute(String file_url) {
        pDialog.dismiss();
        try {
        	 getAllLatsandLngs(inputStream).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    
    public static Ngo[] getAllLatsandLngs (InputStream inputStream) throws IOException, JSONException, java.text.ParseException {
		
    	
    	
    	BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	    String line = "";
	    String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        System.out.println("result is"+result);
	        JSONArray jsonArray = new JSONArray(result);
	        int length = jsonArray.length();
	        ngos = new Ngo[length]; 
	        
	        for(int i =0;i<jsonArray.length();i++) {
	        	JSONObject json  = jsonArray.getJSONObject(i);
	        	ngos[i] = new Ngo();
	        	ngos[i].id = json.getString("ngo_id");
	        	ngos[i].name = json.getString("ngo_name");
	        	ngos[i].address = json.getString("address");
	        	ngos[i].latitude = json.getDouble("latitude");
	        	ngos[i].longitude = json.getDouble("longitude");
	        	ngos[i].city = json.getString("city");
	        	ngos[i].information = json.getString("information");
	        	ngos[i].contacts = new NgoContacts();
	        	
	        	JSONArray websites = json.getJSONArray("website");
	        	length = websites.length();
	        	ngos[i].contacts.websites = new ArrayList<String>();
	        	for(int j =0;j<websites.length();j++){
	        		System.out.println(websites.getString(j));
	        		}
	        	
	        	JSONArray emails = json.getJSONArray("email");
	        	length = emails.length();
	        	ngos[i].contacts.emails = new ArrayList<String>();
	    		for(int j =0;j<emails.length();j++){
	    			ngos[i].contacts.emails.add(emails.getString(j));
       		}
	    		
	    		JSONArray contacts = json.getJSONArray("contact");
	    		length = contacts.length();
	    		ngos[i].contacts.contacts = new ArrayList<String>();
	    		for(int j =0;j<contacts.length();j++) {
	    			ngos[i].contacts.contacts.add(contacts.getString(j));
       		}
	    		
	    		JSONArray events = json.getJSONArray("events");
	    		length = events.length();
	    		ngos[i].events = new ArrayList<Events>();
	    		for(int j =0;j<events.length();j++) {
	    			Events event = new Events();
	    			JSONObject jObject = events.getJSONObject(j);
	    			event.name = jObject.getString("event_title");
	    			event.description = jObject.getString("event");
	    			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	    			SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
	    			try {
	    		 
	    				event.date = (Date) dateFormatter.parse(jObject.getString("date"));
	    				event.time =  (Date) timeFormatter.parse(jObject.getString("time"));
	    				
	    			} catch (ParseException e) {
	    				e.printStackTrace();
	    			}
	    			ngos[i].events.add(event);
       		}
	    		
	    		JSONArray wishes = json.getJSONArray("wishes");
	    		length = wishes.length();
	    		ngos[i].wishes = new ArrayList<Wishes>();
	    		for(int j =0;j<wishes.length();j++) {
	    			Wishes wish = new Wishes();
	    			JSONObject jObject = wishes.getJSONObject(j);
	    			wish.wish = jObject.getString("wish_title");
	    			wish.description = jObject.getString("wish");
	    			
	    			ngos[i].wishes.add(wish);
       		}	
       	 }
	        //Log.d("length is",Integer.toString(ngos.length));
	        return ngos;
	   
	 
	}

}