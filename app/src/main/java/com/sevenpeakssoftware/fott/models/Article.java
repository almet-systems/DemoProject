package com.sevenpeakssoftware.fott.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by razir on 1/3/2017.
 */

public class Article implements Parcelable {

    @SerializedName("id")
    Long id;

    @SerializedName("title")
    String title;

    @SerializedName("dateTime")
    DateTime dateTime;

    @SerializedName("tags")
    ArrayList<String> tags;

    @SerializedName("ingress")
    String ingress;

    @SerializedName("image")
    String image;

    @SerializedName("created")
    Long created;

    @SerializedName("changed")
    Long changed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getTags() {
        return tags;
    }


    public String getIngress() {
        return ingress;
    }

    public void setIngress(String ingress) {
        this.ingress = ingress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getChanged() {
        return changed;
    }

    public void setChanged(Long changed) {
        this.changed = changed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeSerializable(this.dateTime);
        dest.writeStringList(this.tags);
        dest.writeString(this.ingress);
        dest.writeString(this.image);
        dest.writeValue(this.created);
        dest.writeValue(this.changed);
    }

    public Article() {
    }

    protected Article(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.dateTime = (DateTime) in.readSerializable();
        this.tags = in.createStringArrayList();
        this.ingress = in.readString();
        this.image = in.readString();
        this.created = (Long) in.readValue(Long.class.getClassLoader());
        this.changed = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
