package com.example.fitnessclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessclub.Trainee.TraineeLog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    TextView signUp;
    TextView logInAdmin;
    //TextView logInTrainee;

    EditText email, password;
    TextView sign_up_option;
    FirebaseAuth m_firebaseAuth;

    Session session;

    private FirebaseAuth.AuthStateListener m_AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        logInAdmin = findViewById(R.id.login_admin);
        //logInTrainee = findViewById(R.id.login_trainee);
        sign_up_option = findViewById(R.id.sign_up);

        session = new Session(this);

        m_firebaseAuth =  FirebaseAuth.getInstance();

        m_AuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser m_FireaseUser = m_firebaseAuth.getCurrentUser();

                if (m_FireaseUser != null){
                    Toast.makeText(LogIn.this, "You are logged in", Toast.LENGTH_SHORT).show();

//                    if (session.getUserStatus().equals("admin")){
//                        Intent intent = new Intent(LogIn.this,AdminLog.class);
//                        startActivity(intent);
//                    }
//                    else {
                        Intent intent = new Intent(LogIn.this, TraineeLog.class);
                        startActivity(intent);
                    //}

                }
                else{

                    Toast.makeText(LogIn.this, "Please Login", Toast.LENGTH_SHORT).show();
                }

            }
        };


        logInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String temp_email = email.getText().toString();
                final String temp_password = password.getText().toString();

                if (temp_email.isEmpty()){
                    email.setError("please enter the email");
                    email.requestFocus();
                }

                if (temp_password.isEmpty()){
                    password.setError("please enter the password");
                    password.requestFocus();
                }
                else if (temp_email.isEmpty() && temp_password.isEmpty()){
                    Toast.makeText(LogIn.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(temp_email.isEmpty() && temp_password.isEmpty())){
                    m_firebaseAuth.signInWithEmailAndPassword(temp_email,temp_password).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LogIn.this, "LogIn error, Please try again", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                if(temp_email.equals("osamazafar97@gmail.com") && temp_password.equals("25111997")){
                                    session.setUserStatus("admin");
                                    session.setEmail(temp_email);
                                    Intent intent = new Intent(LogIn.this,AdminLog.class);
                                    startActivity(intent);
                                }
                                else{
                                    session.setUserStatus("trainee");
                                    Intent intent = new Intent(LogIn.this,TraineeLog.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LogIn.this, "Error occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        logInTrainee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String temp_email = email.getText().toString();
//                String temp_password = password.getText().toString();
//
//                if (temp_email.isEmpty()){
//                    email.setError("please enter the email");
//                    email.requestFocus();
//                }
//
//                if (temp_password.isEmpty()){
//                    password.setError("please enter the password");
//                    password.requestFocus();
//                }
//                else if (temp_email.isEmpty() && temp_password.isEmpty()){
//                    Toast.makeText(LogIn.this, "Fields are empty", Toast.LENGTH_SHORT).show();
//                }
//                else if (!(temp_email.isEmpty() && temp_password.isEmpty())){
//                    m_firebaseAuth.signInWithEmailAndPassword(temp_email,temp_password).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()){
//                                Toast.makeText(LogIn.this, "LogIn error, Please try again", Toast.LENGTH_SHORT).show();
//
//                            }
//                            else {
//                                session.setUserStatus("trainee");
//                                Intent intent = new Intent(LogIn.this,TraineeLog.class);
//                                startActivity(intent);
//                            }
//                        }
//                    });
//                }
//                else{
//                    Toast.makeText(LogIn.this, "Error occured!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
        sign_up_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        m_firebaseAuth.addAuthStateListener(m_AuthStateListener);

    }

}