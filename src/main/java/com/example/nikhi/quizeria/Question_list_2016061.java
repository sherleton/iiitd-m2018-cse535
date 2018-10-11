package com.example.nikhi.quizeria;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikhi.quizeria.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.security.Permission;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class Question_list_2016061 extends Fragment {

    private ArrayList<QInfo> list = new ArrayList<>();
    private Button submit;
    private RecyclerView r;
    private QuestionAdapter_2016061 adapter;
    private int tempInput, temppos;
    private SQLiteOpenHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.questionlist, container, false);

        submit = v.findViewById(R.id.submit);
        r = v.findViewById(R.id.recyclerview);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {

                    db2csv();

                    SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("INPUT", -1);
                    sqLiteDatabase.update("QUESTIONS", contentValues, "OPTION1=?", new String[]{"True"});

                    Intent intent = new Intent(getActivity(), MainActivity_2016061.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(v.getContext(), layoutManager.getOrientation());
        r.addItemDecoration(dividerItemDecoration);
        r.setLayoutManager(layoutManager);

        db = new QuizDatabase_2016061(getContext());
        if(list.size() <= 0)
            list = ((QuizDatabase_2016061) db).getQuestionList();

        adapter = new QuestionAdapter_2016061(list , v.getContext());
        adapter.setQuestionitem(new QuestionAdapter_2016061.ItemClickListener() {
            @Override
            public void onClick(TextView textView, View view, QInfo q, int i) {

                SQLiteDatabase sqLiteDatabase = ((QuizDatabase_2016061) db).getReadableDatabase();
                Cursor cc = sqLiteDatabase.rawQuery("SELECT * FROM QUESTIONS WHERE _ID = " + (i+1), null);

                String ques = q.getQuestion();
                cc.moveToFirst();
                int input = cc.getInt(cc.getColumnIndex("INPUT"));
                int answer = q.getAnswer();
                temppos = i;

                Bundle bundle = new Bundle();
                bundle.putString("Question", ques);
                bundle.putInt("Input", input);
                bundle.putInt("Answer", answer);

                QuestionDetail questionDetail = new QuestionDetail();
                questionDetail.setArguments(bundle);
                questionDetail.setTargetFragment(Question_list_2016061.this, 1001);
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(this.getClass().getName());
                fragmentTransaction.replace(R.id.fgroup, questionDetail, questionDetail.getTag()).commit();
            }
        });

        r.setAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1001) {
            tempInput = data.getIntExtra("Input", -1);
            list.get(temppos).setInput(tempInput);
        }
    }

    public void db2csv(){
        checkpermission();
        File directory = new File(Environment.getExternalStorageDirectory(), "");

        if(directory.exists()){

        }
        else{
            boolean x = directory.mkdirs();
        }

        File file = new File(directory, "quiz.csv");

        try{
            boolean x = file.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM QUESTIONS", null);
            csvWriter.writeNext(c.getColumnNames());
            while(!c.isAfterLast())
                csvWriter.writeNext(new String[]{Integer.toString(c.getInt(0)), c.getString(1), c.getString(2), c.getString(3), Integer.toString(c.getInt(4)), Integer.toString(c.getInt(5))});
            csvWriter.close();
            c.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void checkpermission()
    {
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
}
