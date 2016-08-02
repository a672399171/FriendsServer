package com.zzuzl.common;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.zzuzl.model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * firebase
 */
public class Firebase {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;

    public Firebase() {
        FirebaseOptions options = null;

        options = new FirebaseOptions.Builder()
                .setServiceAccount(this.getClass().getResourceAsStream("/friends.json"))
                .setDatabaseUrl("https://friends-7d8ec.firebaseio.com/")
                .build();

        if (options != null) {
            FirebaseApp.initializeApp(options);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("server/friends");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public FirebaseAuth getAuth() {
        return firebaseAuth;
    }

    public User searchUser(String schoolNum) {
        DatabaseReference usersRef = ref.child("users");
        // usersRef.child(schoolNum)
        return null;
    }

    public void saveUser(User user) {
        DatabaseReference usersRef = ref.child("users");
        usersRef.child(user.getSchoolNum()).setValue(user);
    }
}
