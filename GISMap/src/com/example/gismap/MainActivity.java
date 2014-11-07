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
    
	new EULA(this).show();
	  
	super.onCreate(savedInstanceState);  
    setContentView(R.layout.activity_main);  
      
    // Find the ListView resource.   
    mainListView = (ListView) findViewById( R.id.mainListView );  
  
    // Create and populate a List of waterbodies.  
    String[] waterbodies = new String[] { "Stevens Point Flowage", "Pike Lake", "Lake Wausau"};    
    ArrayList<String> waterbodyList = new ArrayList<String>();  
    waterbodyList.addAll( Arrays.asList(waterbodies) );  
      
    // Create ArrayAdapter using the waterbody list.  
    listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, waterbodyList);      
      
    // Set the ArrayAdapter as the ListView's adapter.  
    mainListView.setAdapter( listAdapter );  
    
    //Set the item click listener for the list view items
    mainListView.setOnItemClickListener(new OnItemClickListener(){
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
    		switch(position)
    		{
    		//Create new Intent to move to Stevens Point Flowage screen
    		case 0: Intent newActivity = new Intent(MainActivity.this, StevensPointFlowage.class);
    		startActivity(newActivity);
    		break;
    		/*case 1:Intent newActivity1 = new Intent(MainActivity.this, PikeLake.class);
    		startActivity(newActivity1);
    		break;
    		*/
    		case 2: Intent newActivity2 = new Intent(MainActivity.this, LakeWausau.class);
    		startActivity(newActivity2);
    		break;
    		}
    	}
    });
  }  
}
