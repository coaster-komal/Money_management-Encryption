package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Money_mgmt extends ActionBarActivity {
    Button b,b1,b2,b3;
    ImageButton b4;
    Intent toadd,tomod,todel,toview,tomm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_mgmt);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        b = (Button) findViewById(R.id.add);
        b1=(Button)findViewById(R.id.mod);
        b2=(Button)findViewById(R.id.del);
        b3=(Button)findViewById(R.id.view1);
        b4=(ImageButton)findViewById(R.id.lo);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toadd = new Intent(Money_mgmt.this, Add_tran.class);
                startActivity(toadd);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomod = new Intent(Money_mgmt.this, Modify_tran.class);
                startActivity(tomod);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                todel = new Intent(Money_mgmt.this, Delete_tran.class);
                startActivity(todel);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toview = new Intent(Money_mgmt.this, View_tran.class);
                startActivity(toview);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomm = new Intent(Money_mgmt.this, MainActivity.class);
                startActivity(tomm);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_money_mgmt, menu);
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
