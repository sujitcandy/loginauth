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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    Button register_btn;
    TextView login_user;
    EditText sign_email, sign_pass, sign_username;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        sign_email = findViewById(R.id.signup_mail_edit);
        sign_pass = findViewById(R.id.signup_pass_edit);
        sign_username = findViewById(R.id.signup_user_edit);
        register_btn = findViewById(R.id.register_btn);
        login_user = findViewById(R.id.signup_login_text);

        register_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int ids = v.getId();

        switch (ids) {

            case R.id.register_btn:
                regisuser();
            case R.id.signup_login_text:
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));

        }
    }


    public void regisuser() {

        String username = sign_username.getText().toString().trim();
        String email = sign_email.getText().toString().trim();
        String pass = sign_pass.getText().toString().trim();

        if (username.length() == 0) {
            sign_username.setError("Please enter username");
            return;
        }
        if (email.length() == 0) {
            sign_email.setError("Please enter email");
            return;
        }
        if (pass.length() == 0) {
            sign_pass.setError("Please enter password");
        }
        dialog.setMessage("Registering user");
        dialog.show();

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "successfully Registered", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));

                        } else {

                            dialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "not successful", Toast.LENGTH_LONG).show();

                        }

                    }
                });


    }

}
