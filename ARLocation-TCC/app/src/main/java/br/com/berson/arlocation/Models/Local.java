package br.com.berson.arlocation.Models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Local implements Serializable, Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("categoria")
    @Expose
    private Categoria categoria;
    @SerializedName("imagePath1")
    @Expose
    private String imagePath1;
    @SerializedName("imagePath2")
    @Expose
    private String imagePath2;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public final static Parcelable.Creator<Local> CREATOR = new Creator<Local>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Local createFromParcel(Parcel in) {
            return new Local(in);
        }

        public Local[] newArray(int size) {
            return (new Local[size]);
        }

    };
    private final static long serialVersionUID = -1698941939391072858L;

    protected Local(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((Integer.class.getClassLoader())));
        this.longitude = ((String) in.readValue((Integer.class.getClassLoader())));
        this.nome = ((String) in.readValue((String.class.getClassLoader())));
        this.categoria = ((Categoria) in.readValue((Categoria.class.getClassLoader())));
        this.imagePath1 = ((String) in.readValue((String.class.getClassLoader())));
        this.imagePath2 = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Local() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(nome);
        dest.writeValue(categoria);
        dest.writeValue(imagePath1);
        dest.writeValue(imagePath2);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}
