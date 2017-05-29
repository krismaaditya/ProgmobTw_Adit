package com.krismaaditya.progmobtw_adit;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.w3c.dom.Text;

import java.util.Random;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    //Twitter consumerKey dan consumerSecret
    private static final String consumerKey = "3o3B1Wo1DDYi05h3lMPUNhb7m";
    private static final String consumerSecret = "tpDrvsb9pdSgFtazrR1HMDuvUwuyTlIrTmG7i2WJvDcZvWEIqk";


    private String angkaAcak;

    private Button ujiButton;
    private TextView angkaTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(consumerKey, consumerSecret);
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());

        ujiButton = (Button)findViewById(R.id.ujiButton);
        angkaTV = (TextView)findViewById(R.id.angkaTV);

        ujiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acak();
            }
        });
    }

    public void acak()
    {
        Random rand = new Random();
        int a = rand.nextInt(100) + 0;
        String n = String.valueOf(a);
        this.angkaAcak = n;
        angkaTV.setText(angkaAcak);

        AlertDialog aD = new AlertDialog.Builder(MainActivity.this).create();
        aD.setTitle("");
        if (a >= 0 && a <=33)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nSepertinya hari ini saya sedang sial.");
        }
        else if (a >= 34 && a <=66)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nHari ini nampaknya biasa saja.");
        }
        else if (a >= 67 && a <=100)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nSaya merasa beruntung hari ini.");
        }
        aD.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

        aD.show();
    }
}
