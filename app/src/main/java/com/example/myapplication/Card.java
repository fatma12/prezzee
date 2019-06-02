package com.example.myapplication;

public class Card {
    private int id;
    private String name ;
    private String currency;
    private int value;
    private String theme;

    public Card() {
    }

    public Card(int id,String name,int value, String currency, String theme) {

        this.name = name;
        this.currency = currency;
        this.value = value;
        this.theme = theme;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }


    public String getTheme() {
        return theme;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
