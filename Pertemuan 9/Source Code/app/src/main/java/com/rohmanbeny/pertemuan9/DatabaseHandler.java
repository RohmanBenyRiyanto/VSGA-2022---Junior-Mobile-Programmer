package com.rohmanbeny.pertemuan9;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rohmanbeny.pertemuan9.model.CatatanModel;
import com.rohmanbeny.pertemuan9.model.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static DatabaseHandler instance;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name DB
    private static final String DATABASE_NAME = "db_buku";

    // Table name
    private static final String TABLE_POSTS = "post";
    private static final String TABLE_BOOKS = "buku";

    // Post Table Columns
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_BOOK_ID_FK = "bookId";
    private static final String KEY_POST_TEXT = "text";

    // Book Table Columns
    private static final String KEY_BOOK_ID = "id";
    private static final String KEY_BOOK_JUDUL = "judul";

//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POSTS + "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_POST_BOOK_ID_FK + " INTEGER REFERENCES " + TABLE_BOOKS + "," +
                KEY_POST_TEXT + " TEXT" +
                ")";

        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOKS + "(" +
                KEY_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_BOOK_JUDUL + " TEXT" +
                ")";

        db.execSQL(CREATE_POST_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
            onCreate(db);
        }
    }

    //Singleton patterns for DatabaseHandler
    public static synchronized DatabaseHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Insert data
    public void addBuku(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();

        try {
            long id = addOrUpdateBook(post.catatan);

            ContentValues values = new ContentValues();
            values.put(KEY_POST_BOOK_ID_FK, id);
            values.put(KEY_POST_TEXT, post.text);

            db.insertOrThrow(TABLE_POSTS, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e(DatabaseHandler.class.getSimpleName(), "Error while trying to add post to database", e);
        } finally {
            db.endTransaction();

        }
    }


    private long addOrUpdateBook(CatatanModel catatatan) {
        SQLiteDatabase db = this.getWritableDatabase();

        long id = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_BOOK_JUDUL, catatatan.name);

            int rows = db.update(TABLE_BOOKS, values, KEY_BOOK_ID + " = ?", new String[]{String.valueOf(catatatan.name)});

            // Cek apakah data sudah berhasil diupdate atau belum
            if (rows == 1) {
                String bukuSelectQuery = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_BOOKS, KEY_BOOK_ID, KEY_BOOK_JUDUL);

                Cursor cursor = db.rawQuery(bukuSelectQuery, new String[]{String.valueOf(catatatan.name)});

                try {
                    if (cursor.moveToFirst()) {
                        id = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                //Buku belum ada, maka insert baru
                id = db.insertOrThrow(TABLE_BOOKS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e(DatabaseHandler.class.getSimpleName(), "Error while trying to add book to database", e);
        } finally {
            db.endTransaction();
        }
        return id;
    }

    @SuppressLint("Range")
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        String selectQuery =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        TABLE_POSTS,
                        TABLE_BOOKS,
                        TABLE_POSTS, KEY_POST_BOOK_ID_FK,
                        TABLE_BOOKS, KEY_BOOK_ID);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    CatatanModel newBuku = new CatatanModel();
                    newBuku.name =  cursor.getString(cursor.getColumnIndex(KEY_BOOK_JUDUL));


                    Post newPost = new Post();
                    newPost.catatan = newBuku;
                    newPost.text = cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT));

                    posts.add(newPost);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(DatabaseHandler.class.getSimpleName(), "Error while trying to get posts from database", e);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return posts;
    }

    //Update data
    public int updatePost(CatatanModel book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOOK_JUDUL, book.name);

        return db.update(TABLE_BOOKS, values, KEY_BOOK_JUDUL + " = ?",
                new String[] { String.valueOf(book.name) });
    }

    // Delete data
    public void deleteAllPostsAndUsers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(TABLE_POSTS, null, null);
            db.delete(TABLE_BOOKS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(DatabaseHandler.class.getSimpleName(), "Error while trying to delete all posts and users from database", e);
        } finally {
            db.endTransaction();
        }
    }
}