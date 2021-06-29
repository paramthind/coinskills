package com.param.coin.currency;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitCoinCurrencyEngineTest {

    @Test
    public void test(){
        BitCoinCurrencyEngine b = new BitCoinCurrencyEngine();
        String s = b.generateAddress();
        Assertions.assertThat(s).isNotEmpty();
    }
}