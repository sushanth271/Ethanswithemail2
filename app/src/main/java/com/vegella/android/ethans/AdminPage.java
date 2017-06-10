package com.vegella.android.ethans;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shushanth on 3/6/17.
 */

public class AdminPage extends Activity {

    CalendarView calendarView;
    LinearLayout root;
    TextView view1;
    TextView view2;
    EditText view3;
    ListView lv;
    String json_result;
    String dateInput;
    String method;
    String JSON_STRING;
    String fname;
    String email;
    String course;
    int email_flag;
    Button send_email_btn;
    long date_i;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        lv = (ListView)findViewById(R.id.lv);
        calendarView = (CalendarView)findViewById(R.id.calendar);
        send_email_btn = (Button)findViewById(R.id.send_email_btn);

       calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

               dateInput = year + "/" + (month+1) + "/" + (dayOfMonth);
               new BackgroundTask().execute(dateInput);

               if(json_result==null)
                   return;


              /* Intent intent = new Intent(AdminPage.this,DisplayListView.class);
               intent.putExtra("json_data",json_result);
               startActivity(intent);*/



               JSONObject jsonObject = null;
               try {
                   jsonObject = new JSONObject(json_result);
                   JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                   ArrayList<String> listItems = new ArrayList<String>();
                   ArrayAdapter<String> adapter;


                   adapter = new ArrayAdapter<String>(AdminPage.this,android.R.layout.simple_list_item_1,listItems);
                   lv.setAdapter(adapter);

                   for(int i=0;i<jsonArray.length();i++) {
                       JSONObject entry = jsonArray.getJSONObject(i);
                       fname = entry.getString("fname");
                       email = entry.getString("email");
                       course = entry.getString("course");

                       email_flag = Integer.valueOf(entry.getString("email_flag"));
                       if(email_flag == 1) {
                           fname = fname + "  " + email + " " + course + " " + "NOT SENT";
                           send_email_btn.setEnabled(true);

                       }
                       else
                       {
                           fname = fname + "  " + email + " " + course + " " + "SENT";
                       }
                       listItems.add(fname);

                       adapter.notifyDataSetChanged();
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }




           }
       });





        send_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTaskEmailer().execute(dateInput);


            }
        });


    }
    class BackgroundTask extends AsyncTask<String,Void,String>
    {

        String json_url;
        @Override
        protected void onPreExecute() {
           json_url = "http://10.0.2.2/login_ethans_3.php";
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                JSON_STRING = "";
                while ((JSON_STRING = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_result = result;
        }
    }



    class BackgroundTaskEmailer extends AsyncTask<String,Void,String>
    {

        String entry_url;
        @Override
        protected void onPreExecute() {
            entry_url = "http://10.0.2.2/login_ethans_4.php";
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(entry_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));  //below operation for post
               String data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
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
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            //json_result = result;
        }
    }






}
