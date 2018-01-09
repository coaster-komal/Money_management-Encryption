package lf.pocketnote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Encryption extends ActionBarActivity {

    EditText et,et1,et2;
    ImageButton bt;
    Button b;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        bt=(ImageButton)findViewById(R.id.e1);
        b = (Button) findViewById(R.id.send);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.view);
                et2 = (EditText) findViewById(R.id.ph);
                String s="",s1="";
                s = et.getText().toString();
                s1 = et2.getText().toString();
                sendSMSMessage(s,s1);
            } });
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.edit);
                et1 = (EditText) findViewById(R.id.view);
                String s = "", s1 = "";
                s = et.getText().toString();
                System.out.println(s);
                s1 = encryption(s);
                et1.setText(s1);
//perform encryption on s
                //  i = new Intent(Encryption.this, Del_acc.class);
                //  startActivity(i);
            }
        });
    }
    protected void sendSMSMessage(String message,String phoneNo)
    {
        Log.i("Send SMS", "");
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public String encryption(String str)
    {
        String str1="",str2="",str3="";
        Boolean b=false;
        char d='a';
        int i,len,len1;
        len=str.length();
        int a=1;
        for(i=0;i<len;i++)
        {
            char ch=str.charAt(i);
            d=(char)(ch+(2*a));
            a=a*(-1);
            str1=str1+d;
        }
        int mid=len/2;
        for(i=(mid-1);i>=0;i--)
        {
            char ch=str1.charAt(i);
            str2=str2+ch;
        }
        for(i=len-1;i>=(mid);i--)
        {
            char ch=str1.charAt(i);
            str3=str3+ch;
        }
        len1=str3.length();
        for(i=0;i<len1;i++)
        {
            char ch=str3.charAt(i);
            str2=str2+ch;
        }
        System.out.println("encrypted "+str2);
        return str2;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_encryption, menu);
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
