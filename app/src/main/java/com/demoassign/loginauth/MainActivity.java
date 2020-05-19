package com.demoassign.loginauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login_btn;
    TextView signup;
    EditText login_mail, login_pass;
    ProgressDialog dialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        signup = findViewById(R.id.login_signin_text);
        signup.setOnClickListener(this);
        login_mail = findViewById(R.id.login_mail_edit);
        login_pass = findViewById(R.id.login_pass_edit);
        dialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {

        int ids = v.getId();

        switch (ids) {

            case R.id.login_signin_text:
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));

            case R.id.login_btn:
                checklogin();

        }

    }


    public void checklogin() {

        String email = login_mail.getText().toString().trim();
        String password = login_pass.getText().toString().trim();

        if (email.length() == 0) {
            login_mail.setError("Please enter your e-mail");
            return;
        }
        if (password.length() == 0) {
            login_pass.setError("Please enter your password");
            return;
        }

        dialog.setMessage("Logging...");
        dialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));


                        } else {

                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "you haven't registered ", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

}
