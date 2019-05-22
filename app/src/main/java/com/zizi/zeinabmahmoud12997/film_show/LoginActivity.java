package com.zizi.zeinabmahmoud12997.film_show;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView txt;
    private EditText email, password;
    private ProgressBar load;
    private Button bu_login;
    private static String URL_LOGIN = "http://192.168.43.233/android_register_login/mylogin.php";
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        txt = (TextView) findViewById(R.id.reg_Page);
        email = (EditText)findViewById(R.id.em);
        password = (EditText)findViewById(R.id.pas);
        load = findViewById(R.id.load);
        bu_login = (Button)findViewById(R.id.login);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ema = email.getText().toString().trim();
                String pasw = password.getText().toString().trim();

                if(!ema.isEmpty() || !pasw.isEmpty()){
                    Login(ema, pasw);
                }
                else {
                    email.setError("Please insert email!");
                    password.setError("Please insert password!");
                }
            }
        });
    }

    private void Login(final String email, final String password){

        load.setVisibility(View.VISIBLE);
        bu_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if(success.equals("1")){

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String name = object.getString("name").trim();
                            String email = object.getString("email").trim();

                            sessionManager.CreateSession(name, email);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("email", email);
                            startActivity(intent);

                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_LONG).show();
                            load.setVisibility(View.GONE);
                            bu_login.setVisibility(View.VISIBLE);
                        }
                    }

                    else {
                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
                        load.setVisibility(View.GONE);
                        bu_login.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error! " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error! " + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
