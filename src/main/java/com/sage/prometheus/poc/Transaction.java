package com.sage.prometheus.poc;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Transaction
{
    private static final int MIN_NOMINAL = 500;
    private static final int MAX_NOMINAL = 1500;
    private static final int MAX_VALUE = 1000000;

    @Id private String id;
    private final String nominalCode;
    private final String description;
    private final BigDecimal amount;


    private final List<String> descriptions = new ArrayList<>(Arrays.asList(
            "Sales",
            "Assets",
            "Depreciation",
            "Cost",
            "Cash in hand",
            "Fixed Assets",
            "Profit/Loss"));


    public String getNominalCode()
    {
        return nominalCode;
    }

    public String getDescription()
    {
        return description;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }


    public Transaction()
    {
        this.nominalCode = pack(ThreadLocalRandom.current().nextInt(MIN_NOMINAL, MAX_NOMINAL));
        this.description = descriptions.get(ThreadLocalRandom.current().nextInt(descriptions.size()));
        this.amount = getRandomDecimal(1, MAX_VALUE);
    }

    private static BigDecimal getRandomDecimal(int min, int max)
    {
        BigDecimal bd = new BigDecimal(String.format(new String("%.2f"), ThreadLocalRandom.current().nextDouble(min, max)));
        bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    private static String pack(int value)
    {
        return String.format("%04d", value);
    }
}
