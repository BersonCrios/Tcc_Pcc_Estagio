package br.com.berson.arlocation.Models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categoria implements Serializable, Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public final static Parcelable.Creator<Categoria> CREATOR = new Creator<Categoria>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        public Categoria[] newArray(int size) {
            return (new Categoria[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3606734931665082767L;

    protected Categoria(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.nome = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Categoria() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nome);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}