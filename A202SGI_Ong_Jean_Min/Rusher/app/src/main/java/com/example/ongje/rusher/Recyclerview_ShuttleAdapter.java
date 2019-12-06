package com.example.ongje.rusher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Recyclerview_ShuttleAdapter  extends RecyclerView.Adapter<Recyclerview_ShuttleAdapter.MyViewHolder> {

    private List<Info_RequestData> _data; //To access array list data

    public Recyclerview_ShuttleAdapter(List<Info_RequestData>data)
    {
        _data=data;
    }
    private Recyclerview_ShuttleAdapter.OnItemClickListener listener;
    public interface OnItemClickListener
    {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(Recyclerview_ShuttleAdapter.OnItemClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull Recyclerview_ShuttleAdapter.MyViewHolder holder, final int position)
    {

        // To display saved data
        Info_RequestData note= _data.get(position);
        TextView lblstu = holder._lblStu;
        lblstu.setText(note.studentIDShu);
        TextView lblTime = holder._lblTime;
        lblTime.setText(note.time);
        TextView lblLocation = holder._lblLocation;
        lblLocation.setText(note.locations);
        TextView lblStatus = holder._lblStatus;
        lblStatus.setText(note.status_Shuttle);
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // To display data accordingly using ID
        public TextView _lblStu,_lblTime, _lblLocation,_lblStatus;

        public MyViewHolder(final View itemView)
        {
            super(itemView);
            _lblStu=itemView.findViewById(R.id.lblStuShu);
            _lblTime=itemView.findViewById(R.id.lblTime);
            _lblLocation=itemView.findViewById(R.id.lblLocation);
            _lblStatus=itemView.findViewById(R.id.lblStatusShu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(itemView,position);
                        }
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {
        }
    }
    @NonNull
    @Override
    public Recyclerview_ShuttleAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // load row layout
        View rowLayout = inflater.inflate(R.layout.recyclerview_shuttle, parent, false);

        Recyclerview_ShuttleAdapter.MyViewHolder viewHolder = new Recyclerview_ShuttleAdapter.MyViewHolder(rowLayout);

        return viewHolder;

    }
}
