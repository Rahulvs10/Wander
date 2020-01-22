package com.example.wander.wanderMap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wander.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import static com.example.wander.Constants.MAPVIEW_BUNDLE_KEY;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;
    private ClusterManager<ClusterMarker> mClusterManager;
    private ClusterManagerRenderer mClusterManagerRenderer;
    private GoogleMap mGoogleMap = null;
    private LatLngBounds mMapBoundary;
    private ClusterMarker ClusterMarker1,ClusterMarker2,ClusterMarker3,ClusterMarker4;
    private boolean hasResumed=false;

    private Marker m1,m2,m3,m4;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = view.findViewById(R.id.wander_map);
        mMapView.onCreate(savedInstanceState);
        initGoogleMap(savedInstanceState);
    }


    public void addMapMarkers(){

        String name1 = "John";
        String name2 = "Jackie";
        String name3 = "Jimmy";
        String name4 = "Charlie";
        String snippet1 = "Hello World! I am here";
        String snippet2 = "I'm here!!!";
        String snippet3 = "Welcome to Paris!";
        String snippet4 = "Adios amigos!";
        double latitude = 48.8584;
        double longitude = 2.2945;
        double latitude1 = 27.1751;
        double longitude1 = 78.0421;
        int pic1 = R.drawable.profile_pic;
        int pic2 = R.drawable.profile_img2;
        int pic3 = R.drawable.profile_img3;
        int pic4 = R.drawable.wander_logo;


        if(mGoogleMap != null){

            if(mClusterManager == null){
                mClusterManager = new ClusterManager<ClusterMarker>(getActivity().getApplicationContext(), mGoogleMap);
            }
            if(mClusterManagerRenderer == null){
                mClusterManagerRenderer = new ClusterManagerRenderer(
                        getActivity(),
                        mGoogleMap,
                        mClusterManager
                );
                mClusterManager.setRenderer(mClusterManagerRenderer);
            }

            try{
                //Added 4 sample users for demo
                //In real time, use a loop to plot the users on the wander map

                ClusterMarker1 = new ClusterMarker(
                        new LatLng(latitude,longitude),
                        name1,
                        snippet1,
                        pic1
                );
                mClusterManager.addItem(ClusterMarker1);

                ClusterMarker2 = new ClusterMarker(
                        new LatLng(latitude,longitude),
                        name2,
                        snippet2,
                        pic2
                );
                mClusterManager.addItem(ClusterMarker2);

                ClusterMarker3 = new ClusterMarker(
                        new LatLng(latitude,longitude),
                        name3,
                        snippet3,
                        pic3
                );
                mClusterManager.addItem(ClusterMarker3);

                ClusterMarker4 = new ClusterMarker(
                        new LatLng(latitude1,longitude1),
                        name4,
                        snippet4,
                        pic4
                );
                mClusterManager.addItem(ClusterMarker4);


            }catch (NullPointerException e){
                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
            }

            mClusterManager.cluster();


            setCameraView();
        }
    }


    public void setCameraView() {

        // Set a boundary to start
        //Instead of 48.8584,set the current user's latitude and instead of 2.2945 set the current user's longitude
        //This is to set the area around that would be viewed initially when the map is loaded
        double bottomBoundary = 48.8584 - .1;
        double leftBoundary = 2.2945 - .1;
        double topBoundary = 48.8584 + .1;
        double rightBoundary = 2.2945 + .1;

        mMapBoundary = new LatLngBounds(
                new LatLng(bottomBoundary, leftBoundary),
                new LatLng(topBoundary, rightBoundary)
        );

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
    }

    private void initGoogleMap(Bundle savedInstanceState) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        hasResumed = true;
        mMapView.getMapAsync(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        mMapView.onStop();
        super.onStop();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mGoogleMap = map;
        addMapMarkers();
    }


}
