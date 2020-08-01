package com.example.readeraudiobook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CopyDatabase extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/com.example.readeraudiobook/databases/";
    public static String DB_NAME = "sing.db";
    public static final int DB_VERSION = 1;
    Context context;
    private SQLiteDatabase mDataBase;

    public CopyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
        boolean check=checkdatabase();
        if (check){
            openDB();
        } else {
            System.out.println("Database doesn't exist");
            createDB();
        }
    }


    public List<Model> barchasi() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Model> modelList = new ArrayList<>();
        String s = null;

        s= "select * from book";
        Cursor cursor = db.rawQuery(s, null);
        if (cursor.moveToFirst()) {

            do {
                Model model;
                model = new Model();
                model.setId(cursor.getInt(cursor.getColumnIndex("id")));
                model.setName(cursor.getString(cursor.getColumnIndex("name")));
                    model.setDescr_uz(String.valueOf(cursor.getString(cursor.getColumnIndex("descr_uz"))));

                modelList.add(model);


            } while (cursor.moveToNext());
        }
        db.close();
        return modelList;
    }

    public void createDB() {
        boolean dbExist = checkdatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void copyDB() throws IOException {
        InputStream dbInput = context.getAssets().open(DB_NAME);
        String outFile = DB_PATH + DB_NAME;
        OutputStream dbOutput = new FileOutputStream(outFile);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbInput.read(buffer)) > 0) {
            dbOutput.write(buffer, 0, length);
        }
        dbOutput.flush();
        dbOutput.close();
        dbInput.close();
    }


    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;


            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private boolean checkDB() {
        SQLiteDatabase check = null;
        try {
            String dbPath = DB_PATH + DB_NAME;
            check = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (check != null) {
            check.close();
        }
        return check != null ? true : false;
    }

    public void openDB() {
        String dbPath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    public Cursor QueryData(String query){
        return mDataBase.rawQuery(query,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE category (_id INTEGER PRIMARY KEY AUTOINCREMENT, cat_eng TEXT, cat_uz TEXT, image TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            try
            {
                this.copyDB();
            }
            catch (IOException x){
                x.printStackTrace();

            }
        }
    }
}
