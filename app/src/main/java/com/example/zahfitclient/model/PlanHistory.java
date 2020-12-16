package com.example.zahfitclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlanHistory implements Parcelable {
    String date, plan_key, plan_name, level_name, type_name, personal_trainer_id, uri;

    public PlanHistory() {
    }

    public PlanHistory(String date, String plan_key, String plan_name, String level_name, String type_name, String personal_trainer_id, String uri) {
        this.date = date;
        this.plan_key = plan_key;
        this.plan_name = plan_name;
        this.level_name = level_name;
        this.type_name = type_name;
        this.personal_trainer_id = personal_trainer_id;
        this.uri = uri;
    }

    protected PlanHistory(Parcel in) {
        date = in.readString();
        plan_key = in.readString();
        plan_name = in.readString();
        level_name = in.readString();
        type_name = in.readString();
        personal_trainer_id = in.readString();
        uri = in.readString();
    }

    public static final Creator<PlanHistory> CREATOR = new Creator<PlanHistory>() {
        @Override
        public PlanHistory createFromParcel(Parcel in) {
            return new PlanHistory(in);
        }

        @Override
        public PlanHistory[] newArray(int size) {
            return new PlanHistory[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlan_key() {
        return plan_key;
    }

    public void setPlan_key(String plan_key) {
        this.plan_key = plan_key;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getPersonal_trainer_id() {
        return personal_trainer_id;
    }

    public void setPersonal_trainer_id(String personal_trainer_id) {
        this.personal_trainer_id = personal_trainer_id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(plan_key);
        parcel.writeString(plan_name);
        parcel.writeString(level_name);
        parcel.writeString(type_name);
        parcel.writeString(personal_trainer_id);
        parcel.writeString(uri);
    }
}
