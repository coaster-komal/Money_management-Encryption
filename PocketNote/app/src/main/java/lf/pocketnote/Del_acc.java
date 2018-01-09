package lf.pocketnote;

import android.database.Cursor;
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

public class Del_acc extends ActionBarActivity {

    EditText et,et1;
    Intent tolog;
    DBHelper mydb;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_acc);
        addListenerOnButton();
    }
    private void addListenerOnButton()
    {
        mydb = new DBHelper(this);
        bt = (Button) findViewById(R.id.delac);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.uname);
                et1 = (EditText) findViewById(R.id.pass2);
                String s = "", s1 = "",pwd;
                s = et.getText().toString();
                s1 = et1.getText().toString();
                Cursor rs = mydb.getData(s);
                rs.moveToFirst();
                pwd = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PASSWORD));
                System.out.println("bring "+pwd);
                if (s1.equals(pwd)) {
                    int a=7;
                    a=mydb.deleteContact(s);
                    System.out.println("delete "+a);
                    Toast.makeText(getApplicationContext(), "SUCCESSFULLY DELETED", Toast.LENGTH_SHORT).show();
                    tolog = new Intent(Del_acc.this, MainActivity.class);
                    startActivity(tolog);
                }
                else
                    Toast.makeText(getApplicationContext(), "INCORRECT USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
            }
        });
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_del_acc, menu);
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
