package com.georgeifrim.AtmMachineDemo.controllers;

import com.georgeifrim.AtmMachineDemo.amountDTO.DTO;
import com.georgeifrim.AtmMachineDemo.services.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/admin")
public class AtmAdminController {
    private final AtmService atmService;

    @Autowired
    public AtmAdminController(AtmService atmService){
        this.atmService = atmService;
    }

    @GetMapping("/currentStock")
    public DTO getStock(){
        return atmService.getStock();
    }

    @PutMapping("/feedMoney/{amount}")
    public void feedMoney(@PathVariable int amount){
        atmService.feedMoney(amount);
    }
}
