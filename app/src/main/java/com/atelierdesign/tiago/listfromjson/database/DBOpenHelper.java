package com.atelierdesign.tiago.listfromjson.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tiago on 10-03-2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /*
    DATABASE VERSION
     */
    private static final int DATABASE_VERSION = 6;

    /*
    TABLE STRINGS
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA = ", ";

    /*
    SQL CREATE TABLE SENTENCE
     */
    private static final String CREATE_POST_TABLE = "CREATE TABLE "
            + DatabaseContract.PostTable.TABLE_NAME + " ("
            + DatabaseContract.PostTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.LINK + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.IMAGE_LINK + TEXT_TYPE + " )";


    /*
    SQL DELETE TABLE SENTENCE
    */
    private static final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.PostTable.TABLE_NAME;

    public DBOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);  // é aqui que é utilizada a string construida em cima.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // este nao vamos usar
        // só é utilizado para fazer alterações às primeiras versoes da DB.
        db.execSQL(DROP_POST_TABLE);
        onCreate(db);
    }
}
