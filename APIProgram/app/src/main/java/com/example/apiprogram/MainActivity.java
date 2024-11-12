package com.example.apiprogram;

import android.content.Intent;  // Added import for Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextClass, editTextId;
    private Button buttonInsert, buttonRead, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextClass = findViewById(R.id.editTextClass);
        editTextId = findViewById(R.id.editTextId);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonRead = findViewById(R.id.buttonRead);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    private void insertData() {
        String url = "http://192.168.76.164/api/insert.php";
        String name = editTextName.getText().toString().trim();
        String className = editTextClass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("class", className);
                return params;
            }
        };

        NetworkHelper.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void readData() {
        String url = "http://192.168.76.164/api/read.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("Id");  // Use the correct key as per your JSON
                                String name = jsonObject.getString("Name");
                                String className = jsonObject.getString("Class");
                                sb.append("ID: ").append(id).append(", Name: ").append(name).append(", Class: ").append(className).append("\n");
                            }

                            // Launch ViewDataActivity with the data
                            Intent intent = new Intent(MainActivity.this, ViewDataActivity.class);
                            intent.putExtra("data", sb.toString());
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON data.", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        NetworkHelper.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void updateData() {
        String url = "http://192.168.76.164/api/update.php";
        String id = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String className = editTextClass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("class", className);
                return params;
            }
        };

        NetworkHelper.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void deleteData() {
        String url = "http://192.168.76.164/api/delete.php";
        String id = editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        NetworkHelper.getInstance(this).addToRequestQueue(stringRequest);
    }
}
