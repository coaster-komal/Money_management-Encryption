package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;

public class Add_tran extends ActionBarActivity {

    EditText et,et1,et2;
    TextView et3;
    Button bt;
    Spinner sp,sp1,sp2,sp3;
    Intent tolog;
    DBHelpertrans mydb;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    Date d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tran);
        addListenerOnButton();
        et3=(TextView) findViewById(R.id.nm);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        et3.setText(sharedpreferences.getString(Name,""));
        System.out.println("ello " + sharedpreferences.getString(Name, ""));

        d=new Date();
        System.out.println(d.getTime());
        Spinner dropdown=(Spinner)findViewById(R.id.sp);
        String[] items=new String[]{"CHOOSE","INCOME","EXPENSE"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
        dropdown.setAdapter(adapter);

        Spinner sp1=(Spinner)findViewById(R.id.day);
        String day[];
        day=new String[31];
        for(int i=0;i<=30;i++)
        {
          day[i]=String.valueOf(i+1);
        }
        ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,day);
        sp1.setAdapter(adapter1);

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
            year[i]=String.valueOf(i+1990);
        }
        ArrayAdapter<String>adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,year);
        sp3.setAdapter(adapter3);
        mydb = new DBHelpertrans(this);
    }
    private void addListenerOnButton() {

        bt = (Button) findViewById(R.id.add);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                et = (EditText) findViewById(R.id.src);
                et1 = (EditText) findViewById(R.id.amt);
                sp = (Spinner) findViewById(R.id.sp);
                sp1 = (Spinner) findViewById(R.id.day);
                sp2 = (Spinner) findViewById(R.id.month);
                sp3 = (Spinner) findViewById(R.id.year);
                String s = "", s1 = "", s3 = "", s4 = "", s5 = "", s6 = "", s7 = "", s8 = "";
                int am;
                s1 = et.getText().toString();
                s = sp.getSelectedItem().toString();
                s3 = et1.getText().toString();
                s4 = sharedpreferences.getString(Name, "");
                s5 = sp1.getSelectedItem().toString();
                s6 = sp2.getSelectedItem().toString();
                s7 = sp3.getSelectedItem().toString();
                boolean flag = true;
                if (s == "CHOOSE") {
                    Toast.makeText(getApplicationContext(), "CHOOCE TRANSACTION TYPE", Toast.LENGTH_SHORT).show();
                    flag = false;

                }
                if (s == "" || s1 == ""  || s4 == "" || s3 == "" || s5 == "" || s6 == "" || s7 == "") {
                    Toast.makeText(getApplicationContext(), "FILL ALL FIELDS", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                System.out.println("month jj  "+s6);
                System.out.println("date jj "+s5);


                if ((s6.equals("2")))
                {
                    System.out.println("month "+s6);
                    System.out.println("date "+s5);
                    if(s5.equals("30")) {
                        System.out.println("dt "+s5);
                        Toast.makeText(getApplicationContext(), "ENTERED DATE DOES NOT EXIST", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }
                }

                 if(s6.equals("4")||s6.equals("6")||s6.equals("9")||s6.equals("11"))
                 {
                     if(s5.equals("31"))
                     {
                         Toast.makeText(getApplicationContext(), "ENTERED DATE DOES NOT EXIST", Toast.LENGTH_SHORT).show();
                         flag = false;
                     }
                 }
               if(flag)
                    {
                    s8=s5+"/"+s6+"/"+s7;
                    am=Integer.parseInt(s3);
                    try {
                        mydb.inserttransaction(s4, s, s1, s3, s8);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    Toast.makeText(getApplicationContext(), "SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show();
                    tolog = new Intent(Add_tran.this, Money_mgmt.class);
                    startActivity(tolog);
                }

            }
        });
    }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_add_tran, menu);
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
