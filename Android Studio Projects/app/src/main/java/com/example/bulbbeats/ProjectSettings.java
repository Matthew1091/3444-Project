package com.example.bulbbeats;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.philips.lighting.hue.sdk.PHHueSDK;

import java.util.List;

public class ProjectSettings implements Parcelable {
    //Currently just contains a song Uri but as we add more settings we expand these functions.
    Uri songUri;
    List<Bulb> bulbs;
    PHHueSDK phHueSDK;
    static final int MAX_HUE=65535;

    //Constructor.
    public ProjectSettings(Uri songuri, List<Bulb> bs) {
        songUri = songuri;
        bulbs = bs;
        phHueSDK = null;
    }

    public ProjectSettings(List<Bulb> bs) {
        bulbs = bs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.songUri, flags);
        dest.writeTypedList(this.bulbs);
    }

    protected ProjectSettings(Parcel in) {
        this.songUri = in.readParcelable(Uri.class.getClassLoader());
        this.bulbs = in.createTypedArrayList(Bulb.CREATOR);
    }

    public static final Creator<ProjectSettings> CREATOR = new Creator<ProjectSettings>() {
        @Override
        public ProjectSettings createFromParcel(Parcel source) {
            return new ProjectSettings(source);
        }

        @Override
        public ProjectSettings[] newArray(int size) {
            return new ProjectSettings[size];
        }
    };
}