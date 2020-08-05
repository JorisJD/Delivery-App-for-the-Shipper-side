package com.example.sirojiddinjumaev.niholeatitshipper.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sirojiddinjumaev.niholeatitshipper.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView txtOrderId;
    public TextView txtOrderStatus;
    public TextView txtOrderPhone;
    public TextView txtOrderAddress;
    public TextView txtOrderDate;

    public Button btnShipping;



    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderDate = (TextView) itemView.findViewById(R.id.order_date);

        btnShipping = (Button)itemView.findViewById(R.id.btnShipping);





    }




//    @Override
//    public boolean onLongClick(View view) {
//        itemClickListener.onClick(view, getAdapterPosition(), true);
//        return true;
//    }
}
