package com.example.zahfitclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Workout implements Parcelable {
    String workout_name, workout_id, count, time;

    public Workout() {
    }

    public Workout(String workout_name) {
        this.workout_name = workout_name;
    }

    public Workout(String workout_name, String workout_id) {
        this.workout_name = workout_name;
        this.workout_id = workout_id;
    }

    protected Workout(Parcel in) {
        workout_name = in.readString();
        workout_id = in.readString();
        count = in.readString();
        time = in.readString();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(String workout_id) {
        this.workout_id = workout_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(workout_name);
        parcel.writeString(workout_id);
        parcel.writeString(count);
        parcel.writeString(time);
    }
}
