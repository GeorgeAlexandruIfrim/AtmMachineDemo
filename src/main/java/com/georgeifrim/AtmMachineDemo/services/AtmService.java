package com.georgeifrim.AtmMachineDemo.services;

import com.georgeifrim.AtmMachineDemo.amountDTO.DTO;
import com.georgeifrim.AtmMachineDemo.exceptions.IllegalAmount;
import com.georgeifrim.AtmMachineDemo.exceptions.NotEnoughMoney;
import com.georgeifrim.AtmMachineDemo.repositories.AtmRepository;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
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
            return atmRepository.withdrawAmount(amount);
        }else{
            throw new NotEnoughMoney();
        }
    }
    public DTO getStock(){
        return new DTO(atmRepository.currentStock());
    }

    public void feedMoney(int amount) {
        atmRepository.feedMoney(amount);
    }
}
