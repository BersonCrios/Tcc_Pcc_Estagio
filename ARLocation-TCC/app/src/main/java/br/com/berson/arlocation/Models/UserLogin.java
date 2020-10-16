package br.com.berson.arlocation.Models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin implements Serializable, Parcelable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    public final static Parcelable.Creator<UserLogin> CREATOR = new Creator<UserLogin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserLogin createFromParcel(Parcel in) {
            return new UserLogin(in);
        }

        public UserLogin[] newArray(int size) {
            return (new UserLogin[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4654662625065154533L;

    protected UserLogin(Parcel in) {
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLogin() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(password);
    }

    public int describeContents() {
        return 0;
    }

}