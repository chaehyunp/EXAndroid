package com.ch96.ex82httprequest;

public class Item {

    //서버 DB의 board2 테이블의 한줄(row, record) 값을 가지고 있는 데이터용 클래스
    int no;
    String name;
    String msg;
    String date;

    public Item(int no, String name, String msg, String date) {
        this.no = no;
        this.name = name;
        this.msg = msg;
        this.date = date;
    }

    public Item() {
    }
}
