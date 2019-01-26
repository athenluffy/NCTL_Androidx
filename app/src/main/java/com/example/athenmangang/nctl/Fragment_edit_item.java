package com.example.athenmangang.nctl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.athenmangang.nctl.object.Item;
import com.example.athenmangang.nctl.object.ItemAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Fragment_edit_item extends Fragment {

    private static final String TAG = "Edit_Item";
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Items");
     private static final String KEY_ITEM="Name";
    private static final String KEY_STOCK="Item Stock";
    private static final String KEY_CUR_STOCK="Current Stock";
    private static final String KEY_MPRICE="Member Price";
    private static final String KEY_OPRICE="Other Price";
     ItemAdapter itemAdapter;
     ArrayList<Item> items=new ArrayList<>();
     RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutInflater=inflater.inflate(R.layout.fragment_edit_item,container,false);
        //find recycler view
        recyclerView=layoutInflater.findViewById(R.id.edit_item_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //get items from firebase
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String item="Item Name : "+documentSnapshot.getString(KEY_ITEM);
                            String stock ="Stock : " +documentSnapshot.getString(KEY_STOCK);
                            String cur_stock ="Current Stock : " +documentSnapshot.getString(KEY_CUR_STOCK);
                            String oPrice ="Other Price : Rs." +documentSnapshot.getString(KEY_OPRICE);
                            String mPrice = "Member Price : Rs."+documentSnapshot.getString(KEY_MPRICE);
                            Log.d(TAG,"item :"+item);
                            //append each item to list
                            items.add(new Item(item,stock,cur_stock,mPrice,oPrice));

                        }
                        //initialize adapter
                        itemAdapter=new ItemAdapter(getContext(),items);
                        //assign adapter to firebase
                        recyclerView.setAdapter(itemAdapter);

                    }
                });



        return layoutInflater;
    }
}
