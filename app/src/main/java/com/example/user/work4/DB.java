package com.example.user.work4;

import java.util.ArrayList;

/**
 * Created by user on 2017-04-02.
 */

public class DB {
    private static ArrayList<Dish> dishList = dishDB();
    private static ArrayList<Table> tableList = tableDB();

    private static ArrayList<Dish> dishDB(){
        ArrayList<Dish> dishList = new ArrayList<Dish>();
        dishList.add(new Dish(0,new Food("피자", 12000)));
        dishList.add(new Dish(1,new Food("스파게티", 10000)));
        dishList.add(new Dish(2,new Food("짜장면", 5000)));
        dishList.add(new Dish(3,new Food("볶음밥", 8000)));
        dishList.add(new Dish(4,new Food("치킨", 12500)));
        return dishList;
    }
    private static ArrayList<Table> tableDB(){
        ArrayList<Table> tableList = new ArrayList<Table>();
        tableList.add(new Table("사과"));
        tableList.add(new Table("포도"));
        tableList.add(new Table("키위"));
        tableList.add(new Table("자몽"));
        return tableList;
    }

    public static ArrayList<Dish> dishList(){
        return dishList;
    }

    public static ArrayList<Table> tableList(){
        return tableList;
    }



}

