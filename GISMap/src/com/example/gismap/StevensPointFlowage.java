package com.example.gismap;

import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.LocationDisplayManager.AutoPanMode;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class StevensPointFlowage extends Activity {
	
	//Map Object
	MapView mapView;
	
	//Boolean array for layers to properly display checkboxes in popup menu
	boolean[] layersChecked = new boolean[3];
	
	//Layers, Set up layers URLs and create layers objects
	String contoursURL = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";
	ArcGISDynamicMapServiceLayer contoursLayer = new ArcGISDynamicMapServiceLayer(contoursURL);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stevens_point_flowage);
		
		mapView = (MapView)findViewById(R.id.map);
		//Add contours layer to map and set the initial value to invisible
		contoursLayer.setVisible(false);
		mapView.addLayer(contoursLayer);
		
		//Locate your device on map and auto update as moving
		mapView.setOnStatusChangedListener(
				new OnStatusChangedListener(){
					public void onStatusChanged(Object source, STATUS status){
						if(source == mapView && status == STATUS.INITIALIZED){
							LocationDisplayManager ldm = mapView.getLocationDisplayManager();
							ldm.start();
						}
					}
				});
	}
	
	protected void onPause(){
		super.onPause();
		mapView.pause();
	}
	
	protected void onResume(){
		super.onResume();
		mapView.unpause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.stevens_point_flowage, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		//Call popup menu if the add layer action bar button is tapped
		else if(id == R.id.action_addlayer){
			showPopup();
			return true;
		}
		else if(id == R.id.action_information){
			showInfo();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//This method adds makes the popup menu appear on the screen and 
	//also handles the on click events in the menu
	public void showPopup(){
		View menuItemView = findViewById(R.id.action_addlayer);
		PopupMenu popup = new PopupMenu(this, menuItemView);
		MenuInflater inflate = popup.getMenuInflater();
		inflate.inflate(R.menu.popup, popup.getMenu());
		//Add on click listener for the pop menu items
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener(){
			@Override
			public boolean onMenuItemClick(MenuItem item){
				//Reverses the checked status of the items
				item.setChecked(!item.isChecked());
				//Get the item id from the selected item
				int id = item.getItemId();
				//If the ID is contours, set the contours layer to visible if checked 
				//and invisible if not checked
				if(id == R.id.contours){
					layersChecked[0]=item.isChecked();
					if(item.isChecked())
					{
						contoursLayer.setVisible(true);
					}
					else
					{
						contoursLayer.setVisible(false);
					}
				}
				if(id == R.id.pois){
					layersChecked[1]=item.isChecked();
				}
				if(id == R.id.structures){
					layersChecked[2]=item.isChecked();
				}
				return false;
			}
		});
		Menu popupMenu = popup.getMenu();
		//This sets the values of the checkbox when the popup menu displays
		if(layersChecked[0]){
			MenuItem item = (MenuItem) popupMenu.findItem(R.id.contours);
			item.setChecked(true);
		}
		if(layersChecked[1]){
			MenuItem item = (MenuItem) popupMenu.findItem(R.id.pois);
			item.setChecked(true);
		}
		if(layersChecked[2]){
			MenuItem item = (MenuItem) popupMenu.findItem(R.id.structures);
			item.setChecked(true);
		}
		popup.show();
	}
	
	//Redirect to info screen
	public void showInfo(){
		Intent newActivity = new Intent(StevensPointFlowage.this, Information.class);
		startActivity(newActivity);
	}
	
}
