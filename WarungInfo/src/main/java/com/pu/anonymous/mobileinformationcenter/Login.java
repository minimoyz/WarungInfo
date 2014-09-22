package com.pu.anonymous.mobileinformationcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Anonymous on 08/09/2014.
 */

public class Login extends Activity {

    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = (Button) findViewById(R.id.btn_lg_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //session login in here
                startActivity(new Intent(Login.this, MainActivity.class));

            }
        });

        btnSignUp = (Button) findViewById(R.id.btn_lg_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //teat
                // deden ganteng
                //session login in here
                startActivity(new Intent(Login.this, SignUp.class));

            }
        });

    }

    public void onBackPressed() {

    }

}