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


public class FragmentApprovedFoodReserve extends Fragment {
    StudentRequestDB _db;
    View mView;

    public FragmentApprovedFoodReserve() {
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
        mView = inflater.inflate(R.layout.fragment_approved, container, false);
        buttonSubmit_Clicked2();
        return mView;
    }

    public void buttonSubmit_Clicked2()
    {
        RecyclerView list_with_notes3=mView.findViewById(R.id.recyclerview33);

        ArrayList<Info_RequestData> data_with_Notes=new ArrayList<Info_RequestData>();

        Cursor cursor3=_db.getAllDataReservation();//To access to database function

        // To get data according to database's table column name
        int COL_NUMBER=cursor3.getColumnIndex("Number");
        int COL_Student=cursor3.getColumnIndex("Student_ID");
        int COL_SELECTION=cursor3.getColumnIndex("Food_Type");
        int COL_QUANTITY=cursor3.getColumnIndex("Quantity");
        int COL_REMARKS=cursor3.getColumnIndex("Remarks");
        int COL_TOTAL=cursor3.getColumnIndex("Total");
        int COL_RESERVE_STATUS=cursor3.getColumnIndex("Status_Reserve");

        while (cursor3.moveToNext())
        {
            //To display data accordingly
            String sID3 = cursor3.getString(COL_NUMBER);
            String sStudentID = cursor3.getString(COL_Student);
            String sFoodType = cursor3.getString(COL_SELECTION);
            String sQuantity = cursor3.getString(COL_QUANTITY);
            String sRemarks = cursor3.getString(COL_REMARKS);
            String sTotal = cursor3.getString(COL_TOTAL);
            String sStatus = cursor3.getString(COL_RESERVE_STATUS);

            Info_RequestData note=new Info_RequestData();

            note.studentID=sStudentID;
            note.foodType=sFoodType;
            note.quantity=sQuantity;
            note.remarks=sRemarks;
            note.total=sTotal;
            note.status_Reserve=sStatus;
            note.Number=Long.parseLong(sID3);
            data_with_Notes.add(note);

        }
        // To setting up a a list by using RecyclerView
        list_with_notes3.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter3 = new RecyclerView_MenuAdapter(data_with_Notes );
        list_with_notes3.setAdapter(adapter3);

    }
}

