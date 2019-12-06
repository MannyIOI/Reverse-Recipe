package com.ethiopia.addisababa.manny.reverserecipe2;

public class Recipe{
    private int _id;
    private String _productName;

    private Recipe(){
    }

    public Recipe(String productName) {
        this._productName = productName;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
