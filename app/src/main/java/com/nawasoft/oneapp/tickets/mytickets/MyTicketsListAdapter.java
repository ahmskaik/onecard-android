package com.nawasoft.oneapp.tickets.mytickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.datalayer.model.MyTicket;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.util.ImagesUtil;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

public class MyTicketsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<MyTicket> myTickets;
    private OnClickCallback clickCallback;

    public MyTicketsListAdapter(Context context, List<MyTicket> myTickets, OnClickCallback clickCallback) {
        this.context = context;
        this.myTickets = myTickets;
        this.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_item_layout, parent, false);
        return new MyTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyTicketViewHolder) holder).bind(myTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return myTickets.size();
    }

    class MyTicketViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public MyTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.ticket_photo);
        }

        public void bind(MyTicket ticket) {
            //Util.loadPictureAndCache(photo, ticket.getTicketPhotoLink());
            ImagesUtil.loadPictureAndStore(context, photo, ticket.getTicketPhotoLink());
            itemView.setOnClickListener(view -> clickCallback.onClick(getAdapterPosition()));
        }
    }
}
