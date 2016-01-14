package com.testmapbox;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.RectF;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mapbox.mapboxsdk.constants.MyLocationTracking;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private MapView mapView = null;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;

    private LatLng[] unstyledPath = new LatLng[] {
        new LatLng(43.63777718951345, -79.42283534990565),
        new LatLng(43.638320711938064, -79.42051792131679),
        new LatLng(43.63926798212667, -79.42103290544765),
        new LatLng(43.63912822189045, -79.42191267000453),
        new LatLng(43.63998230714579, -79.4223203657748),
        new LatLng(43.639438799751666, -79.42483091341273),
        new LatLng(43.63763742580956, -79.42407989488856)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setStyleUrl(Style.DARK);
        mapView.setZoomLevel(15);
        mapView.setRotateEnabled(false);
        mapView.setCompassEnabled(false);
        mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_NONE);

        checkAndRequestPermissions();

        mapView.onCreate(savedInstanceState);
    }

    private void prepMap() {
        mapView.setMyLocationEnabled(true);
        mapView.setVisibleCoordinateBounds(unstyledPath, new RectF(40,40,40,40), true);
    }

    private void checkAndRequestPermissions() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            prepMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            int isGranted = -1;
            for (int i=0; i<permissions.length;i++) {
                isGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED ? PackageManager.PERMISSION_GRANTED : PackageManager.PERMISSION_DENIED;
//                Log.e("MAP",""+permissions[i]);
//                Log.e("MAP",""+grantResults[i]);
            }

            if (isGranted == PackageManager.PERMISSION_GRANTED) {
                prepMap();
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause()  {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
