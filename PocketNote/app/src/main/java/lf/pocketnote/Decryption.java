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

public class Decryption extends ActionBarActivity {

    EditText et,et1;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        addListenerOnButton();
    }
    private void addListenerOnButton() {
        bt=(Button)findViewById(R.id.d1);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                et = (EditText) findViewById(R.id.get);
                et1=(EditText)findViewById(R.id.set);
                String s = "", s1 = "";
                s = et.getText().toString();
                s1 = decryption(s);
                et1.setText(s1);
            }
        });
    }
    public String decryption(String s)
    {
        String str="",str1="",str2="";
        int a,i;
        char d;
        int len=s.length();
        int mid=len/2;
        for(i=(mid-1);i>=0;i--)
        {
            char ch=s.charAt(i);
            str=str+ch;
        }
        for(i=len-1;i>=(mid);i--)
        {
            char ch=s.charAt(i);
            str1=str1+ch;
        }
        int len1=str1.length();
        for(i=0;i<len1;i++)
        {
            char ch=str1.charAt(i);
            str=str+ch;
        }
        System.out.println("before dec"+str);
        a=-1;
        for(i=0;i<len;i++)
        {
            char ch=str.charAt(i);
            d=(char)(ch+(2*a));
            System.out.println("car "+d);
            a=a*(-1);
            str2=str2+d;
        }
        System.out.println("decrypted"+str2);
        return str2;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decryption, menu);
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
