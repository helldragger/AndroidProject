package com.example.natw.annonce.propriete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.natw.annonce.profile.Seller;

public class PropertyManager {
    private PropertyDb propertyDb;
    private SellerSource sellerSource;
    private CommentsSource commentSource;
    private ImagesSource imagesSource;
    private CharacteristicSource characteristicSource;

    public PropertyManager(Context context) {
        this.propertyDb = new PropertyDb(context);
        this.sellerSource = new SellerSource(context);
        this.commentSource = new CommentsSource(context);
        this.imagesSource = new ImagesSource(context);
        this.characteristicSource = new CharacteristicSource(context);
    }


    public void close() {
        propertyDb.close();
        sellerSource.close();
        commentSource.close();
        imagesSource.close();
        characteristicSource.close();
    }


    private Property cursorToProperty(Cursor cursor) {
        Property property = new Property();
        long propertyId = cursor.getLong(0);
        property.setId(propertyId);
        property.setTitre(cursor.getString(1));
        property.setDescription(cursor.getString(2));
        property.setNbPieces(cursor.getInt(3));
        property.setPrix(cursor.getInt(4));
        property.setVille(cursor.getString(5));
        property.setCodePostal(cursor.getString(6));
        property.setVendeur(sellerSource.getSeller(cursor.getString(7)));
        property.setCharacteristics(characteristicSource.getPropertyCharacteristics(propertyId));
        property.setComments(commentSource.getPropertyComment(propertyId));
        property.setImages(imagesSource.getPropertyImages(propertyId));
        return property;
    }

    public Property getProperty(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_PROPERTY,
                PropertyDb.ALL_PROPERTY_COLUMNS,
                PropertyDb.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        Property property = cursorToProperty(cursor);
        cursor.close();
        return property;
    }


    public void registerProperty(Property property) {
        ContentValues values = new ContentValues();

        values.put(PropertyDb.COLUMN_PROPERTY_TITLE, property.getTitre());
        values.put(PropertyDb.COLUMN_PROPERTY_DESC, property.getDescription());
        values.put(PropertyDb.COLUMN_PROPERTY_ROOMS, property.getNbPieces());
        values.put(PropertyDb.COLUMN_PROPERTY_PRICE, property.getPrix());
        values.put(PropertyDb.COLUMN_PROPERTY_CITY, property.getVille());
        values.put(PropertyDb.COLUMN_PROPERTY_POSTAL, property.getCodePostal());
        values.put(PropertyDb.COLUMN_PROPERTY_SELLER, property.getVendeur().getId());

        SQLiteDatabase writableDb = this.propertyDb.getWritableDatabase();
        writableDb.insert(PropertyDb.TABLE_PROPERTY, null,
                values);

    }

    public void registerComment(Comment comment) {
        this.commentSource.registerComment(comment);
    }

    public void registerSeller(Seller seller) {
        this.sellerSource.registerSeller(seller);
    }

    public void registerPropertyImage(PropertyImage image) {
        this.imagesSource.registerPropertyImage(image);
    }

    public void registerCharacteristic(Characteristic characteristic) {
        this.characteristicSource.registerCharacteristic(characteristic);
    }
}

