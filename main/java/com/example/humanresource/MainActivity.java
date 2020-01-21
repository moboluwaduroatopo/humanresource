package com.example.humanresource;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText deptName;
    Button addDept, viewDept;
    HRDatabase hrD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deptName = (EditText) findViewById(R.id.deptNameText);
        addDept = (Button) findViewById(R.id.addButton);
        viewDept = (Button) findViewById(R.id.viewButton);
        hrD = new HRDatabase(this);
        addDepartment();
        viewDepartment();
        Cursor res = hrD.viewDepartment();
        String display = "";
        if(res.getCount() >  0)
        {
            while (res.moveToNext())
            {
                display += "ID "+res.getString(0);
                display += " Department Name: "+res.getString(1)+" \n";
            }
            display+=" \r";
            showMessage("Departments",display);
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText(display);

        }
        else
        {
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText("No Department yet.");

        }

    }

    public void alert(String msg)
    {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }
    public void showMessage(String title, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    public void addDepartment()
    {
        addDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = deptName.getText().toString();
                long idd = hrD.addDepartment(s);
                if( idd != 0)
                {
                    alert("Department added successfully + insertId : " + idd);
                }
                else
                {
                    alert("Failed :"+hrD.error);
                }
            }
        });
    }


    public void viewDepartment()
    {

        viewDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//                startActivity(intent);
                Cursor res = hrD.viewDepartment();
                String display = "";
                if(res.getCount() >  0)
                {
                    while (res.moveToNext())
                    {
                        display += "ID "+res.getString(0);
                        display += " Department Name: "+res.getString(1)+" \n";
                    }
                    display+=" \r";
                    showMessage("Departments",display);

                    intent.putExtra("com.example.humanresource.department",display);
                    startActivity(intent);
                }
                else
                {
                    alert("No Department yet.");
                }

            }
        });
    }
}
