package com.example.humanresource;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(getIntent().hasExtra("com.example.humanresource.department")){
            String str = getIntent().getExtras().getString("com.example.humanresource.department");

            TextView t = (TextView) findViewById(R.id.textView);
            t.setText(str);
        }
    }
}
