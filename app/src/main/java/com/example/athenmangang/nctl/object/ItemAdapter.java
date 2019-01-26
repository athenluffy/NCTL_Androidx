package com.example.athenmangang.nctl.object;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athenmangang.nctl.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating cardview
        View v= LayoutInflater.from(context).inflate(R.layout.cardview_item_list,viewGroup,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
    Item item=items.get(i);
         String itemName=item.getItemName();
         String stock=item.getStock();
         String curStock=item.getCurStock();
         String memberPrice=item.getMemberPrice();
         String otherPrice=item.getOtherPrice();


         itemViewHolder.name.setText(itemName);
         itemViewHolder.count.setText(stock);
         itemViewHolder.curCount.setText(curStock);
         itemViewHolder.oPrice.setText(otherPrice);
         itemViewHolder.mPrice.setText(memberPrice);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
//initializing text view in recycler view after inflating from cardview
         TextView name,count,curCount,mPrice,oPrice;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvItemName);
            count=itemView.findViewById(R.id.tvTotalStock);
            curCount=itemView.findViewById(R.id.tvCurrentStock);
            mPrice=itemView.findViewById(R.id.tvMemberPrice);
            oPrice=itemView.findViewById(R.id.tvOtherPrice);

        }
    }

}
