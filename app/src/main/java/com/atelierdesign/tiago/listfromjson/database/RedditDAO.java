package com.atelierdesign.tiago.listfromjson.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atelierdesign.tiago.listfromjson.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago on 10-03-2015.
 * UMA SINGLETON PARA SER ACEDIDA EM TODA A APLICAÇÃO
 */
public class RedditDAO {

    // A instancia da propria class UAU
    /*
    SINGLETON PATTERN
     */
    private static RedditDAO sInstance = null;


    public static RedditDAO getInstance() {
        if (sInstance == null) {
            sInstance = new RedditDAO();
        }
        return sInstance;
    }


    public boolean storePosts(Context context, List<Post> postList) {

        //modificação para nao repetir registos na DB
        List<Post> storedPosts = RedditDAO.getInstance().getPostFromDB(context);


        try {
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

            db.beginTransaction();


            // a por os posts na DB
            for (Post post : postList) {

                Boolean isInDatabase = false;

                for (Post postStored : storedPosts) {
                    if (post.getTitle().equals(postStored.getTitle())) ;
                    isInDatabase = true;
                }

                if (!isInDatabase) {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseContract.PostTable.LINK, post.getPermalink());
                    cv.put(DatabaseContract.PostTable.IMAGE_LINK, post.getThumbnail());
                    cv.put(DatabaseContract.PostTable.TITLE, post.getTitle());

                    db.insert(DatabaseContract.PostTable.TABLE_NAME, null, cv);
                }

            }

            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();

        } catch (Exception e) {
            return false;
        }
        return true;

    }

    // metodo para fornecer a lista de posts

    public List<Post> getPostFromDB(Context context) {

        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();

        Cursor cursor = db.query(DatabaseContract.PostTable.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        List<Post> postList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.TITLE));
            String link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.LINK));
            String image_link = cursor.getString(cursor.getColumnIndex(DatabaseContract.PostTable.IMAGE_LINK));

            Post post = new Post(link, image_link, title);

            postList.add(post);
        }
        cursor.close();
        db.close();

        return postList;
    }


}
