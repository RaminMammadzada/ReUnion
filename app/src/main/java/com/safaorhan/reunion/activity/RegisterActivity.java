package com.safaorhan.reunion.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.safaorhan.reunion.R;
import com.safaorhan.reunion.model.User;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity{


    private static final String TAG = RegisterActivity.class.getSimpleName();
    EditText nameEdit;
    EditText surnameEdit;
    EditText emailEdit;
    EditText passwordEdit;
    Button registerButton;

    boolean isTryingToRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_register);

        nameEdit = findViewById( R.id.nameEdit );
        surnameEdit = findViewById( R.id.surnameEdit );
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTryingToRegister) {
                    tryToRegister();
                }
            }
        });
    }

    private void tryToRegister() {
        String name = nameEdit.getText().toString();
        String surname = surnameEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        isTryingToRegister = true;

        User user = new User();
        user.setName( name );
        user.setSurname( surname );
        user.setEmail( email );
        user.setPassword( password );

        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword( user.getEmail(), user.getPassword() )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Check your connection..", Toast.LENGTH_SHORT).show();
                        isTryingToRegister = false;
                    }
                } );

    }

    public void LoginToAccount(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
