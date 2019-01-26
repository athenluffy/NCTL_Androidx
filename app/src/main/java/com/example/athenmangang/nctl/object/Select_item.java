package com.example.athenmangang.nctl.object;

public class Select_item {
    private String itemName;

    private String curStock;
    private String price;


    public Select_item(String itemName, String curStock, String price ) {
        this.itemName=itemName;

        this.curStock=curStock;
        this.price = price;


    }

    public String getItemName() {
        return itemName;
    }

    public String getCurStock() {
        return curStock;
    }

    public String getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCurStock(String curStock) {
        this.curStock = curStock;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
