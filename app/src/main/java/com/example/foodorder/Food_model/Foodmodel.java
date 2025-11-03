package com.example.foodorder.Food_model;

public class Foodmodel {

    int  pic;
    String name,price,discription;

    public Foodmodel(int pic,String name,String price, String discription){
        this.pic=pic;
        this.name=name;
        this.price=price;
        this.discription=discription;
    }

    public int getPic(){
        return pic;
    }

    public void setPic(int pic){
        this.pic=pic;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price=price;
    }
    public String getDiscription(){
        return discription;
    }

    public void setDiscription(String discription){
        this.discription=discription;
    }



}
