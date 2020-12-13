package com.example.zahfitclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    String email, name, username, img;
    int age, height, weight;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String username, String name, int age, int height, int weight) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public User(String email, String username, String name, int age, int height, int weight, String img) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.age = age;
        this.img = img;
        this.height = height;
        this.weight = weight;
    }

    protected User(Parcel in) {
        email = in.readString();
        name = in.readString();
        username = in.readString();
        img = in.readString();
        age = in.readInt();
        height = in.readInt();
        weight = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(img);
        parcel.writeInt(age);
        parcel.writeInt(height);
        parcel.writeInt(weight);
    }
}
