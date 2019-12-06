package com.example.ongje.rusher;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


public class FragmentFoodReservation extends Fragment {

    // To access food info
    ArrayList<Info_FoodReservation> foods=new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter_FoodReservation mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view= inflater.inflate(R.layout.fragment_food, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_viewMenu);
        mAdapter=new Adapter_FoodReservation(foods,getActivity());

        // To setting up a a list by using RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        foodsDetails();
        mAdapter.notifyDataSetChanged();
        return view;
    }

    private void foodsDetails() // To display food image, name and price
    {
        Info_FoodReservation food=new Info_FoodReservation("Fried Rice",R.drawable.fried_rice,"RM6");
        foods.add(food);
        food = new Info_FoodReservation("Spaghetti",R.drawable.spaghetti,"RM10");
        foods.add(food);
        food = new Info_FoodReservation("Soup Noodle",R.drawable.tomyam,"RM7.00");
        foods.add(food);
        food = new Info_FoodReservation("Set Lunch",R.drawable.set_lunch,"RM12.00");
        foods.add(food);
        food = new Info_FoodReservation("Burger",R.drawable.burger,"RM13.00");
        foods.add(food);
        food = new Info_FoodReservation("Drinks",R.drawable.drinks,"RM6.00");
        foods.add(food);
    }
}
