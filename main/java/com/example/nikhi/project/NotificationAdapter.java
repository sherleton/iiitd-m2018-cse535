package com.example.nikhi.project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    private Activity activity;
    private ArrayList<Notification> list;
    private NotificationAdapter.OnItemClickListener listener;

    public NotificationAdapter(Activity activity, ArrayList<Notification> list) {
        this.activity = activity;
        this.list = list;
    }

    public interface OnItemClickListener{
        void clickToDelete(int pos);
    }

    public void setOnItemClickListener(NotificationAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.notification_layout, null);
        NotificationViewHolder holder = new NotificationViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {
        final Notification notification = list.get(i);
        final int pos = i;

        notificationViewHolder.name.setText(notification.getHeader());
        notificationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setTitle(notification.getHeader());
                    alert.setMessage(notification.getDisplay());

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            listener.clickToDelete(pos);
                        }
                    });

                    alert.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView1);

        }
    }
}
