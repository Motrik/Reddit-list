package com.atelierdesign.tiago.listfromjson.database;

/**
 * Created by Tiago on 10-03-2015.
 */
public class DatabaseContract {

    public static final String DB_NAME = "reddit_2database.db";




// uma abstract dentro do contrato ?????? investigar melhor
    // diz que não posso criar objectos a partir de abstract class  -  é apenas para criar umas definições

    public abstract class PostTable {

        public static final String TABLE_NAME = "post_table";

        public static final String TITLE = "title";
        public static final String LINK = "link";
        public static final String IMAGE_LINK = "image";
    }

}
