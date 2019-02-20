package com.example.natw.annonce.propriete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


class CommentsSource {
    private PropertyDb propertyDb;

    CommentsSource(Context context) {
        this.propertyDb = new PropertyDb(context);
    }


    public Comment getComment(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_COMMENTS,
                PropertyDb.ALL_COMMENTS_COLUMNS,
                PropertyDb.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        Comment comment = cursorToComment(cursor);
        cursor.close();
        return comment;
    }

    public List<Comment> getPropertyComment(long id) {
        SQLiteDatabase readableDb = this.propertyDb.getReadableDatabase();
        Cursor cursor = readableDb.query(
                PropertyDb.TABLE_COMMENTS,
                PropertyDb.ALL_COMMENTS_COLUMNS,
                PropertyDb.COLUMN_COMMENTS_PROPERTY + " = " + id,
                null,
                null,
                null,
                null);
        List<Comment> results = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                results.add(cursorToComment(cursor));
            } while (!cursor.moveToNext());
        }
        return results;
    }

    public List<Comment> getPropertyComment(String id) {
        return getPropertyComment(Long.parseLong(id));
    }

    public Comment getComment(String id) {
        return getComment(Long.parseLong(id));
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setPropertyId(cursor.getLong(1));
        comment.setContent(cursor.getString(2));
        comment.setAuthor(cursor.getLong(3));
        return comment;
    }

    public void registerComment(Comment comment) {

        SQLiteDatabase writableDb = this.propertyDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PropertyDb.COLUMN_COMMENTS_PROPERTY, comment.getPropertyId());
        values.put(PropertyDb.COLUMN_COMMENTS_CONTENT, comment.getContent());
        values.put(PropertyDb.COLUMN_COMMENTS_AUTHOR, comment.getAuthor());

        writableDb.insert(PropertyDb.TABLE_COMMENTS, null,
                values);
    }

    public void close() {
        propertyDb.close();
    }

}
