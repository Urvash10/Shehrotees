package com.example.urvash.shehrotees;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab1Food extends Fragment {


    private RecyclerView.Adapter rvAdapter;
    private ArrayList<Category> category;
    private DatabaseReference databaseReference;
    private DatabaseReference logReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private View rootView;

    public Tab1Food() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab1food, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        LinearLayoutManager rvLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());


        FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.food_item, MenuViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Log.d("shero", "setname"+model.getName());
                Picasso.with(getContext()).load(model.getImage()).into(viewHolder.imageView);
            }
        };
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_food);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(rvLayoutManager);

        recyclerView.setHasFixedSize(true);


        return rootView;
    }

//    private void loadEntries() {
//        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_food);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(rvLayoutManager);
//
//        FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.food_item, MenuViewHolder.class, databaseReference) {
//            @Override
//            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
//                viewHolder.txtMenuName.setText(model.getName());
//                Log.d("shero", "setname"+model.getName());
//                Picasso.with(getContext()).load(model.getImage()).into(viewHolder.imageView);
//            }
//        };
//        recyclerView.setAdapter(adapter);
//
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Category cat = snapshot.getValue(Category.class);
//                    Log.w("shero", cat.getName());
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("shero", "loadLog:onCancelled", databaseError.toException());
//            }
//        });
//    }
}
