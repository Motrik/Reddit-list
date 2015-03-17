package com.atelierdesign.tiago.listfromjson;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Tiago on 09-03-2015.
 * ESTA É UMA SINGLETON CLASS
 */
public class ConnectionManager {

    //static significa que faz parte da classe e pode não fazer parte do objecto.
    //Assim é possivel chamar estas variaveis e metodos directamente.  UAU
    private static RequestQueue queue;

    //isto so p a network image
    private static ImageLoader sImageLoader;



    public static RequestQueue getInstance(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        return queue;

    }


    public static ImageLoader getsImageLoader(Context context) {
        if (sImageLoader == null) {
            sImageLoader = new ImageLoader(getInstance(context), new ImageLoader.ImageCache() {

                private final LruCache<String, Bitmap> myCache = new LruCache<String,Bitmap>(30);

                @Override
                public Bitmap getBitmap(String url) {
                    return myCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    myCache.put(url, bitmap);
                }
            });
        }
        return sImageLoader;

    }


}
