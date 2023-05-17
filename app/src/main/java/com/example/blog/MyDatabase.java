package com.example.blog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabase extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME ="blog";
    private static final int DATABASE_VERSION =1;


    public static final String TABLE_NAME ="mesblogs";
    public static final String COLUMN_ID ="_id";
    public static final String COLUMN_AUTHOR ="auteur_blog";
    public static final String COLUMN_TITLE ="titre_blog";
    public static final String COLUMN_TEXT ="text_blog";
    public static final String COLUMN_DATE ="date_blog";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                " ( "+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_AUTHOR+" TEXT,"+
                COLUMN_TITLE+" TEXT,"+
                COLUMN_TEXT+" TEXT,"+
                COLUMN_DATE+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addBlog(String auteur,String title,String text,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AUTHOR,auteur);
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_TEXT,text);
        cv.put(COLUMN_DATE,date);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"L'ajout a échoué",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"L'ajout est un Succès",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query ="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}
