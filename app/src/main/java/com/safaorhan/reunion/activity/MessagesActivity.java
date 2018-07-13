package com.safaorhan.reunion.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentReference;
import com.safaorhan.reunion.FirestoreHelper;
import com.safaorhan.reunion.R;
import com.safaorhan.reunion.adapter.MessageAdapter;
import com.safaorhan.reunion.model.Message;

public class MessagesActivity extends AppCompatActivity {


    private static DocumentReference documentReference;

    RecyclerView recyclerView;

    EditText messagedEditText;
    ImageView sendButton;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_messages );

        messagedEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);
        messageAdapter = MessageAdapter.get(documentReference);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirestoreHelper.sendMessage(messagedEditText.getText().toString(), documentReference);
                messagedEditText.setText("");
            }
        });
    }


    public static void setDocumentReference(DocumentReference docRef) {
        documentReference = docRef;
    }

    @Override
    protected void onStart() {
        super.onStart();
        messageAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        messageAdapter.stopListening();
    }

}
