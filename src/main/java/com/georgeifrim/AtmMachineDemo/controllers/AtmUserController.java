package com.georgeifrim.AtmMachineDemo.controllers;

import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import com.georgeifrim.AtmMachineDemo.services.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AtmUserController {

    private final AtmService atmService;

    @Autowired
    public AtmUserController(AtmService atmService){
        this.atmService = atmService;
    }

    @PostMapping("/withdrawAmount/{amount}")
    public Map<Denominations, Integer> withdrawAmount (@PathVariable int amount){
            return atmService.withdrawAmount(amount);
    }
}
