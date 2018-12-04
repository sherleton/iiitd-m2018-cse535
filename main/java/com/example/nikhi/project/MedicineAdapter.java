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

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>{

    private Context context;
    private ArrayList<Medicine> list;
    private OnItemClickListener listener;

    public MedicineAdapter(Context context, ArrayList<Medicine> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener{
        void clickToDelete(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.medicine_layout, null);
        MedicineViewHolder holder = new MedicineViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder medicineViewHolder, int i) {
        Medicine medicine = list.get(i);

        medicineViewHolder.name.setText(medicine.getName());
        medicineViewHolder.med.setText(medicine.getMed());
        medicineViewHolder.roll.setText(medicine.getRoll() + "");
        medicineViewHolder.quant.setText(medicine.getQuant() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder{

        TextView name, med, roll, quant;
        Button button;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView2);
            med = itemView.findViewById(R.id.textView3);
            roll = itemView.findViewById(R.id.textView);
            quant = itemView.findViewById(R.id.textView4);
            button = itemView.findViewById(R.id.buttonlol2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.clickToDelete(pos);
                        }
                    }
                }
            });
        }
    }
}
