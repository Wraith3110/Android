package com.example.med;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Switch modeSwitch;
    private Button insertButton, fetchButton;
    private LinearLayout medicineNameLayout;
    private EditText medicineNameInput, dateInput;
    private Spinner timeOfDaySpinner;
    private DataBaseConn db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeSwitch = findViewById(R.id.switch1);
        insertButton = findViewById(R.id.insert);
        fetchButton = findViewById(R.id.fetch);
        medicineNameLayout = findViewById(R.id.medicine_name_layout);
        medicineNameInput = findViewById(R.id.editTextText);
        dateInput = findViewById(R.id.editTextDate);
        timeOfDaySpinner = findViewById(R.id.spinner);

        db = new DataBaseConn(this);

        setupSwitchListener();
        setupInsertButton();
        setupFetchButton();
        setupDatePicker(); // Add DatePicker functionality here
    }

    private void setupSwitchListener() {
        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fetchButton.setVisibility(View.VISIBLE);
                insertButton.setVisibility(View.GONE);
                medicineNameLayout.setVisibility(View.GONE);
                medicineNameInput.setVisibility(View.GONE);
            } else {
                fetchButton.setVisibility(View.GONE);
                insertButton.setVisibility(View.VISIBLE);
                medicineNameLayout.setVisibility(View.VISIBLE);
                medicineNameInput.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupInsertButton() {
        insertButton.setOnClickListener(v -> {
            String name = medicineNameInput.getText().toString();
            String date = dateInput.getText().toString();
            String timeOfDay = timeOfDaySpinner.getSelectedItem().toString();

            if (name.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = db.insertData(name, date, timeOfDay);
                if (result) {
                    Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupFetchButton() {
        fetchButton.setOnClickListener(v -> {
            String date = dateInput.getText().toString();
            String timeOfDay = timeOfDaySpinner.getSelectedItem().toString();

            if (date.isEmpty()) {
                Toast.makeText(this, "Please enter a date", Toast.LENGTH_SHORT).show();
            } else {
                Cursor cursor = db.fetchData(date, timeOfDay);
                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder result = new StringBuilder();
                    while (cursor.moveToNext()) {
                        result.append("Name: ").append(cursor.getString(1)).append("\n");
                    }
                    Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setupDatePicker() {
        dateInput.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Set the selected date on the EditText
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateInput.setText(date);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
}
