package com.sourceit.task1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sourceit.task1.R;
import com.sourceit.task1.utils.L;

public class Main2Activity extends AppCompatActivity {

    private TextView name;
    private TextView region;
    private TextView subregion;
    private TextView capital;
    private TextView population;
    private TextView area;

    private final String NAME = "name: ";
    private final String REGION = "region: ";
    private final String SUBREGION = "subregion: ";
    private final String CAPITAL = "capital: ";
    private final String POPULATION = "population: ";
    private final String AREA = "area: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        L.d("start activity");

        init();

        Intent intent = getIntent();
        ObjectType objectType = intent.getParcelableExtra(MainActivity.INFO);
        name.setText(NAME + objectType.getName());
        region.setText(REGION + objectType.getRegion());
        subregion.setText(SUBREGION + objectType.getSubregion());
        capital.setText(CAPITAL + objectType.getCapital());
        population.setText(POPULATION + objectType.getPopulation());
        area.setText(AREA + objectType.getArea());
    }

    private void init() {
        name = (TextView) findViewById(R.id.info_name);
        region = (TextView) findViewById(R.id.info_region);
        subregion = (TextView) findViewById(R.id.info_subregion);
        capital = (TextView) findViewById(R.id.info_capital);
        population = (TextView) findViewById(R.id.info_population);
        area = (TextView) findViewById(R.id.info_area);
    }
}
