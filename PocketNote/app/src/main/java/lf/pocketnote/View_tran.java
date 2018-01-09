package lf.pocketnote;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ListView;

import java.util.ArrayList;
public class View_tran extends ActionBarActivity {

    DBHelpertrans mydb;
    DBHelper md;
    ListView lv;
    Intent tolog;
    Button bt,bt1,bt2;
    TableLayout table_layout;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tran);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        addListenerOnButton();

     /*   mydb = new DBHelpertrans(this);
        try {
            String s = sharedpreferences.getString(Name, "");
            Cursor rs = mydb.getDatatran(s);
            lv = (ListView) findViewById(R.id.l1);
            ArrayList list = new ArrayList();
            String transact="";
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

            //  sqlcon.open();
         Cursor c=mydb.getDatatran(s);
            int rows=c.getCount();
            int cols=c.getColumnCount();

            c.moveToFirst();
            System.out.println("STRING 1" + c.getString(1));
            System.out.println("STRING 2"+ c.getString(2));
            System.out.println("STRING 3"+ c.getString(3));
            System.out.println("STRING 4"+ c.getString(4));

            for(int i=1;i<rows;i++)
            {
                TableRow row=new TableRow(this);
                row.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
                for(int j=1;j<cols;j++) {
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                    tv.setBackgroundResource(R.drawable.bk5);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(18);
                    tv.setPadding(0, 5, 0, 5);
                    tv.setText(c.getString(j));
                    System.out.println(c.getString(j));
                    row.addView(tv);


                }
                c.moveToNext();
                table_layout.addView(row);
            }
          //  sqlcon.close();

                }



        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
*/
    }
    private void addListenerOnButton() {

        bt = (Button) findViewById(R.id.d);
        bt.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View view) {
                                      tolog = new Intent(View_tran.this, Daily_report.class);
                                      startActivity(tolog);

                                  }
                              }
        );
        bt1 = (Button) findViewById(R.id.m);
        bt1.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View view) {
                                      tolog = new Intent(View_tran.this, Montly_report.class);
                                      startActivity(tolog);

                                  }
                              }
        );
        bt2 = (Button) findViewById(R.id.y);
        bt2.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View view) {
                                       tolog = new Intent(View_tran.this, Yearly_report.class);
                                       startActivity(tolog);

                                   }
                               }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_tran, menu);
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
