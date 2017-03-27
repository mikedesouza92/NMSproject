package com.mikedesouza.nmsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText txtWelcome = (EditText) findViewById(R.id.txtWelcome);
        final EditText txtUserAreaUsername = (EditText) findViewById(R.id.txtUserAreaUsername);
    }
}
