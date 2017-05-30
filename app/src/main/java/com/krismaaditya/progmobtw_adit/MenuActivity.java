package com.krismaaditya.progmobtw_adit;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;
import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private String angkaAcak;
    private String sialText = "Sepertinya hari ini saya sedang sial.";
    private String biasaText = "Hari ini nampaknya biasa saja.";
    private String beruntungText = "Saya merasa beruntung hari ini.";
    private Button ujiButton;
    private TextView angkaTV;
    private TextView usernameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        long id = session.getUserId();
        String user_id = String.valueOf(id);
        String username = session.getUserName();

        ujiButton = (Button)findViewById(R.id.ujiButton);
        angkaTV = (TextView)findViewById(R.id.angkaTV);
        usernameTV = (TextView)findViewById(R.id.usernameTV);

        usernameTV.setText(username);

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

        AlertDialog aD = new AlertDialog.Builder(MenuActivity.this).create();
        aD.setTitle("");
        if (a >= 0 && a <=33)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nSepertinya hari ini saya sedang sial.");

            /*Uri sialImageUri = FileProvider.getUriForFile(MenuActivity.this,
                    BuildConfig.APPLICATION_ID + ".file_provider",
                    new File("/res/drawable/sial.png"));*/

            Uri sialImagepath = Uri.parse("android.resource://com.krismaaditya.progmobtw_adit/" + R.drawable.sial);

            TweetComposer.Builder builder = new TweetComposer.Builder(this)
                    .text(sialText)
                    .image(sialImagepath);
            builder.show();
        }
        else if (a >= 34 && a <=66)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nHari ini nampaknya biasa saja.");

            Uri biasaImagepath = Uri.parse("android.resource://com.krismaaditya.progmobtw_adit/" + R.drawable.biasa);

            TweetComposer.Builder builder = new TweetComposer.Builder(this)
                    .text(biasaText)
                    .image(biasaImagepath);
            builder.show();

        }
        else if (a >= 67 && a <=100)
        {
            aD.setMessage("Angka kamu : "+angkaAcak
                    +"\nSaya merasa beruntung hari ini.");

            Uri beruntungImagepath = Uri.parse("android.resource://com.krismaaditya.progmobtw_adit/" + R.drawable.beruntung);

            TweetComposer.Builder builder = new TweetComposer.Builder(this)
                    .text(beruntungText)
                    .image(beruntungImagepath);
            builder.show();
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
