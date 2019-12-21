package com.example.khoir.emegency_service;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, Listener {
    public  Double Lat=null;
    public    Double Lng = null;
    private GoogleMap mMap;
    CameraUpdate cup;
    ArrayList<LatLng> markerPoints;
    public static final String TAG = "MAP DEMO";
    public static final float DEFAULT_ZOOM_LEVEL = 9.0f;

    ArrayList<LatLng> points = null;
    PolylineOptions lineOptions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        markerPoints = new ArrayList<LatLng>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i =getIntent();
        Lat=Double.parseDouble(i.getStringExtra("lt"));
        Lng=Double.parseDouble(i.getStringExtra("lg"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //Moving to a sample location
        LatLng uty = new LatLng(Lat, Lng);
        mMap.addMarker(new MarkerOptions()
                .position(uty)

                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))


        );


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uty, 50));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Setting onclick event listener for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {


                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    mMap.clear();
                }

                // Adding new point to the ArrayList
                markerPoints.add(point);


                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /**
                 * For the start location, Marker color is GREEN,
                 * for the end location, Marker color is MAGENTA.
                 */
                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                }

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = GetDataFromUrl.getDirectionsUrl(origin, dest);
                    GetDirections getDirections = new GetDirections(MapsActivity.this);
                    getDirections.startGettingDirections(url);
                }

                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);


            }
        });

    }


    //The task for getting directions ends up here...
    @Override
    public void onSuccessfullRouteFetch(final List<List<HashMap<String, String>>> result) {

        //if it takes a long time, we will do it in a seperate thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                MarkerOptions markerOptions = new MarkerOptions();
                // Traversing through all the routes
                for (List<HashMap<String, String>> path : result) {
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    int size = path.size();
                    // Get all the points for this route
                    for (HashMap<String, String> point : path) {
                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);
                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(12);
                    lineOptions.color(Color.RED);
                }

                //Do all UI operations on the UI thread only...
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Drawing polyline in the Google Map for the this route
                        mMap.addPolyline(lineOptions);
                    }
                });

            }
        }).start();

    }

    @Override
    public void onFail() {
        Log.i(TAG, "Failed to get directions from Google...");
    }
}
