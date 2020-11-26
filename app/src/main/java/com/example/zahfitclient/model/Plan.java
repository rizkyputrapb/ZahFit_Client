package com.example.zahfitclient.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {
    String plan_name, level_id, type_id, personal_trainer_id, uri;

    protected Plan(Parcel in) {
        plan_name = in.readString();
        level_id = in.readString();
        type_id = in.readString();
        personal_trainer_id = in.readString();
        uri = in.readString();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Plan() {
    }

    public Plan(String plan_name, String level_id, String type_id, String personal_trainer_id, String uri) {
        this.plan_name = plan_name;
        this.level_id = level_id;
        this.type_id = type_id;
        this.personal_trainer_id = personal_trainer_id;
        this.uri = uri;
    }

    public Plan(String plan_name, String level_id, String type_id, String personal_trainer_id) {
        this.plan_name = plan_name;
        this.level_id = level_id;
        this.type_id = type_id;
        this.personal_trainer_id = personal_trainer_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPersonal_trainer_id() {
        return personal_trainer_id;
    }

    public void setPersonal_trainer_id(String personal_trainer_id) {
        this.personal_trainer_id = personal_trainer_id;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(plan_name);
        parcel.writeString(level_id);
        parcel.writeString(type_id);
        parcel.writeString(personal_trainer_id);
        parcel.writeString(uri);
    }
}
