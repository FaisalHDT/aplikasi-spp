package com.daskull.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference userRefrence,matkulRefrence;
    TextView getEmail,getMatkul;
    FirebaseUser firebaseUser;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getEmail=findViewById(R.id.getEmail);
        getMatkul=findViewById(R.id.getMatkul);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            uid=firebaseUser.getUid();
        }
        getUser();
        getMatkul();
    }

    private void getMatkul() {
        userRefrence= FirebaseDatabase.getInstance().getReference().child("User").child(uid);
       userRefrence.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               getEmail.setText(snapshot.child("email").getValue(String.class));

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }

    private void getUser() {
        userRefrence= FirebaseDatabase.getInstance().getReference().child("matkul");
        userRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getMatkul.setText(snapshot.child("namaMatkul").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
