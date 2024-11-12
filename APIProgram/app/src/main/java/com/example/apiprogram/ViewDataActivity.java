package com.example.apiprogram;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;  // Corrected import statement

public class ViewDataActivity extends AppCompatActivity {

    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);  // Ensure you have the correct layout file

        textViewData = findViewById(R.id.textViewData);

        // Get data from intent
        String data = getIntent().getStringExtra("data");
        textViewData.setText(data);
    }
}