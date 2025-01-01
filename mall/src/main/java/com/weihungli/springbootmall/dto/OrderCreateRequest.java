package com.weihungli.springbootmall.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderCreateRequest {
    @NotEmpty
    private List<BuyItems> buyItemsList;

    public List<BuyItems> getBuyItemsList() {
        return buyItemsList;
    }

    public void setBuyItemsList(List<BuyItems> buyItemsList) {
        this.buyItemsList = buyItemsList;
    }
}
