package com.example.user.work4;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2017-03-30.
 */

public class Table {
    private String name;
    private Calendar reservtime;
    private boolean isEmpty = true;
    ArrayList<Dish> dishList;
    private CardType cardType;
    private long totalPrize;

    public Table(String name){
        this.name = name;
        dishList = DB.dishList();
        totalPrize = 0;
    }

    public void setReserv(ArrayList<Dish> dishList,CardType cardType, int year,int month, int day, int hour, int minute){
        this.reservtime = Calendar.getInstance();
        reservtime.set(year, month, day, hour, minute);
        this.dishList = dishList;
        this.cardType = cardType;
        this.totalPrize = getTotalPrize();
        isEmpty = false;
    }

    public void breakReserv(){
        isEmpty = true;
    }

    public String getReservTime(){
        if(isEmpty()){
            return "unreserved";
        }else{
            return reservtime.toString();
        }
    }

    private long getTotalPrize(){
        float discountVal = 1.0f;
        long totalPrize = 0;
        try{
            switch(cardType.ordinal()){
                case 1:
                    discountVal = 0.9f;
                    break;
                case 2:
                    discountVal = 0.7f;
                    break;
            }
            for(Dish dish : dishList){
                totalPrize += discountVal * dish.getNum() * dish.prize();
            }
            return totalPrize;
        }catch(RuntimeException e){
            return 0;
        }
    }


    public boolean isEmpty() {
        return isEmpty;
    }

    private enum CardType{
        None,
        Membership,
        VIP
    }

    @Override
    public String toString() {
        return name.concat(" Table");
    }


}
