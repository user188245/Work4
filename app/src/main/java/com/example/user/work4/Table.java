package com.example.user.work4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Table {
    private String name;
    private Calendar reservTime;
    private boolean isEmpty = true;
    private ArrayList<Dish> dishList;
    private CardType cardType;
    private long totalPrice;

    public String getName() {
        return name;
    }

    public Calendar getReservTime() {
        return reservTime;
    }

    public ArrayList<Dish> getDishList() {
        return dishList;
    }

    public String cardType() {
        return cardType.name();
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public Table(String name){
        this.name = name;
        dishList = DB.dishDB();
        totalPrice = 0;
    }

    public void setReserv(ArrayList<Dish> dishList,CardType cardType, int year,int month, int day, int hour, int minute){
        this.reservTime = Calendar.getInstance();
        reservTime.set(year, month, day, hour, minute);
        this.dishList = dishList;
        this.cardType = cardType;
        this.totalPrice = getTotalPrize();
        isEmpty = false;
    }

    public void breakReserv(){
        isEmpty = true;
    }

    public String reservTimeToString(){
        if(isEmpty()){
            return "unreserved";
        }else{
            return reservTime.get(Calendar.YEAR) +"/"+
                    reservTime.get(Calendar.MONTH)+"/"+
                    reservTime.get(Calendar.DATE)+", "+
                    reservTime.get(Calendar.HOUR_OF_DAY)+":"+
                    reservTime.get(Calendar.MINUTE);
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
                totalPrize += discountVal * dish.getNum() * dish.price();
            }
            return totalPrize;
        }catch(RuntimeException e){
            return 0;
        }
    }


    public boolean isEmpty() {
        return isEmpty;
    }

    public enum CardType{
        None,
        Membership,
        VIP
    }

    @Override
    public String toString() {
        return name.concat(" Table");
    }


}
