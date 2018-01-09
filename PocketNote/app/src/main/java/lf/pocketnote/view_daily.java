package lf.pocketnote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class view_daily extends ActionBarActivity {

    DBHelpertrans mydb;
    DBHelper md;
    ListView lv;
    TextView t,t1;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String date = "date";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String s = sharedpreferences.getString(Name, "");
        String d = sharedpreferences.getString(date, "");
        t=(TextView)findViewById(R.id.exp);
        t1=(TextView)findViewById(R.id.inc);
        mydb = new DBHelpertrans(this);
        Cursor c=mydb.getexpense(s,d);
        int sum=0;
        String amt1="";
        while(c.moveToNext())
        {
            amt1 = c.getString(c.getColumnIndex(DBHelpertrans.TRANS_COLUMN_AMOUNT));
            sum=sum+(Integer.parseInt(amt1));
        }
        String s1="",s2="";
        s1=String.valueOf(sum);
        t.setText(s1);
        Cursor c1=mydb.getincome(s,d);
        int sum1=0;
        String amt2="";
        while(c1.moveToNext())
        {
            amt2 = c1.getString(c1.getColumnIndex(DBHelpertrans.TRANS_COLUMN_AMOUNT));
            sum1=sum1+(Integer.parseInt(amt2));
        }
        s2=String.valueOf(sum1);
        t1.setText(s2);
        try {

            Cursor rs = mydb.viewtransaction(s,d);
            lv = (ListView) findViewById(R.id.lv);
            ArrayList list = new ArrayList();
            String transact = "";
            while (rs.moveToNext()) {
                String date = "", tt = "", src = "", amt = "", id = "";

                id = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID));
                tt = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_TRANTYPE));
                src = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_SOURCE));
                amt = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_AMOUNT));
                date = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_DATE));
                transact = "";

                transact = id + " " + tt + " " + src + " " + amt + " " + date;
                System.out.println("transaction in loop :" + transact);

                list.add(transact);
            }
            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            System.out.println("transaction :" + transact);
            lv.setAdapter(adapter);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_daily, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
