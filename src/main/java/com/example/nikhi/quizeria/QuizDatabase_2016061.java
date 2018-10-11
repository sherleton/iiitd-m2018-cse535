package com.example.nikhi.quizeria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class QuizDatabase_2016061 extends SQLiteOpenHelper {

    private static final String name = "QuizDB.db";
    private SQLiteDatabase db;
    private ArrayList<QInfo> list;

    public QuizDatabase_2016061(Context context) {
        super(context, name, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE QUESTIONS (_ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, OPTION1 TEXT, OPTION2 TEXT, ANSWER INTEGER, INPUT INTEGER);");
        list = new ArrayList<>();
        list.add(new QInfo("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.", 1));
        list.add(new QInfo("Freeware is software that is available for use at no monetary cost.", 1));
        list.add(new QInfo("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.", 2));
        list.add(new QInfo("The hexadecimal number system contains digits from 1 - 15.", 2));
        list.add(new QInfo("Octal number system contains digits from 0 - 7.", 1));
        list.add(new QInfo("MS Word is a hardware.", 2));
        list.add(new QInfo("CPU controls only input data of computer.", 2));
        list.add(new QInfo("CPU stands for Central Performance Unit.", 2));
        list.add(new QInfo("The Language that the computer can understand is called Machine Language.", 1));
        list.add(new QInfo("Magnetic Tape used random access method.", 2));
        list.add(new QInfo("Twitter is an online social networking and blogging service.", 2));
        list.add(new QInfo("Worms and trojan horses are easily detected and eliminated by antivirus software.", 1));
        list.add(new QInfo("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.", 1));
        list.add(new QInfo("GNU / Linux is a open source operating system.", 1));
        list.add(new QInfo("You cannot format text in an e-mail message.", 2));
        list.add(new QInfo("You type the body of a reply the same way you would type the body of a new message.", 1));
        list.add(new QInfo("You can store Web-based e-mail messages in online folders.", 1));
        list.add(new QInfo("You can delete e-mails from a Web-based e-mail account.", 1));
        list.add(new QInfo("Web-based e-mail accounts do not required passwords.", 2));
        list.add(new QInfo("You can sign up for Web-based e-mail without accepting the Web site's terms and conditions.", 2));
        list.add(new QInfo("Your e-mail address must be unique.", 1));
        list.add(new QInfo("You cannot send a file from a Web-based e-mail account.", 2));
        list.add(new QInfo("There is only one way to create a new folder.", 2));
        list.add(new QInfo("You can only store messages in a new folder if they are received after you creat the folder. ", 2));
        list.add(new QInfo("In Outlook, you must store all of your messages in the Inbox.", 2));
        list.add(new QInfo("New folders must all be at the same level.", 2));
        list.add(new QInfo("You cannot edit Contact forms.", 2));
        list.add(new QInfo("Pressing the Delete key and clicking the Delete button produce the same result.", 1));
        list.add(new QInfo("You should always open and attachment before saving it.", 2));
        list.add(new QInfo("The Delete key is for deleting text, it will not delete messages from your Inbox.", 2));

        for(int i = 0; i < list.size(); i++)
            insertQuestions(list.get(i));
    }

    private void insertQuestions(QInfo qInfo) {
        ContentValues cv = new ContentValues();

        cv.put("QUESTION", qInfo.getQuestion() );
        cv.put("OPTION1", "True");
        cv.put("OPTION2", "False");
        cv.put("ANSWER", qInfo.getAnswer());
        cv.put("INPUT", qInfo.getInput());

        db.insert("QUESTIONS", null,  cv);
    }

    public ArrayList<QInfo> getQuestionList(){
        if(list == null){
            list = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM QUESTIONS", null);

            if(c.moveToFirst())
            {
                list.add(new QInfo(c.getString(c.getColumnIndex("QUESTION")), c.getInt(c.getColumnIndex("ANSWER")), c.getInt(c.getColumnIndex("INPUT"))));
                while(c.moveToNext()) {
                    list.add(new QInfo(c.getString(c.getColumnIndex("QUESTION")), c.getInt(c.getColumnIndex("ANSWER")), c.getInt(c.getColumnIndex("INPUT"))));
                }
            }

            c.close();
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QUESTIONS;");
        onCreate(db);
    }
}