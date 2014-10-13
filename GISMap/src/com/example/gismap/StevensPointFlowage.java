package com.example.gismap;

import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.LocationDisplayManager.AutoPanMode;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class StevensPointFlowage extends Activity {
	
	MapView mapView;
	boolean[] layersChecked = new boolean[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stevens_point_flowage);
		
		mapView = (MapView)findViewById(R.id.map);
		
		//mapView.addLayer(new ArcGISDynamicMapServiceLayer(""+
		//"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		
		mapView.setOnStatusChangedListener(
				new OnStatusChangedListener(){
					public void onStatusChanged(Object source, STATUS status){
						if(source == mapView && status == STATUS.INITIALIZED){
							LocationDisplayManager ldm = mapView.getLocationDisplayManager();
							ldm.setAutoPanMode(AutoPanMode.LOCATION);
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
		else if(id == R.id.action_addlayer){
			showPopup();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showPopup(){
		View menuItemView = findViewById(R.id.action_addlayer);
		PopupMenu popup = new PopupMenu(this, menuItemView);
		MenuInflater inflate = popup.getMenuInflater();
		inflate.inflate(R.menu.popup, popup.getMenu());
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener(){
			@Override
			public boolean onMenuItemClick(MenuItem item){
				item.setChecked(!item.isChecked());
				int id = item.getItemId();
				if(id == R.id.contours){
					layersChecked[0]=item.isChecked();
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
		popup.show();
		if(layersChecked[0]){
			MenuItem item = (MenuItem) findViewById(R.id.contours);
			item.setChecked(true);
		}
		if(layersChecked[1]){
			MenuItem item = (MenuItem) findViewById(R.id.pois);
			item.setChecked(true);
		}
		if(layersChecked[2]){
			MenuItem item = (MenuItem) findViewById(R.id.structures);
			item.setChecked(true);
		}
	}
	
}
