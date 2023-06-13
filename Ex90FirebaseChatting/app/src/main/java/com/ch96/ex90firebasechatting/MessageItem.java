package com.ch96.ex90firebasechatting;

public class MessageItem {

    //한 번에 넣을때 Firebase가 건들일 수 있도록 반드시 public이어야 함
    public String name;
    public String message;
    public String profileUrl;
    public String time;

    //Firebase에서는 생성자 모두 받는 것 하나, 안 받는 것 하나 필수로 있어야 함
    public MessageItem(String name, String message, String profileUrl, String time) {
        this.name = name;
        this.message = message;
        this.profileUrl = profileUrl;
        this.time = time;
    }

    public MessageItem() {
    }
}
