package com.coolweather.android.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by zyq on 2018/5/5.
 */

public class User extends DataSupport implements Serializable {
    private int id;
    private String username;
    private String password;
    private String apiKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", api_key='" + apiKey + '\'' +
                '}';
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String api_key) {
        this.apiKey = api_key;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(id);
//        parcel.writeString(username);
//        parcel.writeString(password);
//        parcel.writeString(api_key);
//    }
//
//    public static final Parcelable.Creator<User> CREATOR=new Parcelable.Creator<User>(){
//        @Override
//        public User createFromParcel(Parcel parcel) {
//            User user = new User();
//            user.id = parcel.readInt();
//            user.username = parcel.readString();
//            user.password = parcel.readString();
//            user.api_key = parcel.readString();
//            return user;
//        }
//
//        @Override
//        public User[] newArray(int i) {
//            return new User[i];
//        }
//    };
}
