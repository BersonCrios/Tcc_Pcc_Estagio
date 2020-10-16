package br.com.berson.arlocation.Models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locais implements Serializable, Parcelable
{

    @SerializedName("locais")
    @Expose
    private List<Local> locais = null;
    public final static Parcelable.Creator<Locais> CREATOR = new Creator<Locais>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Locais createFromParcel(Parcel in) {
            return new Locais(in);
        }

        public Locais[] newArray(int size) {
            return (new Locais[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8188400186595769309L;

    protected Locais(Parcel in) {
        in.readList(this.locais, (br.com.berson.arlocation.Models.Locais.class.getClassLoader()));
    }

    public Locais() {
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(locais);
    }

    public int describeContents() {
        return 0;
    }

}