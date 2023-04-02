package com.georgeifrim.AtmMachineDemo.repositories;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class AtmRepository {
    Map<Denominations, Integer> atmStock = new TreeMap<>();

    private AtmRepository(){
        atmStock.put(Denominations.ONE_HUNDRED,50);
        atmStock.put(Denominations.FIFTY,50);
        atmStock.put(Denominations.TEN,100);
        atmStock.put(Denominations.FIVE,100);
        atmStock.put(Denominations.ONE,100);
    }

    public Map<Denominations, Integer> withdrawAmount(Map<Denominations, Integer> withdrawal){
        for(Map.Entry<Denominations, Integer> entry : withdrawal.entrySet()){
            for(Map.Entry<Denominations, Integer> stockentry : atmStock.entrySet()){
                if(stockentry.getKey().equals(entry.getKey())){
                    atmStock.replace(stockentry.getKey(), stockentry.getValue() - entry.getValue());
                }
            }
        }
        return atmStock;
    }

    public Map<Denominations, Integer> currentStock(){
        return atmStock;
    }



}
