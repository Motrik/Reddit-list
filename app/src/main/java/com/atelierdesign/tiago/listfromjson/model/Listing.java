package com.atelierdesign.tiago.listfromjson.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 10-03-2015.
 */
public class Listing {

    @SerializedName("data")
    private ChildrenArray childrenArray;

    public List<Post> getPostList() {
        List<Post> postlist = new ArrayList<Post>();

        for (Children children : childrenArray.getChildrenList()) {
            postlist.add(children.getPost());

        }
        return postlist;
    }
}
