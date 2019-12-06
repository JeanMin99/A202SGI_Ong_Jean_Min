package com.example.ongje.rusher;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentPendingRoomRequest extends Fragment {

    StudentRequestDB _db;
    View mView;

    public FragmentPendingRoomRequest()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _db = new StudentRequestDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        mView = inflater.inflate(R.layout.fragment_booking_request, container, false);
        buttonSubmit_Clicked1();
        return mView;
    }

    public void buttonSubmit_Clicked1()
    {
        RecyclerView list_with_notes2=mView.findViewById(R.id.recyclerview2);

        ArrayList<Info_RequestData> data_with_Notes=new ArrayList<Info_RequestData>(); // To save data using array list

        Cursor cursor2=_db.getAllDataBooking(); // To get data from database

        // To get data according to database's table column name
        int COL_StuID=cursor2.getColumnIndex("StuID");
        int colIDBooking=cursor2.getColumnIndex("Number");
        int COL_RoomType=cursor2.getColumnIndex("Room_Type");
        int COL_NumOfStu=cursor2.getColumnIndex("Num_Of_Student");
        int COL_duration=cursor2.getColumnIndex("Duration");
        int COL_Booking_Date=cursor2.getColumnIndex("Booking_Date");
        int COL_RESERVE_STATUS=cursor2.getColumnIndex("Status_Booking");

        while (cursor2.moveToNext())
        {
            String sIDBook = cursor2.getString(COL_StuID);
            String sID2 = cursor2.getString(colIDBooking);
            String sRoomType = cursor2.getString(COL_RoomType);
            String sNumOfStu = cursor2.getString(COL_NumOfStu);
            String sDuration = cursor2.getString(COL_duration);
            String sBookingDate = cursor2.getString(COL_Booking_Date);
            String sStatus = cursor2.getString(COL_RESERVE_STATUS);

            // To get data store in array
            Info_RequestData note=new Info_RequestData();

            note.stuID=sIDBook;
            note.roomType=sRoomType;
            note.numOfStudent=sNumOfStu;
            note.durationBooking=sDuration;
            note.bookDate=sBookingDate;
            note.status_Booking=sStatus;
            note.Number=Long.parseLong(sID2);

            data_with_Notes.add(note);

        }

        //To show data using recyclerView
        list_with_notes2.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter2 = new RecyclerView_BookingAdapter(data_with_Notes );
        list_with_notes2.setAdapter(adapter2);

    }

}
