package com.example.ongje.rusher;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LecturerLeaveRequest extends Fragment {

    StudentRequestDB _db;
    View mView;
    TextView date;

    public LecturerLeaveRequest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _db = new StudentRequestDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        mView = inflater.inflate(R.layout.fragment_leave_request, container, false);
        buttonSubmit_Clicked1();
        return mView;
    }

    public void buttonSubmit_Clicked1()
    {
        RecyclerView list_with_notes=mView.findViewById(R.id.recyclerview1);


        ArrayList<Info_RequestData> data_with_Notes=new ArrayList<Info_RequestData>();

        Cursor cursor=_db.getAdminData(); //To access to database function

        // To get data according to database's table column name
        int colID=cursor.getColumnIndex("Number");
        int col2=cursor.getColumnIndex("Student");
        int col3=cursor.getColumnIndex("Leave_Date_To");
        int col4=cursor.getColumnIndex("Leave_Date_From");
        int col5=cursor.getColumnIndex("Leave_Type");
        int col6=cursor.getColumnIndex("Status_Leave");


        while (cursor.moveToNext())
        {
            //To display data accordingly
            String sID = cursor.getString(colID);
            String sTitle = cursor.getString(col2);
            String sDateTo = cursor.getString(col3);
            String sDateFrom = cursor.getString(col4);
            String sReason = cursor.getString(col5);
            String sStatus= cursor.getString(col6);

            Info_RequestData note=new Info_RequestData();
            note.Student=sTitle;
            note.Leave_Date_To=sDateTo;
            note.Leave_Date_From=sDateFrom;
            note.Leave_Reason=sReason;
            note.Status_Leave=sStatus;

            note.Number=Long.parseLong(sID);
            data_with_Notes.add(note);
        }
        // To setting up a a list by using RecyclerView
        list_with_notes.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter = new RecycleView_LeaveAdapter(data_with_Notes );
        list_with_notes.setAdapter(adapter);
    }
}
