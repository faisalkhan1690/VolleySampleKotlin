package com.example.faisalkhan.vollysample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMethodSelection = (ListView) findViewById(R.id.lv_method_selection);
        List<String> dataList=new ArrayList<String>();

        dataList.add("Default Request Method Sample");
        dataList.add("Custom Request Method Sample");

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);

        lvMethodSelection.setAdapter(adapter);

        lvMethodSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        startActivity(new Intent(MainActivity.this,DefaultMethodsActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(MainActivity.this,CustomMethodsActivity.class));
                        break;

                    default:
                        startActivity(new Intent(MainActivity.this,DefaultMethodsActivity.class));

                }
            }
        });

    }
}
