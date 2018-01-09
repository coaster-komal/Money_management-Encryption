package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Login extends ActionBarActivity {
     ImageButton but,bt1;
    ImageButton b1,b2,b3,b4;
    Intent todel;
    Intent toenc,tomm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        but=(ImageButton) findViewById (R.id.del);

        b1=(ImageButton) findViewById(R.id.enc);
        b2=(ImageButton)findViewById(R.id.mm);
        b3=(ImageButton)findViewById(R.id.search);
        b4=(ImageButton)findViewById(R.id.lo);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                todel = new Intent(Login.this, Del_acc.class);
                startActivity(todel);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toenc = new Intent(Login.this, Enc_dec.class);
                startActivity(toenc);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomm = new Intent(Login.this, Money_mgmt.class);
                startActivity(tomm);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomm = new Intent(Login.this, View_acc.class);
                startActivity(tomm);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomm = new Intent(Login.this, MainActivity.class);
                startActivity(tomm);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
