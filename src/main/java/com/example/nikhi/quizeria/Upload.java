package com.example.nikhi.quizeria;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.constraint.Constraints.TAG;

public class Upload extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        try{
            File file = null;
            String source = "";
            int countr, ba, bs;
            byte[] store;

            HttpURLConnection httpURLConnection = null;
            DataOutputStream dataOutputStream = null;

            if(file.isFile())
            {
                try {
                    String uri = "";
                    FileInputStream fileInputStream = new FileInputStream(file);
                    URL url = new URL(uri);

                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);

                    httpURLConnection.setUseCaches(false);

                    httpURLConnection.setRequestProperty("ENCTYPE", "multipart/form-data");



                    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                    httpURLConnection.setRequestProperty("Content-Type","multipart/form-data;boundary=" + "*****");
                    httpURLConnection.setRequestProperty("bill", source);

                    dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());

                    dataOutputStream.writeBytes("--" + "*****" + "\r\n");
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                            + source + "\"" + "\r\n");

                    dataOutputStream.writeBytes("\r\n");

                    ba = fileInputStream.available();

                    bs = Math.min(ba, 1024 * 512);
                    store = new byte[1024 * 512];

                    countr = fileInputStream.read(store, 0, bs);

                    while (countr > 0) {

                        dataOutputStream.write(store, 0, bs);
                        ba = fileInputStream.available();
                        bs = Math
                                .min(ba, 1024 * 512);
                        countr = fileInputStream.read(store, 0,
                                bs);

                    }

                    // send multipart form data necesssary after file
                    // data...
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.writeBytes("--*****--\r\n");

//                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = httpURLConnection.getResponseMessage();
                    fileInputStream.close();

                    if (true == true) {

                        dataOutputStream.flush();

                        dataOutputStream.close();

//                        bs = Math.min(ba, 1024 * 512);
//                        store = new byte[1024 * 512];
//
//                        countr = fileInputStream.read(store, 0, bs);
//
//                        while (countr > 0) {
//
//                            dataOutputStream.write(store, 0, bs);
//                            ba = fileInputStream.available();
//                            bs = Math
//                                    .min(ba, 1024 * 512);
//                            countr = fileInputStream.read(store, 0,
//                                    bs);

                    }
                }
                catch (Exception e)
                {
                    Log.d(TAG, "doInBackground: bye");
                }
            }
        }
        catch (Exception e)
        {
            Log.d(TAG, "doInBackground: hellow");
        }

        return null;
    }
}
