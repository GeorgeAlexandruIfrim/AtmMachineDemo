package com.georgeifrim.AtmMachineDemo.controllers;

import com.georgeifrim.AtmMachineDemo.DTOs.DTO;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import com.georgeifrim.AtmMachineDemo.services.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AtmController {

    @Autowired
    AtmService atmService;

    @PostMapping("/withdrawAmount/{amount}")
    public Map<Denominations, Integer> withdrawAmount (@PathVariable Integer amount){
            atmService.withdrawAmount(amount);
            return atmService.splitIntoDenominations(amount);
    }

    @GetMapping("/currentStock")
    public DTO getStock(){
        return atmService.getStock();
    }

//    @GetMapping("/amountwithdrawed/{amount}")
//    public Map<Denominations, Integer> ammountWithdrawed(@PathVariable Integer amount){
//        return atmService.splitIntoDenominations(amount);
//    }
}
