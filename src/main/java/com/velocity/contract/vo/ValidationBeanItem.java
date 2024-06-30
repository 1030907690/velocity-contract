package com.velocity.contract.vo;


import jakarta.validation.constraints.NotNull;


public class ValidationBeanItem {
    @NotNull(message = "null")
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "ValidationBeanItem{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}
