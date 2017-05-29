package com.krismaaditya.progmobtw_adit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

//import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {
    private static final String CONSUMER_KEY = "3o3B1Wo1DDYi05h3lMPUNhb7m";
    private static final String CONSUMER_SECRET = "tpDrvsb9pdSgFtazrR1HMDuvUwuyTlIrTmG7i2WJvDcZvWEIqk";

    private TwitterLoginButton loginButton;
    //dummy button buat ngetest doang
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*config ini harus ditaruh di line tepat sesudah super.oncreate
        / dan sebelum
        / setcontentview baru tombol twitter loginnya bisa jalan
        */

        //config kalau pakai fabric
        //TwitterAuthConfig authConfig = new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET);
        //Fabric.with(this, new Twitter(authConfig));

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.loginButton);
        testButton = (Button) findViewById(R.id.testButton);

        final ProgressDialog pD = new ProgressDialog(this);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                String username = session.getUserName();
                long id = session.getUserId();
                String user_id = String.valueOf(id);

                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                Toast.makeText(LoginActivity.this,
                        "Token : "+token
                                +"\nSecret : "+secret, Toast.LENGTH_LONG).show();

                Toast.makeText(LoginActivity.this,
                        "Username : "+username
                                +"\nUser ID : "+user_id, Toast.LENGTH_LONG).show();
                Intent menu = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(menu);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_LONG).show();
            }
        });

        //buat ngetest doang ini
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pD.setMessage("Logging you in...");
                pD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pD.setIndeterminate(true);
                pD.show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
