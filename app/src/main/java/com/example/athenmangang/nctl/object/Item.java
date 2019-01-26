package com.example.athenmangang.nctl.object;

public class Item {
    private String itemName;
    private String stock;
    private String curStock;
    private String memberPrice;
    private String otherPrice;

    public Item(String itemName, String stock,String curStock, String memberPrice, String otherPrice ) {
    this.itemName=itemName;
    this.stock=stock;
    this.curStock=curStock;
    this.memberPrice=memberPrice;
    this.otherPrice=otherPrice;

    }

    public String getItemName() {
        return itemName;
    }

    public String getStock() {
        return stock;
    }

    public String getCurStock() {
        return curStock;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setCurStock(String curStock) {
        this.curStock = curStock;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public void setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice;
    }

    public String getOtherPrice() {
        return otherPrice;
    }
}