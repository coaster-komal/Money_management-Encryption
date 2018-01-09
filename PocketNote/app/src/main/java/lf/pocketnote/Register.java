package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class Register extends ActionBarActivity {
        EditText et,et1,et2,et3;
        ImageButton img;
        Intent tolog;
     DBHelpertrans mydbtran;
        DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addListenerOnButton();


    }

    private void addListenerOnButton()
    {
        mydbtran = new DBHelpertrans(this);
         mydb = new DBHelper(this);
        img = (ImageButton) findViewById(R.id.reg);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.uname);
                et1 = (EditText) findViewById(R.id.pass);
                et2 = (EditText) findViewById(R.id.phone);
                et3 = (EditText) findViewById(R.id.cnfrmpass);
                System.out.println("name"+et);
                System.out.println("pass"+et1);
                System.out.println("pone"+et2);
                // et3=(EditText) findViewById(R.id.uname);
                String s = "", s1 = "", s2 = "",s3="";
                s = et.getText().toString();
//                mydbtran.insertinitial(s);
                s1 = et1.getText().toString();
                s2 = et2.getText().toString();
                s3 = et3.getText().toString();
                System.out.println("string name"+s);
                System.out.println("string pass" + s1);
                System.out.println("string confirm pass"+s3);
                System.out.println("string pone" + s2);
                if (s == " " || s1 == " " || s2 == " ")
                    Toast.makeText(getApplicationContext(), "FILL ALL                                                                                                                                                           FIELDS", Toast.LENGTH_SHORT).show();
                else if(!s3.equals(s1))
                    Toast.makeText(getApplicationContext(), "PASSWORD DOES NOT MATCHED", Toast.LENGTH_SHORT).show();
                else if(s2.length()!=10)
                    Toast.makeText(getApplicationContext(), "ENTER CORRECT PHONE NUMBER", Toast.LENGTH_SHORT).show();
                else
                {
                    mydb.insertContact(s, s1, s2);
                    mydbtran.insertinitial(s);
                    Toast.makeText(getApplicationContext(), "SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show();
                    tolog = new Intent(Register.this, MainActivity.class);
                    startActivity(tolog);
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
