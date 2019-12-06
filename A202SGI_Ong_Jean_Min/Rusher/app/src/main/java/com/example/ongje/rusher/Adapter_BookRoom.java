package com.example.ongje.rusher;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.ArrayList;

public class Adapter_BookRoom extends RecyclerView.Adapter {

    private final ArrayList<Info_BookRoom> dataSet;
    Context mContext;
    int total_types;
    StudentRequestDB myDB;
    String bookDuration = "";
    String roomType="";
    String stuNum="";
    String status="Pending";


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        //Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
        switch (viewType)
        {
                case Info_BookRoom.TEXT_TYPE:
                    view=LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type,parent,false);
                    return new TextTypeViewHolder(view);
                case Info_BookRoom.IMAGE_TYPE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                    return new ImageTypeViewHolder(view);

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) //to reflect the item at the given position.
    {
        switch(dataSet.get(position).type)
        {
                case 0:
                    return Info_BookRoom.TEXT_TYPE;
                case 1:
                    return Info_BookRoom.IMAGE_TYPE;
                default:
                    return -1;
        }
    }
    @Override //Called by RecyclerView to display the data at the specified position
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int listPosition)
    {
        Info_BookRoom object=dataSet.get(listPosition);

        if(object!=null) //to reflect the item at the given position.
        {
            switch (object.type)
            {
                    case Info_BookRoom.TEXT_TYPE:
                        ((TextTypeViewHolder) holder).txtType.setText(object.text);
                        break;
                    case Info_BookRoom.IMAGE_TYPE:
                        ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                        ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    myDB=new StudentRequestDB(mContext); // To accese to database
                    final Dialog dialog2 = new Dialog(mContext);
                    dialog2.setContentView(R.layout.prompts_box); //To pop display after button is clicked
                    dialog2.setTitle("Book Room Now?");

                    Calendar calendar;
                    final int year,month,day;
                    calendar = Calendar.getInstance();
                    year=calendar.get(Calendar.YEAR);
                    month=calendar.get(Calendar.MONTH);
                    day=calendar.get(Calendar.DAY_OF_MONTH);
                    final RadioButton radioOne = (RadioButton) dialog2.findViewById(R.id.radioButtonOneHR);
                    final RadioButton radioTwo=(RadioButton)dialog2.findViewById(R.id.radioButtonTwoHR) ;
                    final RadioGroup bookHour = (RadioGroup) dialog2.findViewById(R.id.radioGroup2);
                    final Spinner spinnerRoomType=(Spinner)dialog2.findViewById(R.id.spinnerRoomType);
                    Spinner spinnerStudentNum=(Spinner)dialog2.findViewById(R.id.spinnerStudentNum);
                    //Spinner to display dropdown list of room type
                    spinnerRoomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        roomType=parent.getItemAtPosition(position).toString(); // To save selected spinner value into database
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                    //Spinner to display dropdown list of number of student
                    spinnerStudentNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                        {
                            stuNum=parent.getItemAtPosition(position).toString(); // To save selected spinner value into database
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    radioOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast toast = Toast.makeText(mContext.getApplicationContext(),
                                    "One hour is selected",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    radioTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Toast toast = Toast.makeText(mContext.getApplicationContext(),
                                    "Two hour is selected",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                    bookHour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if(checkedId==R.id.radioButtonOneHR)
                            {
                                bookDuration ="One hour";
                            }
                            else if(checkedId==R.id.radioButtonTwoHR)
                            {
                                bookDuration ="Two Hour";
                            }
                        }
                    });

                    TextView textViewStudent = (TextView) dialog2.findViewById(R.id.textViewStudent);
                    TextView selectedRoom = (TextView) dialog2.findViewById(R.id.selectedRoom);
                    TextView textViewDisplayBooking = (TextView) dialog2.findViewById(R.id.textViewDisplayBooking);
                    final TextView displayDateBook = (TextView) dialog2.findViewById(R.id.displayDateBook);
                    final TextView duration = (TextView) dialog2.findViewById(R.id.duration);
                    Button buttonDateBook = (Button) dialog2.findViewById(R.id.buttonDateBook);
                    Button dialogButton = (Button) dialog2.findViewById(R.id.dialogButtonOK);
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            //Error handling
                            if(displayDateBook.getText().length()<1 ||bookHour.getCheckedRadioButtonId()==-1)
                            {
                                displayDateBook.setError("This field is required");
                                radioTwo.setError("This field is required");
                                Toast.makeText(mContext, "Do Not Empty Any Field Empty!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                dialog2.dismiss();
                                //To save data into database
                                boolean isInserted = myDB.insertDataBooking(((MainActivity.value)), roomType.toString(),
                                        stuNum.toString(), bookDuration.toString(), displayDateBook.getText().toString(), status);
                                if (isInserted == true) {

                                    //To show latest user input
                                    Cursor res = myDB.getDataBooking();
                                    if (res.getCount() == -1) {
                                        // show message
                                        showMessage("Error", "Nothing found");
                                        return;
                                    }

                                    // To show summary of selected room details
                                    StringBuffer buffer = new StringBuffer();
                                    while (res.moveToNext()) {
                                        buffer.append("Student Email: " + MainActivity.value + "\n");
                                        buffer.append("Booking Room: " + res.getString(2) + "\n");
                                        buffer.append("Number of Student: " + res.getString(3) + "\n");
                                        buffer.append("Duration: " + res.getString(4) + "\n");
                                        buffer.append("Booking Date: " + res.getString(5) + "\n");
                                        buffer.append("Status:" + status + "\n");
                                    }
                                    // Show all data
                                    showMessage("Request Sent", buffer.toString());
                                    //showMessage("Data",buffer.toString());

                                } else {
                                    Toast toast = Toast.makeText(mContext.getApplicationContext(),
                                            "Data is not inserted",
                                            Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                            }
                        });

                    Button dialogButtonFoodCancel = (Button) dialog2.findViewById(R.id.dialogButtonFoodCancel);
                    dialogButtonFoodCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });

                    //To show room info in dropdown list
                    String[]dataArray=new String[]{"Discussion Room 1, Library","Discussion Room 2, Library","Discussion Room 3, " +
                            "Library","Discussion Room 4, Library","Music Room, Level 5","Basketball Court, Level 5"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, dataArray);
                    spinnerRoomType.setAdapter(adapter);

                    String[]dataArrayNum=new String[]{"4","6","8","10"}; //To show room info in dropdown list
                    ArrayAdapter<String> adapterNum = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, dataArrayNum);
                    spinnerStudentNum.setAdapter(adapterNum);
                    // if button is clicked, close the custom dialog
                    buttonDateBook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            android.app.DatePickerDialog datePicker= new android.app.DatePickerDialog(mContext,R.style.AlertDialogDate, new DatePickerDialog.OnDateSetListener()
                            {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                                {
                                    //sets date in displayDateBook
                                    displayDateBook.setText(dayOfMonth+"-"+ (month+1) +"-"+year);
                                }
                            }, year, month, day);
                            //shows DatePickerDialog
                            datePicker.show();
                        }
                    });
                    dialog2.show();
                }
            });
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

            }
        });
        builder.show().getWindow().setLayout(1100,1000);
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    //It gets a reference to the main view its holding in the constructor. The custom ViewHolder will grab all the subviews
    public static class TextTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtType;
        CardView cardView;
        String [] spinnerValues;

        public TextTypeViewHolder(View itemView)
        {
            super(itemView);

            this.txtType=(TextView)itemView.findViewById(R.id.type);
            this.cardView=(CardView)itemView.findViewById(R.id.card_view);
            this.spinnerValues=spinnerValues;
        }

    }

    // It gets a reference to the main view its holding in the constructor. The custom ViewHolder will grab all the subviews
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView)
        {
            super(itemView);
            this.txtType=(TextView)itemView.findViewById(R.id.type);
            this.image=(ImageView) itemView.findViewById(R.id.background);
        } 
    }

    //Change constructor of adapter
    public Adapter_BookRoom(ArrayList<Info_BookRoom>data, Context context)
    {
        this.dataSet=data;
        this.mContext=context;
        total_types=dataSet.size();

    }
}
