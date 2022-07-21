package com.yoon.boundNotBoundTest.payment.domain;

public class Calculator { // TODO : Command 객체에 있는 클럽의 가격으로 복합결제수단+상품을 함께 판단해 액수를 반환
    int amountCommunityPrice;
    int communityNoExchangeablePoint;

    public Calculator(int i, int noExchangeablePoint) {
        this.amountCommunityPrice = i;
        this.communityNoExchangeablePoint = noExchangeablePoint;
    }

    public int getAmountCommunityPrice() {
        return amountCommunityPrice;
    }

    public int getCommunityNoExchangeablePoint() {
        return communityNoExchangeablePoint;
    }
}