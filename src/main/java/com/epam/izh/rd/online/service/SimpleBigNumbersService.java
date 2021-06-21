package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {

        return (new BigDecimal(a).divide((new BigDecimal(b)), range, RoundingMode.HALF_UP));
    }

    @Override
    public BigInteger getPrimaryNumber(int range) {

        int count = 0;
        BigInteger primaryNumber = BigInteger.valueOf(2);
        while (count != range) {
            primaryNumber = primaryNumber.nextProbablePrime();
            count++;
        }
        return primaryNumber;
    }
}
