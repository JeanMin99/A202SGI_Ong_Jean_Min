package com.example.ongje.rusher;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


public class FragmentBook extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_book, container, false);

        // To display room image, name and description
        ArrayList<Info_BookRoom> list=new ArrayList<>();
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Discussion Room 1, Library",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.discussion_room1));
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Discussion Room 2, Library",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.discussion_room2));
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Discussion Room 3, Library",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.discussion_room3));
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Discussion Room 4, Library",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.discussion_room4));
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Music Room, Level 5",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.music_room));
        list.add(new Info_BookRoom(Info_BookRoom.TEXT_TYPE,"Basketball Court, Level 5",0));
        list.add(new Info_BookRoom(Info_BookRoom.IMAGE_TYPE,"",R.drawable.basketball_court));

        Adapter_BookRoom adapter=new Adapter_BookRoom(list,getActivity());

        // To setting up a a list by using RecyclerView
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),OrientationHelper.HORIZONTAL,false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return  view;
    }
}
