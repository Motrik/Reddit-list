package com.atelierdesign.tiago.listfromjson.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tiago on 10-03-2015.
 */
public class Children {

    @SerializedName("data")
    private Post post;

    public Post getPost() {
        return post;
    }
}
