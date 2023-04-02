package com.georgeifrim.AtmMachineDemo.repositories;

import com.georgeifrim.AtmMachineDemo.services.AtmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.TreeMap;

@Repository
@Slf4j
public class AtmRepository {
    Map<Denominations, Integer> atmStock = new TreeMap<>();

    private AtmRepository(){
        atmStock.put(Denominations.ONE_HUNDRED,50);
        atmStock.put(Denominations.FIFTY,50);
        atmStock.put(Denominations.TEN,100);
        atmStock.put(Denominations.FIVE,100);
        atmStock.put(Denominations.ONE,100);
    }

    public void feedMoney(int amount) {
       Map<Denominations, Integer> map = splitIntoDenominations(amount);
        for(Map.Entry<Denominations, Integer> entry : map.entrySet()){
            for(Map.Entry<Denominations, Integer> stockentry : atmStock.entrySet()){
                if(stockentry.getKey().equals(entry.getKey())){
                    atmStock.replace(stockentry.getKey(), stockentry.getValue() + entry.getValue());
                }
            }
        }
    }

    public Map<Denominations, Integer> withdrawAmount(Map<Denominations, Integer> withdrawal){


//        for(Map.Entry<Denominations, Integer> entry : withdrawal.entrySet()){
//            for(Map.Entry<Denominations, Integer> stockentry : atmStock.entrySet()){
//                if(stockentry.getKey().equals(entry.getKey()) && stockentry.getValue() >= entry.getValue()){
//                    atmStock.replace(stockentry.getKey(), stockentry.getValue() - entry.getValue());
//                }else{
//                    atmStock.replace(stockentry.getKey(), stockentry.getValue());
//                    amount = amount - (entry.getValue()-stockentry.getValue()) * stockentry.getKey().getDenominationValue();
//                }
//            }
//        }
//        return atmStock;

//        for(Map.Entry<Denominations, Integer> stockentry : atmStock.entrySet()){
//            withdrawal = splitIntoDenominations(amount);
//            for(Map.Entry<Denominations, Integer> entry : withdrawal.entrySet()){
//                if(stockentry.getKey().equals(entry.getKey()) && stockentry.getValue() >= entry.getValue()){
//                    atmStock.replace(stockentry.getKey(), stockentry.getValue() - entry.getValue());
//                }else{
//                    atmStock.replace(stockentry.getKey(), stockentry.getValue());
//                    amount = amount - (entry.getValue()-stockentry.getValue()) * stockentry.getKey().getDenominationValue();
//                }
//            }
//        }
//        return atmStock;

        for(Map.Entry<Denominations, Integer> entry : withdrawal.entrySet()){
            for(Map.Entry<Denominations, Integer> stockentry : atmStock.entrySet()){
                if(stockentry.getKey().equals(entry.getKey())){
                    atmStock.replace(stockentry.getKey(), stockentry.getValue() - entry.getValue());
                }
            }
        }
        if (atmStock.get(Denominations.ONE_HUNDRED) <= 5) {
            log.warn("One Hundred Dollar Bill - Critical Level <10% - admin@atm.ro");
        } else {
            if (atmStock.get(Denominations.ONE_HUNDRED) <= 10) {
                log.warn("One Hundred Dollar Bill - Critical Level <20% - +407123456");
            }
        }
        if(atmStock.get(Denominations.FIFTY) <= 7){
            log.warn("Fifty Dollar Bill - Critical Level <20% - admin@atm.ro - +407123456");
        }
        return atmStock;
    }

    public Map<Denominations, Integer> currentStock(){
        return atmStock;
    }

    public int currentStockValue(){
        int sum = 0;
        for(Map.Entry<Denominations,Integer> entries : atmStock.entrySet()){
            sum += entries.getKey().getDenominationValue() * entries.getValue();
        }
        return sum;
    }

    public Map<Denominations, Integer> splitIntoDenominations(int amount){
        Map<Denominations, Integer> map = new TreeMap<>();
        int[] denominations = {100,50,10,5,1};
        Denominations[] list = Denominations.values();

        for(int i : denominations){
            int numberOfBanknotes = amount/i;
            amount = amount % i;
            for(Denominations d : list){
                if(d.getDenominationValue() == i)
                    map.put(d,numberOfBanknotes);
            }
        }
        return map;
    }
}
