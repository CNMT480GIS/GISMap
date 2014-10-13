package com.example.gismap;  
  
import java.util.ArrayList;  
import java.util.Arrays;  
  
import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;  
import android.widget.ListView;  
  
public class MainActivity extends Activity {  
    
  private ListView mainListView ;  
  private ArrayAdapter<String> listAdapter ;  
    
  /** Called when the activity is first created. */  
  @Override  
  public void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.activity_main);  
      
    // Find the ListView resource.   
    mainListView = (ListView) findViewById( R.id.mainListView );  
  
    // Create and populate a List of waterbodies.  
    String[] waterbodies = new String[] { "Stevens Point Flowage", "Wisconsin River", "Little Plover River", 
                                      "Mississippi River", "Lake Dubay"};    
    ArrayList<String> waterbodyList = new ArrayList<String>();  
    waterbodyList.addAll( Arrays.asList(waterbodies) );  
      
    // Create ArrayAdapter using the planet list.  
    listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, waterbodyList);  
        
      
    // Set the ArrayAdapter as the ListView's adapter.  
    mainListView.setAdapter( listAdapter );  
    
    mainListView.setOnItemClickListener(new OnItemClickListener(){
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
    		switch(position)
    		{
    		case 0: Intent newActivity = new Intent(MainActivity.this, StevensPointFlowage.class);
    		startActivity(newActivity);
    		break;
    		}
    	}
    });
  }  
}
