package com.example.ongje.rusher;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentShuttle extends Fragment implements LocationListener {

    private GoogleMap myMap;
    private ProgressDialog myProgress;
    private Marker previousMarker = null;
    private static final String MYTAG = "MYTAG";
    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    private Marker marker,marker2,marker3,marker4,marker5,marker6,marker7,marker8,marker9,marker10;
    Button btnSearch;
    EditText locationSearch;
    StudentRequestDB myDB;
    String timeSelection="";
    Button submitShuttle;
    EditText email;
    String status="Approved";

    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_shuttle, container, false);

        myDB=new StudentRequestDB(getActivity());
        v=view;
        submitShuttle=view.findViewById(R.id.submitShuttle);

        // Create Progress Bar.
        myProgress = new ProgressDialog(getActivity());
        myProgress.setTitle("Map Loading ...");
        myProgress.setMessage("Please wait...");
        myProgress.setCancelable(true);
        // Display Progress Bar.
        myProgress.show();

        btnSearch=(Button)view.findViewById(R.id.search_button1);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                locationSearch = (EditText) getActivity().findViewById(R.id.editText2);
                String location = locationSearch.getText().toString();
                List<Address> addressList = null;
                if(location==null||location.equals("")) // Error handling
                {
                    locationSearch.setError(getString(R.string.field_required));
                    Toast.makeText(getActivity(), "Do Not Search Field Empty!", Toast.LENGTH_LONG).show();

                }
              else {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    myMap.addMarker(new MarkerOptions().position(latLng).title("Shuttle Service Not Available Here"));
                    myMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    locationSearch.setText("");

                }


            }
        });
        SupportMapFragment mapFragment //To show Google Map on fragment
                = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);

        // Set callback listener, on Google Map ready.
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                onMyMapReady(googleMap);

            }
        });
        return view;

    }



    private void onMyMapReady(GoogleMap googleMap) {
        // Get Google Map from Fragment.
        myMap = googleMap;
        // Sét OnMapLoadedCallback Listener.
        myMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                // Map loaded. Dismiss this dialog, removing it from the screen.
                myProgress.dismiss();

                askPermissionsAndShowMyLocation();
            }
        });

        //Marker to pint point location on map
        marker = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3416, 100.2819)) //Pint point location using latitude and longtitude
                .title("Inti International College Penang") //inti penang
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker


        marker2 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3416, 100.2819)) //Pint point location using latitude and longtitude
                .title("Elite Height") //elit height
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker

        marker3 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.4340, 100.3020)) //Pint point location using latitude and longtitude
                .title("Penang Chinese Girls' High School") //pcghs
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker


        marker4 = myMap.addMarker(new MarkerOptions()
                        .position(new LatLng(5.2945, 100.2593)) //Pint point location using latitude and longtitude
                .title("Bayan Lepas")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker

        marker5 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3450, 100.2958)) //Pint point location using latitude and longtitude
                .title("Sungai Dua")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker

        marker6 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3531, 100.3073)) //Pint point location using latitude and longtitude
                .title("Sunny Point")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))); //Set color of marker

        marker7 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.4013, 100.3044))
                .title("McDonald Greenlane")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        marker8 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3423, 100.2940))
                .title("Taman Lip Sin")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        marker9 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.4334, 100.3064))
                .title("Watson One Stop")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        marker10 = myMap.addMarker(new MarkerOptions()
                .position(new LatLng(5.3369, 100.2741))
                .title("Sungai Ara")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


        myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(final Marker arg0) { //If marker location equal to preset location, dialog box will be shown
                if(arg0 != null && arg0.getTitle().equals(marker.getTitle().toString())||arg0.getTitle().equals(marker2.getTitle().toString())
                        ||arg0.getTitle().equals(marker3.getTitle().toString())||arg0.getTitle().equals(marker4.getTitle().toString())
                        ||arg0.getTitle().equals(marker5.getTitle().toString())||arg0.getTitle().equals(marker6.getTitle().toString())
                        ||arg0.getTitle().equals(marker7.getTitle().toString())||arg0.getTitle().equals(marker8.getTitle().toString())
                        ||arg0.getTitle().equals(marker9.getTitle().toString())||arg0.getTitle().equals(marker10.getTitle().toString()))
                {
                    final Dialog dialog2 = new Dialog(getActivity());
                    dialog2.setContentView(R.layout.prompt_box_shuttle);
                    Spinner spinnerTime=(Spinner)dialog2.findViewById(R.id.spinnerTime);

                    //To show list of time available
                    String[]dataArray=new String[]{"8:30 a.m.","9:30 a.m.","10:30 a.m.",
                            "11:30 a.m.","12:30 p.m.","1:30 p.m.","2:30 p.m.","3:30 p.m.",
                            "4:30 p.m.","5:30 p.m."};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataArray);

                    //Spinner to display time
                    spinnerTime.setAdapter(adapter);
                    spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                        {
                            timeSelection=parent.getItemAtPosition(position).toString(); //Selection of time will be get using position of array

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    //Submit button
                    Button submitShuttle = (Button) dialog2.findViewById(R.id.submitShuttle);
                    submitShuttle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // To insert selected data into database
                                boolean isInserted = myDB.insertDataShuttle((MainActivity.value), // To insert user's email using login data.
                                        timeSelection.toString(), arg0.getTitle().toString(), status);
                                if (isInserted == true) {
                                    dialog2.dismiss(); // Dialog will be dismiss if data is inserted into database successfully.

                                    //To get latest data inserted by user
                                    Cursor res = myDB.getDataShuttle();
                                    if (res.getCount() == -1) {
                                        // show message
                                        showMessage("Error", "Nothing found");
                                        return;
                                    }
                                    // To show summary of user submitted data
                                    StringBuffer buffer = new StringBuffer();
                                    while (res.moveToNext()) {
                                        buffer.append("Student Email: " + MainActivity.value + "\n");
                                        buffer.append("Time: " + res.getString(2) + "\n");
                                        buffer.append("Pick-up Location: " + arg0.getTitle() + "\n");
                                        buffer.append("Status:" + status + "\n\n");
                                        buffer.append("Shuttle will be arrived within 30 minutes.");

                                    }

                                    // Show all data
                                    showMessage("Request Sent", buffer.toString());

                                } else
                                    Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                            }
                        //}
                    });
                    dialog2.show();
            }
            else // If location entered by user is not equal with pre-defined marker, error message will be toast
                {
               LatLng position2 = marker.getPosition();

                    Toast.makeText(
                            getActivity(),
                            "Shuttle Service Is Not Available Here",
                            Toast.LENGTH_LONG).show();

                    //zoom to your clicked position :
                    myMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
                    myMap.getUiSettings().setZoomControlsEnabled(true); // To zoom in to searched location
                }
                    return true;
            }

        });

        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myMap.setMyLocationEnabled(true);
    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(),R.style.AlertDialog);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int back) {

                if(locationSearch!=null) // To clear user search history
                    locationSearch.setText("");

                else
                    //if user pressed "yes", then he is close the dialog box
                    dialog.dismiss();

            }
        });
        builder.show().getWindow().setLayout(1100,1000);
    }
    private void askPermissionsAndShowMyLocation() {

        // To ask the user for permission to view their location.
        if (Build.VERSION.SDK_INT >= 23) {
            int accessCoarsePermission
                    = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission
                    = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED
                    || accessFinePermission != PackageManager.PERMISSION_GRANTED) {
                // The Permissions to ask user.
                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};
                // Show a dialog asking the user to allow the above permissions.
                ActivityCompat.requestPermissions(getActivity(), permissions,
                        REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);

                return;
            }
        }

        // Show current location on Map.
        this.showMyLocation();
    }

    // When you have the request results.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_ID_ACCESS_COURSE_FINE_LOCATION: {

                // If request is cancelled, the result arrays are empty.
                // Permissions granted (read/write).
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_LONG).show();

                    // Show current location on Map.
                    this.showMyLocation();
                }
                // Cancelled or denied.
                else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    // Find Location provider is openning.
    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        // Criteria to find location provider.
        Criteria criteria = new Criteria();

        // Returns the name of the provider that best meets the given criteria.
        // ==> "gps", "network",...
        String bestProvider = locationManager.getBestProvider(criteria, true);

        boolean enabled = locationManager.isProviderEnabled(bestProvider);

        if (!enabled) {
            Toast.makeText(getActivity(), "No location provider enabled!", Toast.LENGTH_LONG).show();
            Log.i(MYTAG, "No location provider enabled!");
            return null;
        }
        return bestProvider;
    }

    // This method will only be called when you have the permissions to view a user's location.
    private void showMyLocation() {

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        String locationProvider = this.getEnabledLocationProvider();

        if (locationProvider == null) {
            return;
        }

        // Millisecond
        final long MIN_TIME_BW_UPDATES = 1000;
        // Met
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

        Location myLocation = null;
        try {
            // This code need permissions as above
            locationManager.requestLocationUpdates(
                    locationProvider,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
            // Getting Location.
            myLocation = locationManager
                    .getLastKnownLocation(locationProvider);
        }
        // With Android API >= 23, need to catch SecurityException.
        catch (SecurityException e) {
            Toast.makeText(getActivity(), "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(MYTAG, "Show My Location Error:" + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (myLocation != null) {

            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)             // Sets the center of the map to location user
                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder

            myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            // Add Marker to Map
            MarkerOptions option = new MarkerOptions();
            option.title("My Location");
            option.snippet("....");
            option.position(latLng);
            Marker currentMarker = myMap.addMarker(option);
            currentMarker.showInfoWindow();
        } else {
            Toast.makeText(getActivity(), "Location not found!", Toast.LENGTH_LONG).show();
            Log.i(MYTAG, "Location not found");
        }

    }


    //Location CallBack
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
