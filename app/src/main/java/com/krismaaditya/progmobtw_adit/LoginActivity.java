package com.krismaaditya.progmobtw_adit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

//import javax.security.auth.callback.Callback;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.loginButton);

        testButton = (Button) findViewById(R.id.testButton);

        final ProgressDialog pD = new ProgressDialog(this);
        /*
            ProgressDialog dialog=new ProgressDialog(context);
            dialog.setMessage("message");
            dialog.setCancelable(false);
            dialog.setInverseBackgroundForced(false);
            dialog.show();

            dialog.hide();
             */

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pD.setMessage("Logging you in...");
                pD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pD.setIndeterminate(true);
                pD.show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pD.setMessage("Logging you in...");
                pD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pD.setIndeterminate(false);
                pD.show();

                loginButton.setCallback(new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        //ketika berhasil
                        TwitterSession session = result.data;

                        String username = session.getUserName().toString();
                        long user_id = session.getUserId();
                        String userid = String.valueOf(user_id);
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        pD.dismiss();
                        startActivity(main);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        //ketika gagal
                        String pesanGagal = "LOGIN GAGAL";
                        Log.d("TwitterKit", pesanGagal, exception);
                        Toast.makeText(LoginActivity.this, pesanGagal, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
