package com.example.nikhi.quizeria;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuestionAdapter_2016061 extends RecyclerView.Adapter<QuestionAdapter_2016061.QuestionHold> {

    private ArrayList<QInfo> list;
    private Context c;
    private ItemClickListener questionitem;

    QuestionAdapter_2016061(ArrayList<QInfo> list, Context c)
    {
        this.c = c;
        this.list = list;
    }

    public interface ItemClickListener{
        void onClick(TextView textView, View view, QInfo q, int i);
    }

    public void setQuestionitem(ItemClickListener questionitem){
        this.questionitem = questionitem;
    }

    @NonNull
    @Override
    public QuestionHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.question, viewGroup, false);
        return new QuestionHold(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionHold questionHold, final int i) {
        final QInfo q = list.get(i);
        questionHold.qe.setText("Q" + (i+1) + " " + q.getQuestion());

        questionHold.qe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(questionitem != null) {
                    questionitem.onClick(questionHold.qe, v, q, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionHold extends RecyclerView.ViewHolder {

        TextView qe;

        public QuestionHold(@NonNull View itemView) {
            super(itemView);
            qe = itemView.findViewById(R.id.question);
        }
    }

    public ArrayList<QInfo> getList() {
        return list;
    }
}
