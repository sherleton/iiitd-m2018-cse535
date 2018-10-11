package com.example.nikhi.quizeria;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

import static android.app.Activity.RESULT_OK;

public class QuestionDetail extends Fragment implements View.OnClickListener {

    private Button submit, save, clear;
    private TextView questiondetail;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private int input;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String ques;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.questiondetail, container, false);
        submit = v.findViewById(R.id.submit1);
        save = v.findViewById(R.id.save);
        questiondetail = v.findViewById(R.id.textView);
        radioGroup = v.findViewById(R.id.radiogroup);
        clear = v.findViewById(R.id.clear);

        submit.setOnClickListener(this);
        save.setOnClickListener(this);
        clear.setOnClickListener(this);

        Bundle bundle = getArguments();

        ques = bundle.getString("Question");
        input = bundle.getInt("Input");
        int answer = bundle.getInt("Answer");

        questiondetail.setText(ques);
        radioButton = v.findViewById(radioGroup.getCheckedRadioButtonId());

        if(input == 1)
            radioGroup.check(R.id.True);
        else if(input == 2)
            radioGroup.check(R.id.False);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = v.findViewById(radioGroup.getCheckedRadioButtonId());
                if(radioButton != null) {
                    String what = radioButton.getText().toString();
                    if (what.equals("True")) {
                        input = 1;
                    } else if (what.equals("False")) {
                        input = 2;
                    }
                }
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == submit) {

            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {

                db2csv();
                sqLiteOpenHelper = new QuizDatabase_2016061(getContext());
                SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("INPUT", -1);
                sqLiteDatabase.update("QUESTIONS", contentValues, "OPTION1=?", new String[]{"True"});

                Intent intent = new Intent(getActivity(), MainActivity_2016061.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        else if(v == save){

            sqLiteOpenHelper = new QuizDatabase_2016061(getContext());
            sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("INPUT", input);
            sqLiteDatabase.update("QUESTIONS", contentValues, "QUESTION=?", new String[]{ques});

            Intent intent = new Intent(getContext(), QuestionDetail.class);
            intent.putExtra("Input", input);
            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
            getFragmentManager().popBackStack();
        }
        else if(v == clear) {
            radioGroup.clearCheck();
            input = -1;
        }
    }

    public void db2csv(){

        checkpermission();
        File directory = new File(Environment.getExternalStorageDirectory(), "/store");

        if(directory.exists()){

        }
        else{
            boolean x = directory.mkdirs();
        }

        File file = new File(directory, "quiz.csv");

        try{
            boolean x = file.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
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
