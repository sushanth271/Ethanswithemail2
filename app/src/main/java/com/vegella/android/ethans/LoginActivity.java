package com.vegella.android.ethans;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shushanth on 9/6/17.
 */

public class LoginActivity extends Activity {
    EditText username;
    EditText password;
    Button login_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login_btn = (Button)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if(uname.equals("ethansadmin") && pass.equals("ethansadmin999"))
                {
                    Intent intent = new Intent(LoginActivity.this,AdminPage.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
