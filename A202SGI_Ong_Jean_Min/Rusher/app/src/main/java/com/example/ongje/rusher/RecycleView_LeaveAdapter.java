package com.example.ongje.rusher;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


public class RecycleView_LeaveAdapter extends RecyclerView.Adapter<RecycleView_LeaveAdapter.MyViewHolder> {

    private List<Info_RequestData> _data; //To access array list data
    public static String number;

    public RecycleView_LeaveAdapter(List<Info_RequestData>data)
    {
        _data=data;
    }

    private OnItemClickListener listener;
    public interface OnItemClickListener
    {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_LeaveAdapter.MyViewHolder holder, final int position)
    {
        // To display saved data
        Info_RequestData note= _data.get(position);

        TextView lblTitle = holder._lblTitle;
        lblTitle.setText(note.Student);

        TextView lblDateTo = holder._lblDateTo;
        lblDateTo.setText(note.Leave_Date_To);

        TextView lblDateFrom = holder._lblDateFrom;
        lblDateFrom.setText(note.Leave_Date_From);

        TextView lblReason = holder._lblReason;
        lblReason.setText(note.Leave_Reason);

        TextView lblStatus = holder._lblStatus;
        lblStatus.setText(note.Status_Leave);
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // To display data accordingly using ID

        public TextView _lblTitle,_lblDateTo,_lblDateFrom,_lblReason,_lblStatus;

        public MyViewHolder(final View itemView)
        {
            super(itemView);
            _lblTitle=itemView.findViewById(R.id.lblTitle);
            _lblDateTo=itemView.findViewById(R.id.lblDateTo);
            _lblDateFrom=itemView.findViewById(R.id.lblDateFrom);
            _lblReason=itemView.findViewById(R.id.lblReason);
            _lblStatus=itemView.findViewById(R.id.lblStatusLeave);

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
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        // get Resource-Layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // load row layout
        View rowLayout = inflater.inflate(R.layout.recyclerview_items, parent, false);
        //get Resource-Layout
        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(rowLayout);
        return viewHolder;

    }
}
