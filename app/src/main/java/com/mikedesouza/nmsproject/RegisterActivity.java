package com.mikedesouza.nmsproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText txtLastName = (EditText) findViewById(R.id.txtLastName);
        final EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        final EditText txtRePassword = (EditText) findViewById(R.id.txtRePassword);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        final Button bRegister = (Button) findViewById(R.id.btnRegister);
        bRegister.setBackgroundColor(Color.RED);

        //create spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.account_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = txtFirstName.getText().toString();
                final String lastName = txtLastName.getText().toString();
                final String username = txtUsername.getText().toString();
                final String password = txtPassword.getText().toString();
                final String rePassword = txtRePassword.getText().toString();
                final String email = txtEmail.getText().toString();

                //button click test
                bRegister.setBackgroundColor(Color.GREEN);

                //create listener for request
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        //button click test
                        bRegister.setBackgroundColor(Color.BLUE);

                        //convert JSON String to JSON object
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            //Check success (true or false)
                            boolean success = jsonResponse.getBoolean("success");

                            //if registration was successful
                            if (success){
                                //send user back to login page
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                bRegister.setBackgroundColor(Color.BLACK);
                            }else
                            {
                                //alert user of error
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register failed - JSON Exception caught")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }

                    }
                };

                //create register request
                RegisterRequest regRequest = new RegisterRequest(firstName, lastName, username,
                        password, email, responseListener);
                //Create register queue using Volley
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                //add register request to queue
                queue.add(regRequest);

            }
        });
    }
}
