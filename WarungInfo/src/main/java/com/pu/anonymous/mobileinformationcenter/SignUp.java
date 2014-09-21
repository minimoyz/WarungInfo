package com.pu.anonymous.mobileinformationcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Anonymous on 08/09/2014.
 */
public class SignUp extends Activity {

    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //session sign up in here
                startActivity(new Intent(SignUp.this, MainActivity.class));

            }
        });

    }

}
