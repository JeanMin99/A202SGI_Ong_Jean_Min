package com.example.ongje.rusher;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;


public class FragmentLeave extends Fragment {

    StudentRequestDB myDB;
    RadioButton radioMedical,radioPersonal;
    RadioGroup leaveReason;
    String leaveType="";

    Button btnDate,btnDateTo,submit;
    Calendar calendar;
    EditText displayDate,displayDateTo;
    int year,month,day;
    String status="Pending";

    EditText email;

    View v;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_leave, container, false);
        v = view;
        myDB=new StudentRequestDB(getActivity());

        //To get calendar
        calendar = Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        displayDate=(EditText)view.findViewById(R.id.displayDate);
        displayDateTo=(EditText)view.findViewById(R.id.displayDateTo);
        radioMedical=(RadioButton)view.findViewById(R.id.radioButton1) ;
        radioPersonal=(RadioButton)view.findViewById(R.id.radioButton2) ;
        leaveReason=(RadioGroup)view.findViewById(R.id.radioGroup) ;
        btnDate=(Button)view.findViewById(R.id.buttonDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                android.app.DatePickerDialog datePicker = new android.app.DatePickerDialog(
                        (getActivity()),
                        R.style.AlertDialogDate,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                //sets date in displayDate
                                displayDate.setText(dayOfMonth+"-"+ (month+1) +"-"+year);
                            }
                        },
                        year,
                        month,
                        day);

                //shows DatePickerDialog
                datePicker.show();

            }
            });
        btnDateTo=(Button)view.findViewById(R.id.buttonDateTo);
        btnDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                android.app.DatePickerDialog datePicker = new android.app.DatePickerDialog(
                        (getActivity()),
                        R.style.AlertDialogDate,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                //sets date in displayDate
                                displayDateTo.setText(dayOfMonth+"-"+ (month+1) +"-"+year);
                            }
                        },
                        year,
                        month,
                        day);

                //shows DatePickerDialog
                datePicker.show();

            }
        });

        // To get and save radio button value into database
        leaveReason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton1)
                {
                    leaveType="Medical Leave";

                }
                else if(checkedId==R.id.radioButton2)
                {
                    leaveType="Personal Leave";
                }
            }
        });

        //To show toast message when radio button is clicked
        radioMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Medical Leave is Selected",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        radioPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Personal Leave is Selected",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        submit = v.findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                // Error handling
                if(displayDate.getText().length()<1 ||displayDateTo.getText().length()<1 ||leaveReason.getCheckedRadioButtonId()==-1) {

                    displayDate.setError("This field is required");
                    displayDateTo.setError("This field is required");
                    radioPersonal.setError("This field is required");

                    Toast.makeText(getActivity(), "Do Not Empty Any Field Empty!", Toast.LENGTH_LONG).show();
                }

                else {

                    boolean isInserted = myDB.insertData(MainActivity.value, displayDateTo.getText().toString(), // To insert user's email using login data.
                            displayDate.getText().toString(), leaveType.toString(), status);
                    if (isInserted == true) {
                        Cursor res = myDB.getData();
                        if (res.getCount() == -1) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        // To show summary of user submitted data
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Student Email: " + MainActivity.value + "\n");
                            buffer.append("Leave Type: " + res.getString(4) + "\n");
                            buffer.append("Leave Date From: " + res.getString(3) + "\n");
                            buffer.append("Leave Date To: " + res.getString(2) + "\n");
                            buffer.append("Status:" + status + "\n");

                        }

                        // Show all data
                        showMessage("Request Sent", buffer.toString());

                    } else {
                        Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        return view;

    }

    public void showMessage(String title,String Message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(),R.style.AlertDialog);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int back) {
                //To clear user typed in history
                displayDate.setText("");
                displayDateTo.setText("");
                radioMedical.setChecked(false);
                radioPersonal.setChecked(false);
                displayDate.setError(null);
                radioPersonal.setError(null);
                displayDateTo.setError(null);

            }
        });
        builder.show().getWindow().setLayout(1100,1000);

    }

}
