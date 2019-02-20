package com.example.natw.annonce.propriete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


class CharacteristicSource {
    private PropertyDb propertyDb;

    CharacteristicSource(Context context) {
        this.propertyDb = new PropertyDb(context);
    }


    public Characteristic getCharacteristic(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_CARACTERISTIQUES,
                PropertyDb.ALL_CARACTERISTIQUES_COLUMNS,
                PropertyDb.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        Characteristic characteristic = cursorToCharacteristic(cursor);
        cursor.close();
        return characteristic;
    }

    public List<Characteristic> getPropertyCharacteristics(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_CARACTERISTIQUES,
                PropertyDb.ALL_CARACTERISTIQUES_COLUMNS,
                PropertyDb.COLUMN_CARACTERISTIQUES_PROPERTY + " = " + id,
                null,
                null,
                null,
                null);
        List<Characteristic> results = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                results.add(cursorToCharacteristic(cursor));
            } while (!cursor.moveToNext());
        }
        return results;
    }

    public List<Characteristic> getPropertyCharacteristics(String id) {
        return getPropertyCharacteristics(Long.parseLong(id));
    }

    public Characteristic getCharacteristic(String id) {
        return getCharacteristic(Long.parseLong(id));
    }

    private Characteristic cursorToCharacteristic(Cursor cursor) {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(cursor.getLong(0));
        characteristic.setPropertyId(cursor.getLong(1));
        characteristic.setContent(cursor.getString(2));
        return characteristic;
    }

    public void registerCharacteristic(Characteristic characteristic) {

        SQLiteDatabase writableDb = this.propertyDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PropertyDb.COLUMN_CARACTERISTIQUES_PROPERTY, characteristic.getPropertyId());
        values.put(PropertyDb.COLUMN_CARACTERISTIQUES_CONTENT, characteristic.getContent());

        writableDb.insert(PropertyDb.TABLE_CARACTERISTIQUES, null,
                values);
    }

    public void close() {
        propertyDb.close();
    }
}
