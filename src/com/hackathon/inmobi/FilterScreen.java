package com.hackathon.inmobi;

import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FilterScreen extends Activity implements OnClickListener{
	
	ListView categoriesView;
	ArrayAdapter<String> adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_screen);
        
        Button done = (Button) findViewById(R.id.done);
        Button cancel  = (Button) findViewById(R.id.cancel);
        
        done.setOnClickListener(this);
        cancel.setOnClickListener(this);
        
       String[] categories = getResources().getStringArray(R.array.sports_array);
       
        
       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, categories);
       categoriesView=(ListView)findViewById(R.id.filter_ngo);
       categoriesView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       categoriesView.setAdapter(adapter);
       
       
        
	}



	@Override
	public void onClick(View v) {
		
		
		if(v.getId()==R.id.done){
		SparseBooleanArray checked = categoriesView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }
        
        
        
	}
		
		
		else
			finish();
		
	}

}
