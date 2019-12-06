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

public class FragmentApproved_ShuttleRequest extends Fragment {

    StudentRequestDB _db;
    View mView;
    TextView date;

    public FragmentApproved_ShuttleRequest()
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
        mView = inflater.inflate(R.layout.fragment_shuttle_request, container, false);

        buttonSubmit();
        return mView;
    }

    public void buttonSubmit()
    {
        RecyclerView list_with_notes4=mView.findViewById(R.id.recyclerview4);

        ArrayList<Info_RequestData> data_with_Notes=new ArrayList<Info_RequestData>();

        Cursor cursor4=_db.getAllDataShuttle(); //To access to database function

        // To get data according to database's table column name
        int COL_StuIDShu=cursor4.getColumnIndex("StudentID");
        int colIDShuttle=cursor4.getColumnIndex("Number");
        int COL_Time=cursor4.getColumnIndex("Time");
        int COL_LOCATION=cursor4.getColumnIndex("Location");
        int COL_SHUTTLE_STATUS=cursor4.getColumnIndex("Status_Shuttle");

        while (cursor4.moveToNext())
        {
            //To display data accordingly
            String sIDShu = cursor4.getString(COL_StuIDShu);
            String sID4 = cursor4.getString(colIDShuttle);
            String sTime = cursor4.getString(COL_Time);
            String sLocation = cursor4.getString(COL_LOCATION);
            String sStatus = cursor4.getString(COL_SHUTTLE_STATUS);
            Info_RequestData note=new Info_RequestData();

            note.studentIDShu=sIDShu;
            note.time=sTime;
            note.locations=sLocation;
            note.status_Shuttle=sStatus;
            note.Number=Long.parseLong(sID4);
            data_with_Notes.add(note);

        }

        // To setting up a a list by using RecyclerView
        list_with_notes4.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter adapter4 = new Recyclerview_ShuttleAdapter(data_with_Notes);
        list_with_notes4.setAdapter(adapter4);
    }
}
