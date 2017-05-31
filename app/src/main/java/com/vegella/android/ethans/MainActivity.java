package com.vegella.android.ethans;


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

        submit_btn = (Button) findViewById(R.id.submit_btn);   //gets from the layout

        spinner_s = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.courses,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_s.setAdapter(adapter);

        spinner_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",Toast.LENGTH_LONG).show();
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
                BackgroundWorker backgroundWorker = new BackgroundWorker();
                backgroundWorker.execute(firstname, lastname, emailid, phn, course);
                Toast.makeText(MainActivity.this, "Thank you. We will revert to you soon.", Toast.LENGTH_SHORT).show();
               // finish();

            }
        });








    }




    }
