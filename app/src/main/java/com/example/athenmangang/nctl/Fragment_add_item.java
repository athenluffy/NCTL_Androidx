package com.example.athenmangang.nctl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.facebook.ads.*;
import java.util.HashMap;
import java.util.Map;


public class Fragment_add_item extends Fragment {
    String TAG="add_item";
    FirebaseFirestore db;
    TextInputLayout itemName,itemStock, memberPrice,otherPrice;
    Button save;
    //LinearLayout fbAd;
    private static final String KEY_ITEM="Name";
    private static final String KEY_STOCK="Item Stock";
    private static final String KEY_CUR_STOCK="Current Stock";
    private static final String KEY_MPRICE="Member Price";
    private static final String KEY_OPRICE="Other Price";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutInflater=inflater.inflate(R.layout.fragment_add_item,container,false);
            MobileAds.initialize(getContext());
            //intialize admob
            AdView myAd;
            myAd = layoutInflater.findViewById(R.id.adTestAd);
            AdRequest adRequest=new AdRequest.Builder().build();
            //initialize fb ad
        /*com.facebook.ads.AdView adview;
        adview=new com.facebook.ads.AdView(getContext(),"942701562589590_942703699256043",AdSize.BANNER_HEIGHT_50);
        fbAd=layoutInflater.findViewById(R.id.fbAd);
        fbAd.addView(adview);
        adview.loadAd();*/

        myAd.loadAd(adRequest);
           itemName=layoutInflater.findViewById(R.id.txtItemName);
           itemStock=layoutInflater.findViewById(R.id.txtItemStock);
           memberPrice =layoutInflater.findViewById(R.id.txtMemberPrice);
           otherPrice=layoutInflater.findViewById(R.id.txtOtherPrice);
           save=layoutInflater.findViewById(R.id.btnSave);
        Log.d(TAG,"add_item");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=itemName.getEditText().getText().toString();
                String stock=itemStock.getEditText().getText().toString();
                String txtotherPrice= memberPrice.getEditText().getText().toString();
                String txtmemberPrice=otherPrice.getEditText().getText().toString();
                if(name!=null && txtotherPrice!=null && stock!=null && txtmemberPrice!=null)
                {
                    db=FirebaseFirestore.getInstance();
                    Map<String,Object> newItem= new  HashMap<>();
                    newItem.put(KEY_ITEM,name);
                    newItem.put(KEY_STOCK,stock);
                        newItem.put(KEY_CUR_STOCK,stock);
                    newItem.put(KEY_MPRICE,txtmemberPrice);
                    newItem.put(KEY_OPRICE,txtotherPrice);
                    //add with automatic document id
                    /*
                    db.collection("Items").add(newItem).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            itemName.getEditText().setText("");
                            memberPrice.getEditText().setText("");
                            itemStock.getEditText().setText("");
                            Toast.makeText(getContext(), "successfully Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            Log.d("Failed",e.getMessage());
                        }
                    });*/
                     //add with a your document id
                    db.collection("Items").document(name).set(newItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            itemName.getEditText().setText("");
                            memberPrice.getEditText().setText("");
                            otherPrice.getEditText().setText("");
                            itemStock.getEditText().setText("");
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Failed",e.getMessage());
                        }
                    });
                }

            }
        });


        return layoutInflater;
    }
}
