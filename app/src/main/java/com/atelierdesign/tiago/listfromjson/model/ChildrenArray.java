package com.atelierdesign.tiago.listfromjson.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tiago on 10-03-2015.
 */
public class ChildrenArray {

    @SerializedName("children")
    private List<Children> childrenList;

    public List<Children> getChildrenList() {
        return childrenList;
    }


}
