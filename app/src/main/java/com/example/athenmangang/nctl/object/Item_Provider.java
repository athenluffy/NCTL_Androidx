package com.example.athenmangang.nctl.object;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

public class Item_Provider extends ItemKeyProvider {

    private final List<Select_item> itemList;
    /**
     * Creates a new provider with the given scope.
     *
     * @param scope Scope can't be changed at runtime.
     * @param itemList
     */
    protected Item_Provider(int scope, List<Select_item> itemList) {
        super(scope);
        this.itemList = itemList;
    }

    @Nullable
    @Override
    public Object getKey(int position) {
        return itemList.get(position);
    }

    @Override
    public int getPosition(@NonNull Object key) {
        return itemList.indexOf(key);
    }
}
