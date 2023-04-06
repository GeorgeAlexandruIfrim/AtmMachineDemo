package com.georgeifrim.AtmMachineDemo.unit;

import com.georgeifrim.AtmMachineDemo.repositories.AtmRepository;
import com.georgeifrim.AtmMachineDemo.services.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AtmServiceTest {

    @MockBean
    private AtmRepository atmRepository;
    @Autowired
    private AtmService atmService;

    @MockBean
    private AtmService atmService1;



}
