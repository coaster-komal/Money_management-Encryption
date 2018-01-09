package lf.pocketnote;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.database.Cursor;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class MainActivity extends ActionBarActivity
{
    EditText et,et1;
    ImageButton reg,log;
    Intent toreg,tolog;
    DBHelper mydb;
    String s="",s1="",pwd="";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    private void addListenerOnButton()
    {
        mydb = new DBHelper(this);
        reg = (ImageButton) findViewById(R.id.register);
        log= (ImageButton) findViewById(R.id.login);
        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toreg = new Intent(MainActivity.this, Register.class);
                startActivity(toreg);
            }

        });
        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                et = (EditText) findViewById(R.id.name);
                et1 = (EditText) findViewById(R.id.pass1);

                s=et.getText().toString();
                s1=et1.getText().toString();
                System.out.println("user "+s);
                System.out.println("pass"+s1);
    try
    {
         Cursor rs=null;
         rs   = mydb.getData(s);
         rs.moveToFirst();
         pwd = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PASSWORD));
        System.out.println("password frm database" + pwd);
        if(pwd.equals("")||s1.equals(""))
            Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
         if (s1.equals(pwd))
         {
             Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
             tolog = new Intent(MainActivity.this, Login.class);
       // run();
        Editor editor = sharedpreferences.edit();
        editor.putString(Name, s);
        editor.commit();

        startActivity(tolog);
    } else
        Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
    }
    catch (Exception e)
    {
         System.out.println(e.getMessage())  ;
        Toast.makeText(getApplicationContext(), "INCORRECT USERNAME", Toast.LENGTH_SHORT).show();

    }
            }

        }
        );

    }
    public void run()
    {
        System.out.println("IN RUN FUNCTION");
        Editor editor = sharedpreferences.edit();
        editor.putString(Name, s);
        editor.commit();
        System.out.println(" after commit IN RUN FUNCTION");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
