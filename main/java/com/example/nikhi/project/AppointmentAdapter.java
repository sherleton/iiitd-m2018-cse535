package com.example.nikhi.project;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>{

    private Context context;
    private ArrayList<Appointment> list;
    private OnItemClickListener listener;

    public AppointmentAdapter(Context context, ArrayList<Appointment> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener{
        void clickToDelete(int pos, String s, String s1);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.appointment_layout, null);
        AppointmentViewHolder holder = new AppointmentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder appointmentViewHolder, int i) {
        Appointment appointment = list.get(i);

        appointmentViewHolder.name.setText(appointment.getName());
        appointmentViewHolder.description.setText(appointment.getDescription());
        appointmentViewHolder.date.setText(appointment.getDate());
        appointmentViewHolder.time.setText(appointment.getTime());

        if(list.get(i).getAccept().equals("true"))
        {
            appointmentViewHolder.button.setText("COMPLETE");
            appointmentViewHolder.button.setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        TextView name, description, date, time;
        Button button;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView2);
            description = itemView.findViewById(R.id.textViewTitle2);
            date = itemView.findViewById(R.id.textView3);
            time = itemView.findViewById(R.id.textView4);
            button = itemView.findViewById(R.id.buttonlol2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        String s = String.valueOf(button.getText());
                        if(s.equals("ACCEPT"))
                        {
                            int pos = getAdapterPosition();
                            String ss = list.get(pos).getName();
                            if(pos != RecyclerView.NO_POSITION){
                                listener.clickToDelete(pos, ss, s);
                            }
                            button.setText("COMPLETE");
                            button.setBackgroundColor(Color.BLUE);
                        }
                        else
                        {
                            int pos = getAdapterPosition();
                            String ss = list.get(pos).getName();
                            if(pos != RecyclerView.NO_POSITION){
                                listener.clickToDelete(pos, ss, s);
                            }
                        }
                    }
                }
            });
        }
    }
}
