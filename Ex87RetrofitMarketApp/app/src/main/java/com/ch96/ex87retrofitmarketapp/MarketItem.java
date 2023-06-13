package com.ch96.ex87retrofitmarketapp;

public class MarketItem {
    //DB의 market 테이블에 column 값들을 저장할 멤버변수(필드), DB의 식별자와 똑같이 (다르게 쓰면 뭘 더 써줘야함)
    String no, name, title, msg, price, image, date;

    public MarketItem(String no, String name, String title, String msg, String price, String image, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.image = image;
        this.date = date;
    }

    public MarketItem() {
    }
}
