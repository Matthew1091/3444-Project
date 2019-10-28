package com.example.bulbbeats;

import android.os.Parcel;
import android.os.Parcelable;

public class Bulb implements Parcelable {
    int id;

    public Bulb(int ID)
    {
        id = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    protected Bulb(Parcel in) {
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Bulb> CREATOR = new Parcelable.Creator<Bulb>() {
        @Override
        public Bulb createFromParcel(Parcel source) {
            return new Bulb(source);
        }

        @Override
        public Bulb[] newArray(int size) {
            return new Bulb[size];
        }
    };
}
