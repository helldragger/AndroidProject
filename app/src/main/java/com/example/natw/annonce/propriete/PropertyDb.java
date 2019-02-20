package com.example.natw.annonce.propriete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class PropertyDb extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "_id";

    public static final String TABLE_PROPERTY = "property";
    public static final String COLUMN_PROPERTY_TITLE = "titre";
    public static final String COLUMN_PROPERTY_DESC = "description";
    public static final String COLUMN_PROPERTY_ROOMS = "nbPieces";
    public static final String COLUMN_PROPERTY_PRICE = "prix";
    public static final String COLUMN_PROPERTY_CITY = "ville";
    public static final String COLUMN_PROPERTY_POSTAL = "codePostal";
    public static final String COLUMN_PROPERTY_SELLER = "vendeur_id";
    public static final String[] ALL_PROPERTY_COLUMNS = new String[]{
            COLUMN_ID,
            COLUMN_PROPERTY_TITLE,
            COLUMN_PROPERTY_DESC,
            COLUMN_PROPERTY_ROOMS,
            COLUMN_PROPERTY_PRICE,
            COLUMN_PROPERTY_CITY,
            COLUMN_PROPERTY_POSTAL,
            COLUMN_PROPERTY_SELLER
    };

    public static final String TABLE_SELLERS = "sellers";
    public static final String COLUMN_SELLER_NAME = "name";
    public static final String COLUMN_SELLER_SURNAME = "surname";
    public static final String COLUMN_SELLER_EMAIL = "email";
    public static final String COLUMN_SELLER_PHONE = "phone";
    public static final String[] ALL_SELLER_COLUMNS = new String[]{
            COLUMN_ID,
            COLUMN_SELLER_NAME,
            COLUMN_SELLER_SURNAME,
            COLUMN_SELLER_EMAIL,
            COLUMN_SELLER_PHONE
    };

    public static final String TABLE_CARACTERISTIQUES = "caracteristiques";
    public static final String COLUMN_CARACTERISTIQUES_PROPERTY = "property_id";
    public static final String COLUMN_CARACTERISTIQUES_CONTENT = "content";
    public static final String[] ALL_CARACTERISTIQUES_COLUMNS = new String[]{
            COLUMN_ID,
            COLUMN_CARACTERISTIQUES_PROPERTY,
            COLUMN_CARACTERISTIQUES_CONTENT
    };

    public static final String TABLE_IMAGES = "images";
    public static final String COLUMN_IMAGES_PROPERTY = "property_id";
    public static final String COLUMN_IMAGES_PATH = "path";
    public static final String[] ALL_IMAGES_COLUMNS = new String[]{
            COLUMN_ID,
            COLUMN_IMAGES_PROPERTY,
            COLUMN_IMAGES_PATH
    };


    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_COMMENTS_PROPERTY = "property_id";
    public static final String COLUMN_COMMENTS_CONTENT = "content";
    public static final String COLUMN_COMMENTS_AUTHOR = "author";
    public static final String[] ALL_COMMENTS_COLUMNS = new String[]{
            COLUMN_ID,
            COLUMN_COMMENTS_PROPERTY,
            COLUMN_COMMENTS_CONTENT,
            COLUMN_COMMENTS_AUTHOR
    };

    private static final String COMMENTS_DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COMMENTS_PROPERTY + " integer not null REFERENCES " + TABLE_PROPERTY + " (" + COLUMN_ID + ") , "
            + COLUMN_COMMENTS_CONTENT + " text not null ,"
            + COLUMN_COMMENTS_AUTHOR + " integer not null REFERENCES " + TABLE_SELLERS + " (" + COLUMN_ID + "));";

    private static final String CARACTERISTICS_DATABASE_CREATE = "create table "
            + TABLE_CARACTERISTIQUES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CARACTERISTIQUES_PROPERTY + " integer not null REFERENCES " + TABLE_PROPERTY + " (" + COLUMN_ID + ") , "
            + COLUMN_CARACTERISTIQUES_CONTENT + " text not null);";

    private static final String IMAGES_DATABASE_CREATE = "create table "
            + TABLE_IMAGES + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_IMAGES_PROPERTY + " integer not null REFERENCES " + TABLE_PROPERTY + " (" + COLUMN_ID + ") , "
            + COLUMN_IMAGES_PATH + " text not null);";

    private static final String PROPERTY_DATABASE_CREATE = "create table "
            + TABLE_PROPERTY + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PROPERTY_TITLE + " text not null, "
            + COLUMN_PROPERTY_DESC + " text not null, "
            + COLUMN_PROPERTY_ROOMS + " integer not null, "
            + COLUMN_PROPERTY_PRICE + " integer not null, "
            + COLUMN_PROPERTY_CITY + " text not null, "
            + COLUMN_PROPERTY_POSTAL + " text not null, "
            + COLUMN_PROPERTY_SELLER + " integer not null REFERENCES " + TABLE_SELLERS + " (" + COLUMN_ID + "));";


    private static final String SELLER_DATABASE_CREATE = "create table "
            + TABLE_SELLERS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SELLER_NAME + " text not null, "
            + COLUMN_SELLER_SURNAME + " text not null, "
            + COLUMN_SELLER_EMAIL + " text not null, "
            + COLUMN_SELLER_PHONE + " text not null);";

    private static final String DATABASE_NAME = "property.db";
    private static final int DATABASE_VERSION = 1;

    public PropertyDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(COMMENTS_DATABASE_CREATE);
        db.execSQL(CARACTERISTICS_DATABASE_CREATE);
        db.execSQL(IMAGES_DATABASE_CREATE);
        db.execSQL(PROPERTY_DATABASE_CREATE);
        db.execSQL(SELLER_DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PropertyDb.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPERTY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELLERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARACTERISTIQUES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        onCreate(db);
    }
}
