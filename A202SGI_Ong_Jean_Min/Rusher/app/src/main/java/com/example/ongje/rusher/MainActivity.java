package com.example.ongje.rusher;

import android.content.DialogInterface;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

            EditText email;
            TabItem tabShuttle,tabLeave,tabFood,tabBook;
            TabLayout tabLayoutMain;
            ViewPager viewPagerMain;
            PageAdapter_MainActivity pageAdapterMain;
            public static String value;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main2);

                //To get user login email to display each request accordingly
                value= getIntent().getStringExtra("email");
                Toolbar toolbarMain=(Toolbar)findViewById(R.id.toolbarMain);
                setSupportActionBar(toolbarMain);
                BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigationView);
                bottomNavigationView.setOnNavigationItemReselectedListener(
                        new BottomNavigationView.OnNavigationItemReselectedListener() {
                            @Override
                            public void onNavigationItemReselected(@NonNull MenuItem item) {
                                switch (item.getItemId())
                                {
                                    case R.id.navigation_requestCheck:
                                        //Intent to check pending request
                                        Intent newAct = new Intent(getApplicationContext(),PendingRequestStatus.class);
                                        startActivity(newAct);
                                        break;

                                    case R.id.navigation_requestApproved:
                                        //Intent to approved pending request
                                        Intent i = new Intent(getApplicationContext(),ApprovedRequestStatus.class);
                                        startActivity(i);
                                        break;
                                }
                            }
                        }
                );
        TabLayout tabLayoutMain=findViewById(R.id.tabLayoutMain);
        tabShuttle=findViewById(R.id.tabShuttle);
        tabLeave=findViewById(R.id.tabLeave);
        tabFood=findViewById(R.id.tabFood);
        tabBook=findViewById(R.id.tabBook);
        viewPagerMain=findViewById(R.id.viewPagerMain);
        //Allows the user to flip left and right through pages of data.
        pageAdapterMain = new PageAdapter_MainActivity(getSupportFragmentManager(), tabLayoutMain.getTabCount());
        viewPagerMain.setAdapter(pageAdapterMain);
        viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMain));

        // Tab layout to allow swipe of tab
        tabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPagerMain.setCurrentItem(tab.getPosition());
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
        menu.findItem(R.id.back).setVisible(false);

        return true;
    }
    //Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout)
        {
            Intent i= new Intent(this,LoginActivity.class); //Direct user back to login activity
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
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();

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
