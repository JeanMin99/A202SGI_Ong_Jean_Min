package com.example.ongje.rusher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class LecturerActivity extends AppCompatActivity {

    StudentRequestDB myDB;
    TabItem tabLeaveAdmin,tabBookAdmin;
    TabLayout tabLayoutAdmin;
    ViewPager viewPagerAdmin;
    LecturerPagerAdapter pageAdapterAdmin;
    Button approveBook,approveLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbarMain=(Toolbar)findViewById(R.id.toolbarAdmin);
        setSupportActionBar(toolbarMain);
        myDB = new StudentRequestDB(this); // To access into database
        TabLayout tabLayoutAdmin=findViewById(R.id.tabLayoutAdmin);
        tabLeaveAdmin=findViewById(R.id.tabLeaveAdmin);
        tabBookAdmin=findViewById(R.id.tabBookAdmin);
        viewPagerAdmin=findViewById(R.id.viewPagerAdmin);
        pageAdapterAdmin = new LecturerPagerAdapter(getSupportFragmentManager(), tabLayoutAdmin.getTabCount());
        viewPagerAdmin.setAdapter(pageAdapterAdmin);
        viewPagerAdmin.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutAdmin));
        approveBook=(Button)findViewById(R.id.buttonAdminBook);
        approveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder( LecturerActivity.this,R.style.AlertDialogDate)
                        .setTitle( "Approve Student Request?" )
                        .setMessage("This action will aprrove all pending booking activities room request")
                        .setPositiveButton( "Approve", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Log.d( "AlertDialog", "Positive" );
                                myDB.updateDataBooking(StudentRequestDB.COL_Booking_Status); //To update status request into approved
                                Intent i = new Intent(getApplicationContext(),LecturerActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                Toast.makeText(LecturerActivity.this,
                                        "Request Approved, There Are Currently No Pending Booking Request.", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Negative" );
                            }
                        } )
                        .show().getWindow().setLayout(1000,600);
            }
        });

        approveLeave=(Button)findViewById(R.id.buttonAdminLeave);
        approveLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder( LecturerActivity.this,R.style.AlertDialogDate)
                        .setTitle( "Approve Student Request?" )
                        .setMessage("This action will aprrove all pending leave request")
                        .setPositiveButton( "Approve", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Log.d( "AlertDialog", "Positive" );
                                myDB.updateDataLeave(StudentRequestDB.COL_6); //To update status request into approved
                                Intent i = new Intent(getApplicationContext(),LecturerActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                Toast.makeText(LecturerActivity.this,
                                        "Request Approved, There Are Currently No Pending Leave Request.", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Negative" );
                            }
                        } )
                        .show().getWindow().setLayout(1000,600); // To set size of display layout
            }
        });

        // Tab layout to allow swipe of tab
        tabLayoutAdmin.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPagerAdmin.setCurrentItem(tab.getPosition());
               if(tab.getPosition()==0) // If tab position is at leave of absence request, approve button for book room request will be invisible
               {
                   approveBook=(Button)findViewById(R.id.buttonAdminBook);
                   approveLeave=(Button)findViewById(R.id.buttonAdminLeave);
                   approveLeave.setVisibility(View.VISIBLE);
                   approveBook.setVisibility(View.GONE);

               }
               else if(tab.getPosition()==1) // If tab position is at book room request, approve button for leave of absence request will be invisible
               {
                   approveLeave=(Button)findViewById(R.id.buttonAdminLeave);
                   approveBook=(Button)findViewById(R.id.buttonAdminBook);
                   approveLeave.setVisibility(View.GONE);
                   approveBook.setVisibility(View.VISIBLE);
               }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.back).setVisible(false); // To set back button invisible
        return true;
    }

    //Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout)
        {
            Intent i= new Intent(this,LoginActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
