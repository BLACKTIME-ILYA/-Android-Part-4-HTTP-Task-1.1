package com.sourceit.task1.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class ObjectType implements Parcelable {
    private String name;
    private String region;
    private String subregion;
    private String capital;
    private String population;
    private String area;


    protected ObjectType(Parcel in) {
        name = in.readString();
        region = in.readString();
        subregion = in.readString();
        capital = in.readString();
        population = in.readString();
        area = in.readString();
    }

    public static final Creator<ObjectType> CREATOR = new Creator<ObjectType>() {
        @Override
        public ObjectType createFromParcel(Parcel in) {
            return new ObjectType(in);
        }

        @Override
        public ObjectType[] newArray(int size) {
            return new ObjectType[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(region);
        dest.writeString(subregion);
        dest.writeString(capital);
        dest.writeString(population);
        dest.writeString(area);
    }
}
