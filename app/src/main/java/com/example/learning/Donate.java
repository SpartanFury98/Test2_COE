package com.example.learning;

public class Donate {
    private String Date;
    private String Money;
    public Donate(String date, String money){
        date=Date;
        money=Money;

    }

    public Donate(){}

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }
}
