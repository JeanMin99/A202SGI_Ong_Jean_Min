package com.example.ongje.rusher;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




public class Login_Database extends SQLiteOpenHelper {


    //table name=loginTable
    private final static int    DB_VERSION = 100;

    //Constructor
    public Login_Database(Context context)
    {
        super(context,"rusher.db",null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "create table loginTable (userID Integer primary key autoincrement, "+
                " username text, password text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try{
            System.out.println("UPGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
            onCreate(db);
            if (oldVersion<10){
                String query = "create table loginTable (userID Integer primary key autoincrement, "+
                        " username text, password text)";
                db.execSQL(query);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);

    }

    public Info_User insertUser (Info_User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userID=database.insert("loginTable", null, values);
        database.close();
        return queryValues;
    }

    public int updateUserPassword (Info_User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userID=database.insert("loginTable", null, values);
        database.close();
        return database.update("loginTable", values, "userID = ?", new String[] {String.valueOf(queryValues.userID)});
    }

    public Info_User getUser (String username){
        String query = "Select userID, password from loginTable where username ='"+username+"'";
        Info_User Info_User = new Info_User(0,username,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor= database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                Info_User.userID=cursor.getLong(0);
                Info_User.password=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return Info_User;
    }
}
