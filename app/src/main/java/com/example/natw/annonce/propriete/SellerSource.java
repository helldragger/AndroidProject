package com.example.natw.annonce.propriete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.natw.annonce.profile.Seller;


class SellerSource {
    private PropertyDb propertyDb;

    SellerSource(Context context) {
        this.propertyDb = new PropertyDb(context);
    }

    public Seller getSeller(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_SELLERS,
                PropertyDb.ALL_SELLER_COLUMNS,
                PropertyDb.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        Seller seller = cursorToSeller(cursor);
        cursor.close();
        return seller;
    }

    public Seller getSeller(String id) {
        return getSeller(Long.parseLong(id));
    }

    private Seller cursorToSeller(Cursor cursor) {
        Seller seller = new Seller();
        seller.setId(cursor.getLong(0));
        seller.setName(cursor.getString(1));
        seller.setFirstName(cursor.getString(2));
        seller.setEmail(cursor.getString(3));
        seller.setPhone(cursor.getString(4));
        return seller;
    }

    public void registerSeller(Seller seller) {

        SQLiteDatabase writableDb = this.propertyDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PropertyDb.COLUMN_SELLER_NAME, seller.getName());
        values.put(PropertyDb.COLUMN_SELLER_SURNAME, seller.getFirstName());
        values.put(PropertyDb.COLUMN_SELLER_EMAIL, seller.getEmail());
        values.put(PropertyDb.COLUMN_SELLER_PHONE, seller.getPhone());

        writableDb.insert(PropertyDb.TABLE_SELLERS, null,
                values);
    }

    public void close() {
        propertyDb.close();
    }
}
