package lf.pocketnote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Montly_report extends ActionBarActivity {

    Button bt;
    Spinner sp1,sp2,sp3;
    DBHelpertrans mydb;
    ListView lv;
    Intent tolog;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    public static final String date = "date";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montly_report);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mydb = new DBHelpertrans(this);

        Spinner sp2=(Spinner)findViewById(R.id.month);
        String month[];
        month=new String[12];
        for(int i=0;i<=11;i++)
        {
            month[i]=String.valueOf(i+1);
        }
        ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,month);
        sp2.setAdapter(adapter2);

        Spinner sp3=(Spinner)findViewById(R.id.year);
        String year[];
        year=new String[200];
        for(int i=0;i<=199;i++)
        {
            year[i]=String.valueOf(i+1900);
        }
        ArrayAdapter<String>adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,year);
        sp3.setAdapter(adapter3);
        addListenerOnButton();
    }
    private void addListenerOnButton() {

        bt = (Button) findViewById(R.id.vt);
        bt.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View view) {
                                      String s = "", s1 = "", s2 = "", s3 = "", s4 = "";
                                      sp2 = (Spinner) findViewById(R.id.month);
                                      sp3 = (Spinner) findViewById(R.id.year);
                                      s1 = sp2.getSelectedItem().toString();
                                      s2 = sp3.getSelectedItem().toString();
                                      s3 = s1 + "/" + s2;
                                      s4 = sharedpreferences.getString(Name, "");
                                      Cursor r = mydb.viewtransactionmonthly(s4, s3);
                                      SharedPreferences.Editor editor = sharedpreferences.edit();
                                      editor.putString(date, s3);
                                      editor.commit();
                                      tolog = new Intent(Montly_report.this, View_montly.class);
                                      startActivity(tolog);
                                      lv = (ListView) findViewById(R.id.lv);
                                      ArrayList list = new ArrayList();
                                      String transact = "";
                                      while (r.moveToNext()) {
                                          String date = "", tt = "", src = "", amt = "", id = "";

                                          id = r.getString(r.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID));
                                          tt = r.getString(r.getColumnIndex(DBHelpertrans.TRANS_COLUMN_TRANTYPE));
                                          src = r.getString(r.getColumnIndex(DBHelpertrans.TRANS_COLUMN_SOURCE));
                                          amt = r.getString(r.getColumnIndex(DBHelpertrans.TRANS_COLUMN_AMOUNT));
                                          date = r.getString(r.getColumnIndex(DBHelpertrans.TRANS_COLUMN_DATE));
                                          transact = "";

                                          transact = id + " " + tt + " " + src + " " + amt + " " + date;
                                          System.out.println("transaction in loop :" + transact);

                                          list.add(transact);
                                      }
                                      //ArrayAdapter<String>adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,list);
                                      System.out.println("transaction :" + transact);
                                      //   lv.setAdapter(adapter4);
                                  }
                              }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_montly_report, menu);
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
