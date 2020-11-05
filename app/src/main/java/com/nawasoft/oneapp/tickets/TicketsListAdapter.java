package com.nawasoft.oneapp.tickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nawasoft.datalayer.model.Ticket;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnClickCallback;
import com.nawasoft.oneapp.util.Util;

import java.util.List;

public class TicketsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Ticket> tickets;
    private OnClickCallback clickCallback;

    public TicketsListAdapter(Context context, List<Ticket> tickets, OnClickCallback clickCallback) {
        this.context = context;
        this.tickets = tickets;
        this.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_item_layout, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TicketViewHolder) holder).bind(tickets.get(position));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.ticket_photo);
        }

        public void bind(Ticket ticket) {
            Util.loadPictureAndCache(photo, ticket.getTicketPhotoLink());
            itemView.setOnClickListener(view -> clickCallback.onClick(getAdapterPosition()));
        }
    }
}
