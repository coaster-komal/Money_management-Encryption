package lf.pocketnote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by acer on 10/16/2015.
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_USERNAME = "username";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    public static final String CONTACTS_COLUMN_PHONE = "phone";


    private HashMap hp;
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        try {
        //    db.execSQL("create table trans (id text primary key, username text, trantype text, source text, amount text, date text)");

            db.execSQL("create table contacts (username text primary key,password text,phone text)");
        }
        catch(Exception ev)
        {
            System.out.println(ev.getMessage());
        }
     }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
   //     db.execSQL("DROP TABLE IF EXISTS trans");
        onCreate(db);
    }
    public boolean insertContact (String name, String password, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", name);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Integer deleteContact (String name)
    { 
        String s[]=new String[1];
        s[0]=name;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "username = ? " ,s );
    }
   /* public Integer deletetransaction (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("transaction", "id = ? " ,id );
    }*/
    public Cursor getData(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select password from contacts where username='" + uname + "'", null);
        return res;
    }
    public Cursor viewphone(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery("select phone from contacts where username='" + name + "'", null);
        return c;

    }

    public Cursor getid()
    {
       Cursor r=null;
        try {
           // SQLiteDatabase db = this.getReadableDatabase();
           // r = db.rawQuery("select id from trans", null);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return r;
    }

}