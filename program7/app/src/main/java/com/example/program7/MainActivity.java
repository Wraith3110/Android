package com.example.program7;

import android.os.Bundle;
import android.os.AsyncTask; // Correct import
import android.text.TextUtils; // Correct import for TextUtils
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        tv = (TextView) findViewById(R.id.text_asyn);
        start = (Button) findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);

        AsyncClass asyncClass = new AsyncClass(); // Create an instance of AsyncTask

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute AsyncTask when "Start Task" button is clicked
                asyncClass.doInBackground();
                asyncClass.onProgressUpdate();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stop marquee and display completion message
                tv.setSelected(false);
                asyncClass.onPostExecute("AsyncTask Completed"); // Pass the completion message
            }
        });
    }

    // Inner class for AsyncTask
    private class AsyncClass extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Task Starting", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            // Configure TextView for marquee effect
            tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tv.setSelected(true);
            tv.setVisibility(View.VISIBLE);
            return "Task is running"; // Message to pass to onPostExecute
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), "Banner is moving", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Hide TextView and display completion message
            tv.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}
