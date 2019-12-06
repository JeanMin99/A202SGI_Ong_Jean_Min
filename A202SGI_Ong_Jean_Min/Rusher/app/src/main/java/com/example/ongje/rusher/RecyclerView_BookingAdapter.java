package com.example.ongje.rusher;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerView_BookingAdapter extends RecyclerView.Adapter<RecyclerView_BookingAdapter.MyViewHolder>{

    private List<Info_RequestData> _data; //To access array list data

    public RecyclerView_BookingAdapter(List<Info_RequestData>data)
    {
        _data=data;
    }

    private RecyclerView_BookingAdapter.OnItemClickListener listener;
    public interface OnItemClickListener
    {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(RecyclerView_BookingAdapter.OnItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_BookingAdapter.MyViewHolder holder, final int position)
    {

        // To display saved data
        Info_RequestData note= _data.get(position);
        TextView lblstu = holder._lblStu;
        lblstu.setText(note.stuID);

        TextView lblRoomType = holder._lblRoomType;
        lblRoomType.setText(note.roomType);

        TextView lblNumOfStudent = holder._lblNumOfStudent;
        lblNumOfStudent.setText(note.numOfStudent);

        TextView lblDurationBooking = holder._lblDurationBooking;
        lblDurationBooking.setText(note.durationBooking);

        TextView lblBookingDate = holder._lblBookingDate;
        lblBookingDate.setText(note.bookDate);

        TextView lblStatus = holder._lblStatus;
        lblStatus.setText(note.status_Booking);
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        // To display data accordingly using ID
        public TextView _lblStu,_lblRoomType,_lblNumOfStudent,_lblDurationBooking,_lblBookingDate,_lblStatus;

        public MyViewHolder(final View itemView)
        {
            super(itemView);

            _lblStu=itemView.findViewById(R.id.lblStuBookType);

            _lblRoomType=itemView.findViewById(R.id.lblRoomType);
            _lblNumOfStudent=itemView.findViewById(R.id.lblStuNum);
            _lblDurationBooking=itemView.findViewById(R.id.lblDuration);
            _lblBookingDate=itemView.findViewById(R.id.lblDate);
            _lblStatus=itemView.findViewById(R.id.lblStatusBook);

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
    public RecyclerView_BookingAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // load row layout
        View rowLayout = inflater.inflate(R.layout.recyclerview_boooking, parent, false);

        RecyclerView_BookingAdapter.MyViewHolder viewHolder = new RecyclerView_BookingAdapter.MyViewHolder(rowLayout);

        return viewHolder;
    }
}
