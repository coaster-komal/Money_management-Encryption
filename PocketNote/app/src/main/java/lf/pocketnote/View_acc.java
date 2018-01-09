package lf.pocketnote;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class View_acc extends ActionBarActivity {

    TextView t,t1;
    DBHelper mydb;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acc);
        String s="",s1="";
        t=(TextView) findViewById(R.id.uname);
        t1=(TextView) findViewById(R.id.ph);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        t.setText(sharedpreferences.getString(Name,""));
        mydb = new DBHelper(this);
        Cursor c=mydb.viewphone(sharedpreferences.getString(Name,""));
        String p="";
        while (c.moveToNext()) {

            p = c.getString(c.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
            System.out.println("transaction in loop :" + p);
        }
        t1.setText(p);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_acc, menu);
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
