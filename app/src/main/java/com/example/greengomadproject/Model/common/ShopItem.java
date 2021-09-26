package com.example.greengomadproject.Model.common;

public class ShopItem {
        String category;
        int quantity;
        String unit;

    public ShopItem() {
    }

    public ShopItem(String category, int quantity, String unit) {
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
