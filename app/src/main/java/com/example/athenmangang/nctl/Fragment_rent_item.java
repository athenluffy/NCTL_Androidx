package com.example.athenmangang.nctl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.athenmangang.nctl.object.Item;
import com.example.athenmangang.nctl.object.ItemAdapter;
import com.example.athenmangang.nctl.object.Select_item;
import com.example.athenmangang.nctl.object.Select_item_Adapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Fragment_rent_item extends Fragment {
    FloatingActionButton checkout;
    ArrayList<Select_item> select_items=new ArrayList<>();
    RecyclerView select_item_recyclerView;
    Select_item_Adapter select_item_adapter;
    FirebaseFirestore db=FirebaseFirestore.getInstance();


    CollectionReference notebookRef = db.collection("Items");
    String PRICE_TAG="";
    private static final String KEY_ITEM="Name";
    private static final String KEY_CUR_STOCK="Current Stock";
    private static final String TAG="Rent Item";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layoutInflater=inflater.inflate(R.layout.fragment_rent_item,container,false);
        //Select User type

        checkout=layoutInflater.findViewById(R.id.fabCheckout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Clicked fab",Snackbar.LENGTH_SHORT).show();
            }
        });
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Choose Type").setCancelable(false).
        setMessage("Are you a Member or a Non-member?").
                setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PRICE_TAG ="Member Price";
                        Toast.makeText(getContext(), "Member", Toast.LENGTH_SHORT).show();
                        select_item_recyclerView=layoutInflater.findViewById(R.id.rvSelectItem);
                        select_item_recyclerView.setHasFixedSize(true);
                        select_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        notebookRef.get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                            String item=documentSnapshot.getString(KEY_ITEM);
                                            String cur_stock ="Current Stock : " +documentSnapshot.getString(KEY_CUR_STOCK);
                                            String Price =documentSnapshot.getString(PRICE_TAG);

                                            Log.d(TAG,"item :"+item);
                                            //append each item to list
                                            select_items.add(new Select_item(item,cur_stock,Price));

                                        }
                                        //initialize adapter
                                        select_item_adapter =new Select_item_Adapter(getContext(),select_items);
                                        //assign adapter to firebase
                                        select_item_recyclerView.setAdapter(select_item_adapter);

                                    }
                                });

                    }
                }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Non-Member", Toast.LENGTH_SHORT).show();
                PRICE_TAG ="Other Price";
                select_item_recyclerView=layoutInflater.findViewById(R.id.rvSelectItem);
                select_item_recyclerView.setHasFixedSize(true);
                select_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                notebookRef.whereGreaterThan(KEY_CUR_STOCK,0).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    String item=documentSnapshot.getString(KEY_ITEM);

                                    String cur_stock ="Current Stock : " +documentSnapshot.getString(KEY_CUR_STOCK);
                                    String Price =documentSnapshot.getString(PRICE_TAG);

                                    Log.d(TAG,"item :"+Price);
                                    //append each item to list
                                    select_items.add(new Select_item(item,cur_stock,Price));

                                }
                                //initialize adapter
                                select_item_adapter =new Select_item_Adapter(getContext(),select_items);
                                //assign adapter to firebase
                                select_item_recyclerView.setAdapter(select_item_adapter);

                            }
                        });
            }
        });
builder.show();

        return layoutInflater;
    }
}
