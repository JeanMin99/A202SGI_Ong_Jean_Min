package com.example.ongje.rusher;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import androidx.core.app.NotificationCompat;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter_FoodReservation extends RecyclerView.Adapter<Adapter_FoodReservation.CustomViewHolder> {

    private List<Info_FoodReservation> _foods;
    Context mContext;
    StudentRequestDB myDB;
    String foodSelection="";
    int number=0;
    TextView textView3;
    String status= "Approved";

    double a;

    public Adapter_FoodReservation(List<Info_FoodReservation>data, Context context)
    {
        this._foods=data;
        this.mContext=context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_card, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_FoodReservation.CustomViewHolder holder, int position)
    {

        //To display the data at the specified position
        Info_FoodReservation menu= _foods.get(position);

        //update the contents of the itemView to reflect the item at the given position.
            holder._name.setText(menu.getName());
            holder._price.setText(menu.getPrice());
            holder.image.setImageResource(menu.getImageMenu());
            holder.menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOptionsMenu(holder.menu);
                }
            });
    }

    // To show pop up menu after user click the dot button
    private void showOptionsMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_food, popup.getMenu());
        popup.setOnMenuItemClickListener(new FoodMenuClickListener());
        popup.show();
    }

    @Override
    public int getItemCount()
    {
        return _foods.size();
    }

    // Helps to populate data to the view
    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public TextView _name, _price;
        public ImageView image, menu;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            _name = (TextView) itemView.findViewById(R.id.foodName);
            _price = (TextView) itemView.findViewById(R.id.foodPrice);
            image = (ImageView) itemView.findViewById(R.id.foodImage);
            menu = (ImageView) itemView.findViewById(R.id.menuDots);

        }
    }

    private class FoodMenuClickListener implements PopupMenu.OnMenuItemClickListener
    {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.order_now:

                    // To access database
                    myDB=new StudentRequestDB(mContext);
                    final Dialog dialog2 = new Dialog(mContext);

                    dialog2.setContentView(R.layout.prompt_box_food);
                    dialog2.setTitle("Order Food Now?");
                    number=1;

                    final Spinner spinnerFoodSelection=(Spinner)dialog2.findViewById(R.id.spinnerFoodType);
                    TextView selectedQuantity = (TextView) dialog2.findViewById(R.id.selectedQuantity);
                    TextView remarks = (TextView) dialog2.findViewById(R.id.remarks);
                    final EditText editTextRemark=(EditText) dialog2.findViewById(R.id.editTextRemark);
                    Button dialogButtonOrder = (Button) dialog2.findViewById(R.id.dialogButtonOrder);
                    final TextView textView3=(TextView)dialog2.findViewById(R.id.textView3);
                    final TextView displayInteger = (TextView)dialog2.findViewById(R.id.integer_number);
                    Button decrease= (Button) dialog2.findViewById(R.id.decrease);
                    // To decrease number of quantity
                    decrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            number=number-1;

                            if(number<0)
                            {
                               number=0;
                            }
                            displayInteger.setText("" + number);
                            if (spinnerFoodSelection.getSelectedItemPosition()==0) // To calculate food price
                            {
                                float total=number*6;
                                textView3.setText(Float.toString(total));
                            }

                            else if (spinnerFoodSelection.getSelectedItemPosition()==1)
                            {
                                float total=number*6;
                                textView3.setText(Float.toString(total));
                            }

                            else if (spinnerFoodSelection.getSelectedItemPosition()==2)
                            {
                                float total=number*10;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==3)
                            {
                                float total=number*10;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==4)
                            {
                                float total=number*7;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==5)
                            {
                                float total=number*7;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==6)
                            {
                                float total=number*12;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==7)
                            {
                                float total=number*12;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==8)
                            {
                                float total=number*13;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==9)
                            {
                                float total=number*13;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==10)
                            {
                                float total=number*6;
                                textView3.setText(Float.toString(total));
                            }
                            else if (spinnerFoodSelection.getSelectedItemPosition()==11)
                            {
                                float total=number*6;
                                textView3.setText(Float.toString(total));
                            }

                        }
                    });
                    // To increase the number of quantity
                    Button increase= (Button) dialog2.findViewById(R.id.increase);
                    increase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                              number=number+1;
                              displayInteger.setText("" + number);

                              if (spinnerFoodSelection.getSelectedItemPosition()==0) // To calculate food price
                              {
                                  float total=number*6;
                                  textView3.setText(Float.toString(total));
                              }

                              else if (spinnerFoodSelection.getSelectedItemPosition()==1)
                                {
                                    float total=number*6;
                                    textView3.setText(Float.toString(total));
                                }

                              else if (spinnerFoodSelection.getSelectedItemPosition()==2)
                              {
                                  float total=number*10;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==3)
                              {
                                  float total=number*10;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==4)
                              {
                                  float total=number*7;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==5)
                              {
                                  float total=number*7;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==6)
                              {
                                  float total=number*12;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==7)
                              {
                                  float total=number*12;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==8)
                              {
                                  float total=number*13;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==9)
                              {
                                  float total=number*13;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==10)
                              {
                                  float total=number*6;
                                  textView3.setText(Float.toString(total));
                              }
                              else if (spinnerFoodSelection.getSelectedItemPosition()==11)
                              {
                                  float total=number*6;
                                  textView3.setText(Float.toString(total));
                              }

                        }
                    });
                    Button dialogButtonCancel=(Button)dialog2.findViewById(R.id.dialogButtonCancel);
                    /// To show dropdown list of foods
                    spinnerFoodSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                        {
                            foodSelection=parent.getItemAtPosition(position).toString();
                            int quantity=Integer.parseInt(displayInteger.getText().toString());

                            switch (position)
                            {
                                case 0:
                                    float total=number*6;
                                    textView3.setText(Float.toString(total)); // To calculate food price
                                    break;
                                case 1:
                                    float total1=number*6;
                                    textView3.setText(Float.toString(total1));
                                    break;
                                case 2:
                                    float total2=number*10;
                                    textView3.setText(Float.toString(total2));
                                    break;
                                case 3:
                                    float total3=number*10;
                                    textView3.setText(Float.toString(total3));
                                    break;
                                case 4:
                                    float total4=number*7;
                                    textView3.setText(Float.toString(total4));
                                    break;
                                case 5:
                                    float total5=number*7;
                                    textView3.setText(Float.toString(total5));
                                    break;
                                case 6:
                                    float total6=number*12;
                                    textView3.setText(Float.toString(total6));
                                    break;
                                case 7:
                                    float total7=number*12;
                                    textView3.setText(Float.toString(total7));
                                    break;
                                case 8:
                                    float total8=number*13;
                                    textView3.setText(Float.toString(total8));
                                    break;
                                case 9:
                                    float total9=number*13;
                                    textView3.setText(Float.toString(total9));
                                    break;
                                case 10:
                                    float total10=number*6;
                                    textView3.setText(Float.toString(total10));
                                    break;
                                case 11:
                                    float total11=number*6;
                                    textView3.setText(Float.toString(total11));
                                    break;

                            }

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {

                            dialog2.dismiss();

                        }
                    });
                    dialogButtonOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {

                                dialog2.dismiss();

                                // To insert selected value into database
                                boolean isInserted = myDB.insertDataReservation(MainActivity.value,
                                        foodSelection.toString(), displayInteger.getText().toString(), editTextRemark.getText().toString()
                                        , textView3.getText().toString(), status);
                                if (isInserted == true) {

                                    Cursor res = myDB.getDataReservation();
                                    if (res.getCount() == -1) {
                                        // show message
                                        showMessage("Error", "Nothing found");
                                        return;
                                    }

                                    // To show summary of food ordering details
                                    StringBuffer buffer = new StringBuffer();
                                    while (res.moveToNext()) {
                                        buffer.append("Student Email: " + MainActivity.value + "\n");
                                        buffer.append("Food Selection: " + res.getString(2) + "\n");
                                        buffer.append("Quantity: " + res.getString(3) + "\n");
                                        buffer.append("Remarks: " + res.getString(4) + "\n");
                                        buffer.append("Total Amount: RM" + textView3.getText() + "\n");
                                        buffer.append("Status:" + status + "\n");

                                    }
                                    // Show all data
                                    showMessage("Request Sent", buffer.toString());

                                } else {
                                    Toast toast = Toast.makeText(mContext.getApplicationContext(),
                                            "Data is not inserted",
                                            Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }

                    });

                    // To insert drop down list value
                    String[]dataArray=new String[]{"Belacan Fried Rice","Kimchi Fried Rice","Aglio Olio Spaghetti","Tomato Sauce Spaghetti"
                            ,"Tomyam Soup Noodle","Clear Soup Noodle","Ginger Chicken Set Lunch","Ginger Fish Set Lunch",
                    "Double Cheese Burger","Crispy Chicken Burger","Pearl Milk Tea","Jasmine Green Tea"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, dataArray);
                    spinnerFoodSelection.setAdapter(adapter);

                    dialog2.show();
                    return true;
            }
            return false;

        }
    }


    public void showMessage(String title, String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext,R.style.AlertDialog);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(Message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int back)
            {
                //if user pressed "yes", then he is allowed to exit from application
                dialog.dismiss();
                notifyThis();

            }
        });
        builder.show().getWindow().setLayout(1100,1000);;
    }


    // To sent notification to user
    public  void notifyThis()
    {
        Fragment mFeedFragment=null;

        String channelId = mContext.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this.mContext, channelId)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.food)
                        .setTicker("{Your tiny message}")
                        .setContentTitle("Your Order is Preparing")
                        .setContentText("Tap to view details")
                        .setContentInfo("Info");

        Intent resultIntent = new Intent(this.mContext,ApprovedRequestStatus.class); //to open an activity on touch notification
        PendingIntent resultPendingIntent = PendingIntent
                .getActivity(this.mContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =(NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
