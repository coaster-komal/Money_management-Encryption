package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Enc_dec extends ActionBarActivity {

    ImageButton but,b,b4;
    Intent toenc,todec,tomm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enc_dec);
        addListenerOnButton();
    }
    private void addListenerOnButton()
    {
        but = (ImageButton) findViewById(R.id.enc);
        b= (ImageButton)findViewById(R.id.dec);
        b4= (ImageButton)findViewById(R.id.lo);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                toenc = new Intent(Enc_dec.this, Encryption.class);
                startActivity(toenc);
            }

        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                todec = new Intent(Enc_dec.this, Decryption.class);
                startActivity(todec);
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tomm = new Intent(Enc_dec.this, MainActivity.class);
                startActivity(tomm);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enc_dec, menu);
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
