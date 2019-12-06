package com.example.ongje.rusher;

import android.content.DialogInterface;
import android.content.Intent;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ApprovedRequestStatus extends AppCompatActivity {

    StudentRequestDB _db;
    TabItem tabShuttleRequest,tabFoodRequest,tabLeaveRequest,tabBookRequest;
    TabLayout tabLayoutBookStatus;
    ViewPager viewPagerBookingStatus;
    PageAdapter_ApprovedRequest PageAdapterBookiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_status);

        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbarBooking);
        setSupportActionBar(toolbar3);

        TabLayout tabLayoutBookStatus=findViewById(R.id.tabLayoutBookStatus);
        tabShuttleRequest=findViewById(R.id.tabShuttleRequest);
        tabFoodRequest=findViewById(R.id.tabFoodRequest);
        tabLeaveRequest=findViewById(R.id.tabLeaveRequest);
        tabBookRequest=findViewById(R.id.tabBookRequest);
        viewPagerBookingStatus=findViewById(R.id.viewPagerBookingStatus);//Allows the user to flip left and right through pages of data.
        PageAdapterBookiRequest = new PageAdapter_ApprovedRequest(getSupportFragmentManager(), tabLayoutBookStatus.getTabCount());
        viewPagerBookingStatus.setAdapter(PageAdapterBookiRequest);
        viewPagerBookingStatus.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutBookStatus));
        tabLayoutBookStatus.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerBookingStatus.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        _db=new StudentRequestDB(this); //To access to database
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    //Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent i= new Intent(this,LoginActivity.class);
            startActivity(i);
            return true;
        }
        else if (id==R.id.back)
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this,R.style.AlertDialog);

            builder.setCancelable(false);
            builder.setTitle("Exit");
            builder.setMessage("Changes unsaved, do you want to exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int back) {
                    //if user pressed "yes", then he is allowed to leave the current page
                    Intent newAct = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(newAct);

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int back) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            builder.show().getWindow().setLayout(900,700);

        }
        return super.onOptionsItemSelected(item);
    }
}
