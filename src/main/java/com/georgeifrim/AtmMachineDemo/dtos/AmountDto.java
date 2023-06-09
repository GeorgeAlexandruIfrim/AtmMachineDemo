package com.georgeifrim.AtmMachineDemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import java.util.Map;
public class AmountDto {

    int oneHundredDollars;
    int fiftyDollars;
    int tenDollars;
    int fiveDollars;
    int oneDollars;
    int total;

    public AmountDto(Map<Denominations, Integer> map){
        this.oneHundredDollars = map.get(Denominations.ONE_HUNDRED);
        this.fiftyDollars = map.get(Denominations.FIFTY);
        this.tenDollars = map.get(Denominations.TEN);
        this.fiveDollars = map.get(Denominations.FIVE);
        this.oneDollars = map.get(Denominations.ONE);
        this.total = stockTotalValue(map);
    }

    @JsonProperty("One Hundred Dollar Bills")
    public int getOneHundredDollars() {
        return oneHundredDollars;
    }

    public void setOneHundredDollars(int oneHundredDollars) {
        this.oneHundredDollars = oneHundredDollars;
    }
    @JsonProperty("Fifty Dollar Bills")
    public int getFiftyDollars() {
        return fiftyDollars;
    }

    public void setFiftyDollars(int fiftyDollars) {
        this.fiftyDollars = fiftyDollars;
    }
    @JsonProperty("Ten Dollar Bills")
    public int getTenDollars() {
        return tenDollars;
    }

    public void setTenDollars(int tenDollars) {
        this.tenDollars = tenDollars;
    }
    @JsonProperty("Five Dollar Bills")
    public int getFiveDollars() {
        return fiveDollars;
    }

    public void setFiveDollars(int fiveDollars) {
        this.fiveDollars = fiveDollars;
    }
    @JsonProperty("One Dollar Bills")
    public int getOneDollars() {
        return oneDollars;
    }

    public void setOneDollars(int oneDollars) {
        this.oneDollars = oneDollars;
    }
    @JsonProperty("Total Value of Stock")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int stockTotalValue(Map<Denominations, Integer> atmStock){
        int sum = 0;
        for(Map.Entry<Denominations,Integer> entries : atmStock.entrySet()){
            sum += entries.getKey().getDenominationValue() * entries.getValue();
        }
        return sum;
    }
}
