package com.example.user.work4;

public final class Dish {
    private Food food;
    private int index;
    private int num;

    public Dish(int index, Food food) {
        this.food = food;
        this.index = index;
        this.num = 0;
    }

    @Override
    public String toString() {
        return String.format("#%-3d : %-10s %d개/판매가 : %d원",index,food,num,price());
    }

    public int getIndex() {
        return index;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public String name(){
        return food.getName();
    }

    public int price(){
        return food.getPrice();
    }


}
