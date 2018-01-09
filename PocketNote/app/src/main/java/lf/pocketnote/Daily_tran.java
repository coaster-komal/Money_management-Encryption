package lf.pocketnote;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Daily_tran extends ActionBarActivity {

    DBHelpertrans mydb;
    DBHelper md;
    ListView lv,lv1,lv2,lv3;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tran);
        mydb = new DBHelpertrans(this);
        try {
            String s = sharedpreferences.getString(Name, "");
            Cursor rs = mydb.getDatatran(s);
            lv = (ListView) findViewById(R.id.i);
            lv1 = (ListView) findViewById(R.id.type);
            lv2 = (ListView) findViewById(R.id.source);
            lv3 = (ListView) findViewById(R.id.dt);
            ArrayList list = new ArrayList();
            ArrayList list1 = new ArrayList();
            ArrayList list2 = new ArrayList();
            ArrayList list3 = new ArrayList();
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

                list.add(id);
                list1.add(tt);
                list2.add(src);
                list3.add(date);
            }
            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            final ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list1);
            final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list2);
            final ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list3);
            System.out.println("transaction :" + transact);
            lv.setAdapter(adapter);
            lv1.setAdapter(adapter1);
            lv2.setAdapter(adapter2);
            lv3.setAdapter(adapter3);

        }
        catch (Exception ex) {
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_tran, menu);
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
