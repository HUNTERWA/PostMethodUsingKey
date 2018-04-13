package com.example.rohit.postquestioninsert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    EditText editText;
    Button button;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    static final String apiAdd="http://192.168.0.110:9000/question/insert\n";
    static final String authKey="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YWFhMjc4MWZlMTAzNDdmMWZlYzEyNDAiLCJhZG1pbklkIjoiamF5IiwicGFzc3dvcmQiOiJqYXkxMjMiLCJpYXQiOjE1MjM2MTE5Njd9.5-RMgiiQ_HceDYxVoVDXM0WKefUgvD4QBxz5HVBAv48";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);
        requestQueue= Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hitToPost();
            }
        });
    }

    private void hitToPost()
    {
        final String question=editText.getText().toString().trim();

        stringRequest=new StringRequest(Request.Method.POST, apiAdd, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getApplicationContext(),"Posting done",Toast.LENGTH_LONG).show();
                Log.d("Response is",""+response);
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                        Log.d("Cause of error is",""+error);
                    }
                })
        {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> map=new HashMap<String,String>();
                map.put("question",question);
                return map;
            }

            @Override
            public Map<String,String>getHeaders()
            {
                HashMap<String,String> hashMap=new HashMap<String, String>();
                hashMap.put("authorization",authKey);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }
}
