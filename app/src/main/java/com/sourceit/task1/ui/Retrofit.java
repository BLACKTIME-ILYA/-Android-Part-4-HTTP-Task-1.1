package com.sourceit.task1.ui;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by User on 15.02.2016.
 */
public class Retrofit {

    private static final String ENDPOINT = "https://restcountries.eu/rest";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        @GET("/v1/all")
        void getCountries(Callback<List<ObjectType>> callback);

        @GET("/v1/region/{region}")
        void getRegion(@Path("region") String name, Callback<List<ObjectType>> callback);

        @GET("/v1/subregion/{subregion}")
        void getSubregion(@Path("subregion") String name, Callback<List<ObjectType>> callback);

        @GET("/v1/name/{country}")
        void getCountry(@Path("country") String name, Callback<List<ObjectType>> callback);
    }

    public static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getCountries(Callback<List<ObjectType>> callback) {
        apiInterface.getCountries(callback);
    }

    public static void getRegion(String region, Callback<List<ObjectType>> callback) {
        apiInterface.getRegion(region, callback);
    }

    public static void getSubregion(String subregion, Callback<List<ObjectType>> callback) {
        apiInterface.getSubregion(subregion, callback);
    }

    public static void getCountry(String country, Callback<List<ObjectType>> callback) {
        apiInterface.getCountry(country, callback);
    }
}
