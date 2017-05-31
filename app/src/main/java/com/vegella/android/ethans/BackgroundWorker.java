package com.vegella.android.ethans;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by shushanth on 29/5/17.
 */



public class BackgroundWorker extends AsyncTask<String,String,String>{

    Context context;
     BackgroundWorker()    //constructor to initialise context that will be used in toast.
    {
        this.context = context;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {   //performs post operation onto database
        String entry_url = "http://10.0.2.2/login_ethans_2.php";
        String fname = params[0];
        String lname = params[1];
        String email= params[2];
        String phone = params[3];
        String course = params[4];

        try {
            URL url = new URL(entry_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));  //below operation for post
            String data = URLEncoder.encode("fName","UTF-8") + "=" + URLEncoder.encode(fname,"UTF-8")+"&"+
                    URLEncoder.encode("lName","UTF-8") + "=" + URLEncoder.encode(lname,"UTF-8")+"&"+
                    URLEncoder.encode("phone","UTF-8") + "=" + URLEncoder.encode(phone,"UTF-8")+"&"+
                    URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email,"UTF-8")+"&"+
                    URLEncoder.encode("course","UTF-8") + "=" + URLEncoder.encode(course,"UTF-8");


            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            return "Details entered successfully...";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String Result) {
       // Toast.makeText(context,Result,Toast.LENGTH_LONG).show();
    }


}
