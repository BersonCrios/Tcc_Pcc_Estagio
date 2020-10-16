
package br.com.berson.arlocation.Models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Serializable, Parcelable
{

    @SerializedName("aplicacao")
    @Expose
    private List<Object> aplicacao = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public final static Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
            "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8229253498472144982L;

    protected User(Parcel in) {
        in.readList(this.aplicacao, (Object.class.getClassLoader()));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public User() {
    }

    public List<Object> getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(List<Object> aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(aplicacao);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(username);
        dest.writeValue(email);
        dest.writeValue(created);
        dest.writeValue(v);
    }

    public int describeContents() {
        return  0;
    }

}
