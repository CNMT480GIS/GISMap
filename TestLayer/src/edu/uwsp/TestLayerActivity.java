/* Copyright 2012 ESRI
 *
 * All rights reserved under the copyright laws of the United States
 * and applicable international laws, treaties, and conventions.
 *
 * You may freely redistribute and use this sample code, with or
 * without modification, provided you include the original copyright
 * notice and use restrictions.
 *
 * See the Sample code usage restrictions document for further information.
 *
 */

package edu.uwsp;

import android.app.Activity;
import android.os.Bundle;


import com.esri.android.map.Layer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;

//SAMPLE CODE TAKEN FROM ArcGIS for Android Resource Center http://help.arcgis.com/en/arcgismobile/10.0/apis/android/help/index.html#//01190000000n000000

public class TestLayerActivity extends Activity {
	
	private static final String STEVENS_POINT_FLOWAGE_URL="https://gissrv4.uwsp.edu/publisher/rest/services/SPFL_MapServer_trial1_201409180949/MapServer/2";
	//test purposes only
	private static final String RIVERS_URL="http://services.nationalmap.gov/ArcGIS/rest/services/nhd/MapServer";
	
	MapView map = null;
	ArcGISDynamicMapServiceLayer sp_FlowageLayer = null;

   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
     // Retrieve the map from XML layout
     		map = (MapView) findViewById(R.id.map);
     	// Add dynamic layer to MapView
     		ArcGISDynamicMapServiceLayer sp_FlowageLayer = new ArcGISDynamicMapServiceLayer(STEVENS_POINT_FLOWAGE_URL);		
     		map.addLayer(sp_FlowageLayer);
     		
     		//Create a button for geolocation rather
     		
     		
     		//create onStatus change listener to use Location Display manager on
     		map.setOnStatusChangedListener(new OnStatusChangedListener() {
     			public void onStatusChanged(Object source, STATUS status) {
     				if (source == map && status == STATUS.INITIALIZED) {
     					LocationDisplayManager ls = map.getLocationDisplayManager();
     					ls.getAutoPanMode();
     					ls.start();
     					try {
     						ls.getLocationAcquiringSymbol();
     					} catch (Exception e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					}
     					ls.getPoint();
     					ls.isAllowNetworkLocation();
     					
     				}
     			}
     		});		
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
 }
	@Override
	protected void onPause() {
		super.onPause();
		map.pause();
 }
	@Override
	protected void onResume() {
		super.onResume();
		map.unpause();
	}

}