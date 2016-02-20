package com.sourceit.task1.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ObjectType implements Parcelable {
    private String name;
    private String region;
    private String subregion;
    private String capital;
    private String population;
    private String area;

    ArrayList<String> languages;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.region);
        dest.writeString(this.subregion);
        dest.writeString(this.capital);
        dest.writeString(this.population);
        dest.writeString(this.area);
        dest.writeSerializable(this.languages);
    }

    public ObjectType() {
    }

    private ObjectType(Parcel in) {
        this.name = in.readString();
        this.region = in.readString();
        this.subregion = in.readString();
        this.capital = in.readString();
        this.population = in.readString();
        this.area = in.readString();
        this.languages = (ArrayList<String>) in.readSerializable();
    }

    public static final Creator<ObjectType> CREATOR = new Creator<ObjectType>() {
        public ObjectType createFromParcel(Parcel source) {
            return new ObjectType(source);
        }

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

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public static Creator<ObjectType> getCREATOR() {
        return CREATOR;
    }
}
