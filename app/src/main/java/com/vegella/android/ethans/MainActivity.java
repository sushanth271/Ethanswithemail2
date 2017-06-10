package com.vegella.android.ethans;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vegella.android.ethans.R.id.btn_admin;
import static com.vegella.android.ethans.R.id.parent;



public class MainActivity extends AppCompatActivity {

    EditText first_name;
    EditText last_name;
    EditText email_id;
    EditText phone;
    EditText course_st;
    Spinner spinner_s;    //spinner
    ArrayAdapter<CharSequence> adapter;
    Button submit_btn;
    Button submit_btn_sms;
    Button admin_btn;



    String firstname;
    String lastname;
    String emailid;
    String phn;
    String course;

    String result = "";
   // View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        first_name = (EditText) findViewById(R.id.fname);
        last_name = (EditText) findViewById(R.id.lname);
        email_id = (EditText) findViewById(R.id.email_addr);
        phone = (EditText) findViewById(R.id.phoneno);
        //course_st = (EditText) findViewById(R.id.course);
        admin_btn = (Button)findViewById(R.id.btn_admin);
        submit_btn = (Button) findViewById(R.id.submit_btn);   //gets from the layout
        //submit_btn_sms = (Button)findViewById(R.id.submit_btn_sms);
        spinner_s = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.courses,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_s.setAdapter(adapter);

        spinner_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = first_name.getText().toString();
                lastname = last_name.getText().toString();
                emailid = email_id.getText().toString();
                phn = phone.getText().toString();
                //course = course_st.getText().toString();
                course = spinner_s.getSelectedItem().toString();
                //validations code
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String PHONE_PATTERN = "^[987]+[0-9]{9}$";
                String NAMES = "^[a-zA-Z]+$";
                Pattern pattern1 = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher1 = pattern1.matcher(emailid);   //matcher1 for emailid
                Pattern pattern2 = Pattern.compile(PHONE_PATTERN);
                Matcher matcher2 = pattern2.matcher(phn);    //matcher2 for phone number
                Pattern pattern3 = Pattern.compile(NAMES);
                Matcher matcher4 = pattern3.matcher(firstname);
                Matcher matcher5 = pattern3.matcher(lastname);  //matcher3 for names
                if(firstname.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please enter first name",Toast.LENGTH_SHORT).show();
                    return;
                }

                    if(!matcher4.matches())
                {
                    Toast.makeText(MainActivity.this,"Please enter valid first name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(lastname.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!matcher5.matches())
                {
                    Toast.makeText(MainActivity.this,"Please enter a valid last name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if( !matcher2.matches() )
                {
                    Toast.makeText(MainActivity.this,"Please enter a valid Phone number",Toast.LENGTH_LONG).show();
                    return;
                }

                if(!matcher1.matches())
                {
                    Toast.makeText(MainActivity.this,"Please enter a valid email address",Toast.LENGTH_LONG).show();
                    return;
                }
                //validation code ends
                String method = "insert";
                BackgroundWorker backgroundWorker = new BackgroundWorker();
                backgroundWorker.execute(method,firstname, lastname, emailid, phn, course);
                Toast.makeText(MainActivity.this, "Thank you. We have mailed you the course details.", Toast.LENGTH_SHORT).show();
               // finish();
            }
        });






        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });




    }




    }
