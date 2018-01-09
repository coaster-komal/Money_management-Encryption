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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Modify_tran extends ActionBarActivity {
    DBHelpertrans mydb;
    DBHelper md;
    Button bt,bt1;
    Spinner sp;
    Intent tolog;
    EditText et,et1,et2,et3;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_tran);
        Spinner dropdown = (Spinner) findViewById(R.id.sp1);
        sp=(Spinner) findViewById(R.id.sp1);
        mydb = new DBHelpertrans(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String s = sharedpreferences.getString(Name, "");
        Cursor rs = mydb.getDatatran(s);
        Cursor rid=mydb.countid(s);
        rid.moveToLast();
        int ccount=Integer.parseInt(rid.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID)));
        System.out.println("data "+ccount);
        String id[];
        int i = 1;
        id = new String[ccount];
        while (rs.moveToNext()) {
            id[i] = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID));
            i++;
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,id);
        dropdown.setAdapter(adapter);
        mydb = new DBHelpertrans(this);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        bt=(Button)findViewById(R.id.vt);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String s = "";
                s = sp.getSelectedItem().toString();
                Cursor rs = mydb.getdata1(s);
                while (rs.moveToNext()) {
                    String date = "", tt = "", src = "", amt = "", id = "", transact = "";

                    id = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_ID));
                    tt = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_TRANTYPE));
                    src = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_SOURCE));
                    amt = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_AMOUNT));
                    date = rs.getString(rs.getColumnIndex(DBHelpertrans.TRANS_COLUMN_DATE));
                    transact = "";

                    transact = id + " " + tt + " " + src + " " + amt + " " + date;
                    System.out.println("transaction in loop :" + transact);
                    et = (EditText) findViewById(R.id.type1);
                    et1 = (EditText) findViewById(R.id.src1);
                    et2 = (EditText) findViewById(R.id.amt1);
                    et3 = (EditText) findViewById(R.id.date1);
                    et.setText(tt);
                    et1.setText(src);
                    et2.setText(amt);
                    et3.setText(date);

                }
            }
        });


        bt1=(Button)findViewById(R.id.sc);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.type1);
                et1 = (EditText) findViewById(R.id.src1);
                et2 = (EditText) findViewById(R.id.amt1);

                String s = "", s1 = "", s2 = "",s3="",s4="",s5="";
                int am;
                s = sp.getSelectedItem().toString();
                s1 = et.getText().toString();
                s2 = et1.getText().toString();
                s3=et2.getText().toString();
                s4=sharedpreferences.getString(Name, "");
                s5=et3.getText().toString();
                am=Integer.parseInt(s3);
                try {
                    mydb.updatetran(s,s4, s1, s2, s3, s5);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                Toast.makeText(getApplicationContext(), "CHANGES SAVED", Toast.LENGTH_SHORT).show();
                tolog = new Intent(Modify_tran.this, Money_mgmt.class);
                startActivity(tolog);

            }
        });
            }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_tran, menu);
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
