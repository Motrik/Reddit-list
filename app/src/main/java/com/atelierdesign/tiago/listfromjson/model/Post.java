package com.atelierdesign.tiago.listfromjson.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tiago on 09-03-2015.
 */
public class Post {

    @SerializedName("permalink")
    private String permalink;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("title")
    private String title;

    public Post(String permalink, String thumbnail, String title) {
        this.permalink = permalink;

        this.thumbnail = thumbnail;


        this.title = title;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}
