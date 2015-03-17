package com.atelierdesign.tiago.listfromjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.atelierdesign.tiago.listfromjson.model.Post;

import java.util.List;

/**
 * Created by Tiago on 10-03-2015.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {

    List<Post> mPostList;
    Context mContext;
    ItemClick myListner;

    public RedditAdapter(List<Post> postList, Context context, ItemClick mListner) {
        mPostList = postList;
        mContext = context;
        myListner = mListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Post currentPost = mPostList.get(i);
        holder.mTextViewPost.setText(currentPost.getTitle());


       // URLUtil.isValidUrl(currentPost.getThumbnail())
        if ( URLUtil.isValidUrl(currentPost.getThumbnail()) && !TextUtils.isEmpty(currentPost.getThumbnail())) {
            holder.mPostImage.setImageUrl(currentPost.getThumbnail(), ConnectionManager.getsImageLoader(mContext));
        } else {
            holder.mPostImage.setVisibility(View.GONE);
           // holder.mPostImage.setImageUrl("http://droidfruit.com/wp-content/uploads/2014/12/Android.jpg", ConnectionManager.getsImageLoader(mContext));
        }


    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    // as vistas nao têm de ser recarregadas , apenas se substituem os elementos (conceito novo no lollipop)


    //esta class é o ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextViewPost;
        public NetworkImageView mPostImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewPost = (TextView) itemView.findViewById(R.id.rowTextViewName);
            mPostImage = (NetworkImageView) itemView.findViewById(R.id.rowImageView);
            itemView.setOnClickListener(this); // aqui é o triger do click
        }

        @Override  // é aqui que está o segredo
        public void onClick(View v) {
            myListner.OnItemClicked(mPostList.get(getPosition()));
        }
    }


    //interface para activar os eventos, neste caso é o OnItemClicked()
    public static interface ItemClick {
        public void OnItemClicked(Post postClicked);
    }
}
