package com.vegella.android.ethans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shushanth on 6/6/17.
 */

public class DisplayListView extends AppCompatActivity{

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view);


        listView = (ListView)findViewById(R.id.list_view);
        contactAdapter = new ContactAdapter(DisplayListView.this,R.layout.row_layout);

        listView.setAdapter(contactAdapter);



        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count=0;
            String name,email,mobile;
            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                name = jsonObject.getString("fname");
                email = jsonObject.getString("email");
                mobile = jsonObject.getString("mobile");
                Contacts contacts = new Contacts(name,email,mobile);
                contactAdapter.add(contacts);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
