package com.ethiopia.addisababa.manny.reverserecipe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_RECIPES = "Recipes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "recipename";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_RECIPES + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_RECIPENAME + " TEXT " +
                ");";
        Log.d("query create", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    public void addRecipe(Recipe recipe){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, recipe.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECIPES,null,values);
        db.close();
    }

    public void deleteRecipe(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_RECIPES+" WHERE "+COLUMN_RECIPENAME+ " =\""+productName+"\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_RECIPES+" WHERE 1";
        Log.d("query_str", query);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Log.d("db_to_str",c.toString());
            if(c.getString(c.getColumnIndex("recipename")) != null) {
                dbString += c.getString(c.getColumnIndex("recipename"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public boolean inDatabase(Recipe recipe){
        String query = "SELECT * FROM "+TABLE_RECIPES+" WHERE recipename='"+recipe.get_productName()+"'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Log.d("db_to_str",c.toString());
            if(c.getString(c.getColumnIndex("recipename")) != null) {
                return true;
            }
            c.moveToNext();
        }
        db.close();
        return false;
    }
    public String[] getAllRecipes(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_RECIPES+" WHERE 1";
        Cursor c = db.rawQuery(query, null);
        String[] dbString = new String[c.getCount()];
        c.moveToFirst();
        int count = 0;
        while(!c.isAfterLast()){
            Log.d("db_to_str",c.toString());
            if(c.getString(c.getColumnIndex("recipename")) != null) {
                dbString[count] = c.getString(c.getColumnIndex("recipename"));
                count++;
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
