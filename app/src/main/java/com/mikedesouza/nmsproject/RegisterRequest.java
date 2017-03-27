package com.mikedesouza.nmsproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mike on 24/03/2017.
 */

public class RegisterRequest extends StringRequest {

    private static String registerRequestURL = "http://mikedesouza.000webhostapp.com/register.php";
    private Map<String, String> params;

    //constructor
    public RegisterRequest (String firstName, String lastName, String username,
                            String password, String email, Response.Listener<String> listener) {
        super (Method.POST, registerRequestURL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
    }

    public Map<String, String> getParams() {
        return params;
    }
}
