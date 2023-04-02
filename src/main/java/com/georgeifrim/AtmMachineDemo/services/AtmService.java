package com.georgeifrim.AtmMachineDemo.services;


import com.georgeifrim.AtmMachineDemo.DTOs.DTO;
import com.georgeifrim.AtmMachineDemo.repositories.AtmRepository;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AtmService {

    @Autowired
    AtmRepository atmRepository;

    public Map<Denominations, Integer> withdrawAmount(int amount){
        Map<Denominations, Integer> map = splitIntoDenominations(amount);
        return atmRepository.withdrawAmount(map);
    }

    public Map<Denominations, Integer> splitIntoDenominations(int amount){
        Map<Denominations, Integer> map = new TreeMap<>();
        int[] denominations = {100,50,10,5,1};
        List<Denominations> list = Denominations.getDenominationsList();

        for(int i : denominations){
            int numberOfBancknotes = amount/i;
            amount = amount % i;
            for(Denominations d : list){
                if(d.getDenominationValue() == i)
                    map.put(d,numberOfBancknotes);
            }
        }
        return map;
    }

    public DTO getStock(){
        return new DTO(atmRepository.currentStock());
    }
}
