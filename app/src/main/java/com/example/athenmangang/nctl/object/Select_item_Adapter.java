package com.example.athenmangang.nctl.object;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.athenmangang.nctl.R;

import java.util.ArrayList;

public class Select_item_Adapter extends RecyclerView.Adapter<Select_item_Adapter.ItemViewHolder> {
    private Context curContext;
    private ArrayList<Select_item> select_items;

    public Select_item_Adapter(Context context,ArrayList<Select_item> select_items) {
        curContext=context;
        this.select_items= select_items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(curContext).inflate(R.layout.choose_item_card_view,viewGroup,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {
    Select_item item=select_items.get(i);
    String item_name=item.getItemName();
    String stock=item.getCurStock();
    final String price=item.getPrice();
    itemViewHolder.itemCkecked.setText(item_name);
    itemViewHolder.price.setText("Price : Rs."+price);
    itemViewHolder.stock.setText(stock);
    itemViewHolder.qty.getEditText().addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            itemViewHolder.totalPrice.setText("Total price: Rs.0");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String no=s.toString();
            if (s.length()==0)
            {
                itemViewHolder.totalPrice.setText("Total Price : Rs.0");

            }
            else {
                itemViewHolder.totalPrice.setText("Total Price : Rs."+Integer.parseInt(price)*Integer.parseInt(no));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    }

    @Override
    public int getItemCount() {
        return select_items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCkecked;
        TextView stock,price,totalPrice;
        TextInputLayout qty;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCkecked=itemView.findViewById(R.id.optCheckBox);
            stock=itemView.findViewById(R.id.tvRentItemStock);
            price=itemView.findViewById(R.id.tvRentItemPrice);
            totalPrice=itemView.findViewById(R.id.tvTotalPrice);
            qty=itemView.findViewById(R.id.txtRentQty);

        }
    }
}
