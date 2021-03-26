package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FoodInfo extends AppCompatActivity {

    List<CheckBox> listCB;
    List<RadioButton> listRB;
    RadioGroup rgSize, rgTortilla;
    GridLayout rlFilling, rlBeverage;

    List<String> listTitle;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = "";
                for (String s : listTitle
                ) {
                    order += s;
                }
                Toast.makeText(FoodInfo.this, getInfo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getInfo() {
        String order = "Size: ";
        int i = 1, lenTitle = listTitle.size();
        for (int k = 0; k < listRB.size(); k++
        ) {
            if (k - Integer.parseInt(listTitle.get(i)) + 1 == 0) {
                order = order.substring(0, order.length() - 1);
                order += "\n" + listTitle.get(i + 1) + ": ";
                i += 2;
            }
            if (listRB.get(k).isChecked()) {
                order += listRB.get(k).getText().toString() + ",";
            }

        }
        for (int k = 0; k < listCB.size(); k++
        ) {
            if (k - Integer.parseInt(listTitle.get(i)) + 1 == 0) {
                order = order.substring(0, order.length() - 1);
                order += "\n" + listTitle.get(i + 1) + ": ";
                i += 2;
            }
            if (listCB.get(k).isChecked()) {
                order += listCB.get(k).getText().toString() + ",";
            }
        }
        order = order.substring(0, order.length() - 1);
        return order;
    }

    public void addControls() {
        listCB = new ArrayList<>();
        listRB = new ArrayList<>();

        rgSize = findViewById(R.id.rbGroupSize);
        rgTortilla = findViewById(R.id.rbGroupTortilla);
        rlBeverage = findViewById(R.id.gridBeverage);
        rlFilling = findViewById(R.id.gridFillings);

        btn = findViewById(R.id.btnPlace);
        listTitle = new ArrayList<>();
        addViewAndElement(listCB, listRB);
    }

    private void addViewAndElement(List<CheckBox> listCB, List<RadioButton> listRB) {

//        Add View of size
        String[] str = getResources().getStringArray(R.array.ArrSize);
        addViewRadioButton(str, rgSize, listRB);

//        Add View of Tortilla
        str = getResources().getStringArray(R.array.ArrTortilla);
        addViewRadioButton(str, rgTortilla, listRB);

//        Add View of Fillings
        str = getResources().getStringArray(R.array.ArrFillings);
        addViewCheckBox(str, rlFilling, listCB, 3);

//        Add View of Beverage
        str = getResources().getStringArray(R.array.ArrBeverage);
        addViewCheckBox(str, rlBeverage, listCB, 2);

    }

    private void addViewCheckBox(String[] str, GridLayout grid, List<CheckBox> listCB, int columnSpec) {
        addListTitle(str);
        int k = 0, j = 0;
        for (int i = 1; i < str.length; i++
        ) {
            CheckBox cb = new CheckBox(this);
            GridLayout.LayoutParams layoutParamsCb = new GridLayout.LayoutParams();
            layoutParamsCb.rowSpec = GridLayout.spec(j);
            layoutParamsCb.columnSpec = GridLayout.spec(k++);
            cb.setLayoutParams(layoutParamsCb);
            cb.setPadding(10, 0, 0, 0);
            cb.setText(str[i]);
            cb.setTextSize(18);
            grid.addView(cb);

            listCB.add(cb);
            if (k == columnSpec) {
                j++;
                k = 0;
            }
        }
    }

    private void addListTitle(String[] str) {
        listTitle.add(str[0]);
        listTitle.add(str.length + "");


    }

    private void addViewRadioButton(String[] str, RadioGroup rgSize, List<RadioButton> listRB) {
        addListTitle(str);

        for (int i = 1; i < str.length; i++
        ) {
            RadioButton rb = new RadioButton(this);
            LinearLayout.LayoutParams layoutParamsCb = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rb.setLayoutParams(layoutParamsCb);
            rb.setPadding(20, 0, 0, 0);
            rb.setText(str[i]);
            rb.setTextSize(18);
            rgSize.addView(rb);
            listRB.add(rb);
        }
    }
}