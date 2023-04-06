package com.georgeifrim.AtmMachineDemo.unit;

import com.georgeifrim.AtmMachineDemo.repositories.AtmRepository;
import com.georgeifrim.AtmMachineDemo.repositories.Denominations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AtmRepositoryTest {

    @MockBean
    private AtmRepository atmRepository;

    private final int amount = 168;

    @Test
    public void feedMoneyWithValueGreaterThan100ReturnsProperMap(){

        Map<Denominations, Integer> map = new TreeMap<>();
        map.put(Denominations.ONE_HUNDRED,1);
        map.put(Denominations.FIFTY, 1);
        map.put(Denominations.TEN, 1);
        map.put(Denominations.FIVE, 1);
        map.put(Denominations.ONE, 3);

        given(atmRepository.feedMoney(amount)).willReturn(map);

        var returnedMap = atmRepository.feedMoney(amount);

        assertThat(returnedMap).containsExactlyEntriesOf(map);
    }

    @Test
    public void feedMoneyAdjustsStock(){

        Map<Denominations, Integer> map = new TreeMap<>();
        map.put(Denominations.ONE_HUNDRED,1);
        map.put(Denominations.FIFTY, 1);
        map.put(Denominations.TEN, 1);
        map.put(Denominations.FIVE, 1);
        map.put(Denominations.ONE, 3);

        var adjustedAtmStock = atmRepository.getAtmStock();
        adjustedAtmStock.forEach((k,v) -> map.merge(k,v,Integer::sum));

        given(atmRepository.feedMoney(amount)).willReturn(adjustedAtmStock);

        var returnedMap = atmRepository.feedMoney(amount);

        assertThat(atmRepository.getAtmStock()).containsExactlyEntriesOf(returnedMap);
    }

    @Test
    public void splitIntoDenominationsReturnsProperMap(){

        var den = Denominations.ONE_HUNDRED;

        Map<Denominations, Integer> map = new TreeMap<>();
        map.put(Denominations.ONE_HUNDRED,1);
        map.put(Denominations.FIFTY, 1);
        map.put(Denominations.TEN, 1);
        map.put(Denominations.FIVE, 1);
        map.put(Denominations.ONE, 3);

        when(atmRepository.splitIntoDenominations(amount, den)).thenReturn(map);

        var returnedMap = atmRepository.splitIntoDenominations(amount,den);

        assertThat(returnedMap).containsExactlyEntriesOf(map);
    }
}
