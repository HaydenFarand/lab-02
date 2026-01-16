package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int selectedCityIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);




        Button addButton = findViewById(R.id.add_button);
        EditText editTextMain = findViewById(R.id.editText_main);
        Button deleteButton = findViewById(R.id.delete_button);
        Button confirmButton = findViewById(R.id.confirm_button);
        editTextMain.setVisibility(View.INVISIBLE);
        confirmButton.setVisibility(View.INVISIBLE);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic for when add button pressed
                editTextMain.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic for when delete button pressed
                if (selectedCityIndex >= 0) {
                    dataList.remove(selectedCityIndex);
                    cityAdapter.notifyDataSetChanged();
                    selectedCityIndex = -1;
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic for when confirm button pressed
                String userInput = editTextMain.getText().toString();
                if (!userInput.isEmpty()) {
                    dataList.add(userInput);
                    cityAdapter.notifyDataSetChanged();
                    editTextMain.setVisibility(View.INVISIBLE);
                    confirmButton.setVisibility(View.INVISIBLE);
                    editTextMain.setText("");
                }

            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //logic for when item in list is clicked
                selectedCityIndex = position;
            }
        });


    }
}