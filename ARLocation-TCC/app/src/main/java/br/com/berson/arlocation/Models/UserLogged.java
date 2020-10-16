
package br.com.berson.arlocation.Models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogged implements Serializable, Parcelable
{

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;
    public final static Creator<UserLogged> CREATOR = new Creator<UserLogged>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserLogged createFromParcel(Parcel in) {
            return new UserLogged(in);
        }

        public UserLogged[] newArray(int size) {
            return (new UserLogged[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1509883734233203664L;

    protected UserLogged(Parcel in) {
        this.user = ((User) in.readValue((User.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserLogged() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(user);
        dest.writeValue(token);
    }

    public int describeContents() {
        return  0;
    }

}
