package com.example.athenmangang.nctl.object;

public class Item {
    private  String Name;
    private String Stock;
    private String curStock;
    private String mPrice;
    private String oPrice;
    public  Item(String name,String stock,String curStock,String mPrice,String oPrice)
    {
        Name=name;
        Stock=stock;
        this.curStock=curStock;
        this.mPrice=mPrice;
        this.oPrice=oPrice;

    }

    public String getName() {
        return Name;
    }

    public String getStock() {
        return Stock;
    }

    public String getCurStock() {
        return curStock;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getoPrice() {
        return oPrice;
    }
}
