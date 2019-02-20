package com.example.natw.annonce.propriete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


class ImagesSource {
    private PropertyDb propertyDb;

    ImagesSource(Context context) {
        this.propertyDb = new PropertyDb(context);
    }


    public PropertyImage getPropertyImage(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_IMAGES,
                PropertyDb.ALL_IMAGES_COLUMNS,
                PropertyDb.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        PropertyImage image = cursorToPropertyImage(cursor);
        cursor.close();
        return image;
    }

    public List<PropertyImage> getPropertyImages(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_IMAGES,
                PropertyDb.ALL_IMAGES_COLUMNS,
                PropertyDb.COLUMN_IMAGES_PROPERTY + " = " + id,
                null,
                null,
                null,
                null);
        List<PropertyImage> results = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                results.add(cursorToPropertyImage(cursor));
            } while (!cursor.moveToNext());
        }
        return results;
    }


    public PropertyImage getPropertyImage(String id) {
        return getPropertyImage(Long.parseLong(id));
    }

    private PropertyImage cursorToPropertyImage(Cursor cursor) {
        PropertyImage image = new PropertyImage();
        image.setId(cursor.getLong(0));
        image.setPropertyId(cursor.getLong(1));
        image.setPath(cursor.getString(2));
        return image;
    }

    public void registerPropertyImage(PropertyImage image) {

        SQLiteDatabase writableDb = this.propertyDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PropertyDb.COLUMN_IMAGES_PROPERTY, image.getPropertyId());
        values.put(PropertyDb.COLUMN_IMAGES_PATH, image.getPath());

        writableDb.insert(PropertyDb.TABLE_IMAGES, null,
                values);
    }

    public void close() {
        propertyDb.close();
    }

}
