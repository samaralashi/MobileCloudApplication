package com.example.mobilecloudapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.example.mobilecloudapplication.adapter.MyAdapter;
import com.example.mobilecloudapplication.model.TopicsAvailable;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.ArrayList;
import java.util.List;

public class TopicsAvailableActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

//    ArrayList<TopicsAvailable> topicsAvailableArrayList;


    CollectionReference mNotesRef = db.collection("topics");

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_available);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.rv);

//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setAdapter(myAdapter);
        getAllTopics();
    }
    private void getAllTopics(){
        db.collection("topics")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<TopicsAvailable> itemList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            TopicsAvailable item = document.toObject(TopicsAvailable.class);
                            itemList.add(item);
                            Log.e("sara", String.valueOf(item));
                        }
                        // Pass the itemList to the RecyclerView adapter
                        myAdapter = new MyAdapter(db, itemList);
                        recyclerView.setAdapter(myAdapter);
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });





//        db.collection("topics").get()
//
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot documentSnapshots) {
//                        if (documentSnapshots.isEmpty()) {
//                            Log.d("drn", "onSuccess: LIST EMPTY");
//                            return;
//                        } else {
//                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
//                                if (documentSnapshot.exists()) {
//                                    String id = documentSnapshot.getId();
//                                    String topicName = documentSnapshot.getString("topicName");
//                                    TopicsAvailable topicsAvailable= new TopicsAvailable(id, topicName);
//                                    topicsAvailableArrayList.add(topicsAvailable);
//                                    recyclerView.setLayoutManager(layoutManager);
//                                    recyclerView.setHasFixedSize(true);
//                                    recyclerView.setAdapter(myAdapter);
//                                    myAdapter.notifyDataSetChanged();
//                                    Log.e("LogDATA", topicsAvailableArrayList.toString());
//                                }
//                            }
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("LogDATA", "get failed with ");
//                    }
//                });
    }
}