package com.example.foodorder.Food_model;

public class OrderModel {
    int pic;
    String name;
    String ordernumber;
    String price;

//    public OrderModel(int pic, String name, String ordernumber, String price) {
//        this.pic = pic;
//        this.name = name;
//        this.ordernumber = ordernumber;
//        this.price = price;
//    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
