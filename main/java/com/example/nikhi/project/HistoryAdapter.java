package com.example.nikhi.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private Context context;
    private ArrayList<History> list;

    public HistoryAdapter(Context context, ArrayList<History> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.history_layout, null);
        HistoryViewHolder holder = new HistoryViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int i) {
        History history = list.get(i);

        historyViewHolder.description.setText(history.getDescription());
        historyViewHolder.date.setText(history.getDate());
        historyViewHolder.time.setText(history.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView description, date, time;
        
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.textViewTitle2);
            date = itemView.findViewById(R.id.textView3);
            time = itemView.findViewById(R.id.textView4);
        }
    }
}
