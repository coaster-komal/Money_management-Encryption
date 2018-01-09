package lf.pocketnote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by acer on 10/26/2015.
 */
public class DBHelpertrans extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "MyDB.db";
    public static final String TRANSACTION_TABLE_NAME = "trans";
    public static final String TRANS_COLUMN_USERNAME = "username";
    public static final String TRANS_COLUMN_TRANTYPE = "trantype";
    public static final String TRANS_COLUMN_SOURCE = "source";
    public static final String TRANS_COLUMN_AMOUNT = "amount";
    public static final String TRANS_COLUMN_DATE = "date";
    public static final String TRANS_COLUMN_ID = "id";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    private HashMap hp;
    public DBHelpertrans(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        try 
        {
            db.execSQL("create table trans (id text primary key, username text, trantype text, source text, amount text, date text)");
        }
        catch(Exception ev)
        {
            System.out.println(ev.getMessage());
        }
    }
    public void insertinitial(String name)
    {
        String s="",s1="",s2="",s3="",s4="";
    //    s=sharedpreferences.getString(Name,"");
        s=name;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", 0);
        contentValues.put("username",s);
        contentValues.put("trantype", s1);
        contentValues.put("source", s2);
        contentValues.put("amount", s3);
        contentValues.put("date", s4);
        db.insert("trans", null, contentValues);


       // inserttransaction(s,s1,s2,s3,s4);
        System.out.println("inserted");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS trans");
        onCreate(db);
    }
    public boolean inserttransaction (String name, String type , String src,String amt,String date )
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            int b = 0;
            Cursor a=null;
            a=getid();
            while(a.moveToNext()) {

                System.out.println("id " + a.getString(a.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID)));
                b = Integer.parseInt(a.getString(a.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID)));
                System.out.println("id from table " + b);

            }
                b++;

            System.out.println("value of b after increment "+b);
            String bb="";
            bb =String.valueOf(b);

            System.out.println("id from table on increment value of bb  "+bb);

            contentValues.put("id", bb);
            contentValues.put("username", name);
            contentValues.put("trantype", type);
            contentValues.put("source", src);
            contentValues.put("amount", amt);
            contentValues.put("date", date);
            db.insert("trans", null, contentValues);
        }
        catch(Exception ev)
        {
            System.out.println(ev.getMessage());
        }

        return true;
    }
    public Cursor getDatatran(String uname)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from trans where username='" + uname + "'", null);
      //  Cursor res = db.rawQuery( "select * from trans where date='"+uname+"'", null );
        // month=mn+"/"+year;
//Cursor res = db.rawQuery( "select * from trans where month like '%"+uname+"'", null );
        //year
//Cursor res = db.rawQuery( "select * from trans where month like '%"+uname+"'", null );

        return res;
    }

    public boolean updatetran (String id,String name, String type , String src,String amt,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
    //    contentValues.put("username", name);
        contentValues.put("trantype", type);
        contentValues.put("source", src);
        contentValues.put("amount", amt);
        contentValues.put("date", date);
        db.update("trans", contentValues, "id = ? and username=? "  , new String[] { id,name} );
        return true;
    }
    public Integer deletetransaction (String id,String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("trans", "id = ? and username=? ", new String[] { id,name });
    }
    public Cursor viewtransaction(String name,String date)
    {
        Cursor r=null;
        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select * from trans where date='"+date+"' and username='"+name+"'", null );
        return r;
    }
    public Cursor countid(String name)
    {
        Cursor r=null;
        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select id from trans where username='"+name+"'", null );
        return r;
    }
    public Cursor viewtransactionmonthly(String name,String date)
    {
        Cursor r=null;
        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select * from trans where date like '%"+date+"' and username='"+name+"' ", null );
        return r;
    }
    public Cursor getexpense(String name,String date)
    {
        Cursor r=null;
        String type="";
        type="EXPENSE";
        date="%"+date;
        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select amount from trans where username=? and trantype=? and date like ? ", new String[]{name,type,date} );
        return r;
    }
    public Cursor getincome(String name,String date)
    {
        Cursor r=null;
        String type="";
        type="INCOME";
        date="%"+date;

        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select amount from trans where username=? and trantype=? and date like ? ", new String[]{name,type,date} );
        return r;
    }
    public Cursor getid()
    {
        Cursor r=null;
        try {
             SQLiteDatabase db = this.getReadableDatabase();
            r = db.rawQuery("select id from trans", null);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return r;
    }
    public Cursor getids(String name)
    {
        Cursor r=null;
        SQLiteDatabase db=this.getReadableDatabase();
        r=db.rawQuery( "select id from trans where username='"+name+"' order by id", null );
        return r;
    }
    public Cursor getid1()
    {
        Cursor r=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            r = db.rawQuery("select id from trans", null);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return r;
    }
    public Cursor getdata1(String id)
    {
        Cursor r=null;
        SQLiteDatabase db = this.getReadableDatabase();
        r = db.rawQuery("select * from trans where id='" + id + "'", null);
        return r;
    }

}
