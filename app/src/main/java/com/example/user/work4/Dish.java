package com.example.user.work4;

/**
 * Created by user on 2017-03-30.
 */

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
        return "# " + index + " :" + food +
                ", (" + num +
                "개)";
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

    public int prize(){
        return food.getPrize();
    }


}
