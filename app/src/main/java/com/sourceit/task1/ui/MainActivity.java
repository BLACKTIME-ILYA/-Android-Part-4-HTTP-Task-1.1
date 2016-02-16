package com.sourceit.task1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sourceit.task1.R;
import com.sourceit.task1.utils.L;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private Intent intent;


    private final String REGIONS = "regions";
    private final String SUBREGIONS = "subregions";
    private final String COUNTRIES = "countries";
    public static final String INFO = "info";

    private RecyclerView list;
    private EditText filter;
    private List<ObjectType> localObjectsType;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());

    private ArrayList<String> currentObjects;
    private LinkedList<String> tempCurrentObjects;
    private String currentList;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, Main2Activity.class);
        filter = (EditText) findViewById(R.id.edit_filter);
        list = (RecyclerView) findViewById(R.id.recycler_list);
        currentObjects = new ArrayList<>();
        tempCurrentObjects = new LinkedList<>();

        list.setLayoutManager(layoutManager);

        levelsWithRetrofit();
    }

    private void levelsWithRetrofit() {
        if (currentList == null) {
            currentList = REGIONS;
            Retrofit.getCountries(new Callback<List<ObjectType>>() {
                @Override
                public void success(List<ObjectType> countries, Response response) {
                    localObjectsType = countries;
                    getObjects();

                    list.setAdapter(new MyRecyclerAdapter(tempCurrentObjects, new OnItemClickWatcher<String>() {
                        @Override
                        public void onItemClick(View v, int position, String item) {
                            L.d("click " + item);
                            selectedItem = item.toLowerCase();
                            levelsWithRetrofit();
                        }
                    }));

                    filter.addTextChangedListener(new MyTextWatcher() {
                        @Override
                        public void afterTextChanged(Editable s) {
                            try {
                                L.d("try");
                                for (Iterator<String> it = currentObjects.iterator(); it.hasNext(); ) {
                                    String tempValue = it.next();
                                    L.d("temp value " + tempValue);
                                    if (tempValue.toLowerCase().contains(filter.getText().toString().toLowerCase())) {
                                        if (!tempCurrentObjects.contains(tempValue)) {
                                            tempCurrentObjects.add(tempValue);
                                        }
                                    } else {
                                        if (tempCurrentObjects.contains(tempValue)) {
                                            tempCurrentObjects.remove(tempValue);
                                        }
                                    }
                                }
                                list.getAdapter().notifyDataSetChanged();
                            } catch (NumberFormatException e) {
                            }
                        }
                    });
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (currentList.equals(REGIONS)) {
            L.d("mygetregions");
            currentList = SUBREGIONS;
            Retrofit.getRegion(selectedItem, new Callback<List<ObjectType>>() {
                @Override
                public void success(List<ObjectType> region, Response response) {
                    localObjectsType = region;
                    currentObjects.clear();
                    tempCurrentObjects.clear();
                    getObjects();
                    list.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (currentList.equals(SUBREGIONS)) {
            L.d("mygetsubregions");
            currentList = COUNTRIES;
            Retrofit.getSubregion(selectedItem, new Callback<List<ObjectType>>() {
                @Override
                public void success(List<ObjectType> objectTypes, Response response) {
                    localObjectsType = objectTypes;
                    currentObjects.clear();
                    getObjects();
                    list.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
                }

            });

        } else if (currentList.equals(COUNTRIES)) {
            L.d("mygetcountry");
            Retrofit.getCountry(selectedItem, new Callback<List<ObjectType>>() {
                @Override
                public void success(List<ObjectType> objectTypes, Response response) {
                    intent.putExtra(INFO, objectTypes.get(0));
                    startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getObjects() {

        for (ObjectType objectType : localObjectsType) {
            if (!currentObjects.isEmpty()) {
                if (currentList.equals(REGIONS)) {
                    if (!currentObjects.contains(objectType.getRegion())) {
                        currentObjects.add(objectType.getRegion());
                        tempCurrentObjects.add(objectType.getRegion());
                    }

                } else if (currentList.equals(SUBREGIONS)) {
                    if (!currentObjects.contains(objectType.getSubregion())) {
                        currentObjects.add(objectType.getSubregion());
                        tempCurrentObjects.add(objectType.getSubregion());
                    }
                } else if (currentList.equals(COUNTRIES)) {
                    if (!currentObjects.contains(objectType.getName())) {
                        currentObjects.add(objectType.getName());
                        tempCurrentObjects.add(objectType.getName());
                    }
                }
            } else {
                if (currentList.equals(REGIONS)) {
                    currentObjects.add(objectType.getRegion());
                    tempCurrentObjects.add(objectType.getRegion());
                } else if (currentList.equals(SUBREGIONS)) {
                    currentObjects.add(objectType.getSubregion());
                    tempCurrentObjects.add(objectType.getSubregion());
                } else if (currentList.equals(COUNTRIES)) {
                    currentObjects.add(objectType.getName());
                    tempCurrentObjects.add(objectType.getName());
                }
            }
        }
    }
}
