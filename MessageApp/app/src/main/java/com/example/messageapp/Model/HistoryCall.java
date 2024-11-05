package com.example.messageapp.Model;

public class HistoryCall {
    private String name; // Tên người gọi
    private String phoneNumber; // Số điện thoại
    private String callTime; // Thời gian cuộc gọi

    public HistoryCall(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.callTime = callTime;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCallTime() {
        return callTime;
    }
}
