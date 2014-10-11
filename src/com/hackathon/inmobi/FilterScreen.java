package com.hackathon.inmobi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilterScreen extends Activity implements OnClickListener{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_screen);
        
       String[] categories = getResources().getStringArray(R.array.sports_array);
       
        
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, categories);
       ListView categoriesView=(ListView)findViewById(R.id.filter_ngo);
       categoriesView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       categoriesView.setAdapter(adapter);
        
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
		
		
		
	}

}
