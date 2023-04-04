package com.georgeifrim.AtmMachineDemo.services;


import com.georgeifrim.AtmMachineDemo.amountDTO.DTO;
import com.georgeifrim.AtmMachineDemo.exceptions.IllegalAmount;
import com.georgeifrim.AtmMachineDemo.exceptions.NotEnoughMoney;
import com.georgeifrim.AtmMachineDemo.repositories.AtmRepository;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AtmService {

    private final AtmRepository atmRepository;
    @Autowired
    public AtmService(AtmRepository atmRepository){
        this.atmRepository = atmRepository;
    }

    public Map<Denominations, Integer> withdrawAmount(int amount){
        if(amount<=0){
            throw new IllegalAmount();
        } else if(amount <= atmRepository.currentStockValue()){
            Map<Denominations, Integer> map = splitIntoDenominations(amount);
            atmRepository.withdrawAmount(map, amount);
            return map;
        }else{
            throw new NotEnoughMoney();
        }
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
    public DTO getStock(){
        return new DTO(atmRepository.currentStock());
    }

    public void feedMoney(int amount) {
        atmRepository.feedMoney(amount);
    }
}
