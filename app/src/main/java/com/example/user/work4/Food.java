package com.example.user.work4;

/**
 * Created by user on 2017-03-30.
 */

public class Food {
    private String name;
    private int prize;

    public Food(String name, int prize) {
        this.name = name;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        return name;
    }


}
