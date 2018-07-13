package com.safaorhan.reunion.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentReference;
import com.safaorhan.reunion.R;
import com.safaorhan.reunion.adapter.MessageAdapter;

public class MessagingActivity extends AppCompatActivity {


    private static DocumentReference documentReference;

    RecyclerView recyclerView;

    EditText sendEditText;
    ImageView sendButton;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout. );
    }


    public static void setDocumentReference(DocumentReference docRef) {
        documentReference = docRef;
    }

}
