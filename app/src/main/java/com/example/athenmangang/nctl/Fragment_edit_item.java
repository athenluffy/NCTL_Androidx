package com.example.athenmangang.nctl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_edit_item extends Fragment {

    private static final String TAG = "Edit_Item";
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Items");
    TextView itemname;
     private static final String KEY_ITEM="Name";
    private static final String KEY_STOCK="Item Stock";
    private static final String KEY_CUR_STOCK="Current Stock";
    private static final String KEY_MPRICE="Member Price";
    private static final String KEY_OPRICE="Other Price";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutInflater=inflater.inflate(R.layout.fragment_edit_item,container,false);

        itemname=layoutInflater.findViewById(R.id.tvItemName);
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        StringBuilder data= new StringBuilder();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String item = documentSnapshot.getString(KEY_ITEM);
                            String stock = documentSnapshot.getString(KEY_STOCK);
                            String cur_stock = documentSnapshot.getString(KEY_CUR_STOCK);
                            String mPrice = documentSnapshot.getString(KEY_OPRICE);
                            String oPrice = documentSnapshot.getString(KEY_MPRICE);
                            data.append(KEY_ITEM + " : ").append(item).append("\n").append(KEY_STOCK).append(" : ").append(stock).append("\n").append(KEY_CUR_STOCK).append(" : ").append(cur_stock).append("\n").append(KEY_MPRICE).append(" : Rs. ").append(mPrice).append("\n").append(KEY_OPRICE).append(" : Rs. ").append(oPrice).append("\n\n\n");

                        }
                        itemname.setMovementMethod(new ScrollingMovementMethod());
                        itemname.setText(data.toString());

                    }
                });



        return layoutInflater;
    }
}
