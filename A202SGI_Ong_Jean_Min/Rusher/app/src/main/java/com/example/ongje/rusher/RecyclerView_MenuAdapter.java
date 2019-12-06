package com.example.ongje.rusher;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_MenuAdapter extends RecyclerView.Adapter<RecyclerView_MenuAdapter.MyViewHolder> {
    private List<Info_RequestData> _data;//To access array list data

    public RecyclerView_MenuAdapter(List<Info_RequestData>data)
    {
        _data=data;
    }
    private RecyclerView_MenuAdapter.OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(RecyclerView_MenuAdapter.OnItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_MenuAdapter.MyViewHolder holder, final int position)
    {
        // To display saved data
        Info_RequestData note= _data.get(position);

        TextView lblID = holder._studentID;
        lblID.setText(note.studentID);
        TextView lblFoodType = holder._foodType_;
        lblFoodType.setText(note.foodType);
        TextView lblquantity = holder._quantity;
        lblquantity.setText(note.quantity);
        TextView lblRemarks = holder._remarks;
        lblRemarks.setText(note.remarks);
        TextView lblTotal = holder._total;
        lblTotal.setText(note.total);
        TextView lblStatus = holder._lblStatus;
        lblStatus.setText(note.status_Reserve);

    }
    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        // To display data accordingly using ID
        public TextView _studentID,_foodType_,_quantity,_remarks,_total,_lblStatus;

        public MyViewHolder(final View itemView)
        {
            super(itemView);
            _studentID=itemView.findViewById(R.id.lblStudentID);
            _foodType_=itemView.findViewById(R.id.lblFoodType);
            _quantity=itemView.findViewById(R.id.lblQuantity);
            _remarks=itemView.findViewById(R.id.lblRemarks);
            _total=itemView.findViewById(R.id.lblTotal);
            _lblStatus=itemView.findViewById(R.id.lblStatusReserve);

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
    public RecyclerView_MenuAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // load row layout
        View rowLayout = inflater.inflate(R.layout.recyclerview_reservation, parent, false);
        RecyclerView_MenuAdapter.MyViewHolder viewHolder = new RecyclerView_MenuAdapter.MyViewHolder(rowLayout);
        return viewHolder;

    }
}
