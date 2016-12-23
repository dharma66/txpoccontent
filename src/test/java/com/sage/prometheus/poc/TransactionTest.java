package com.sage.prometheus.poc;


import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by phil on 16/12/16.
 */
public class TransactionTest
{
    @Test
    public void testGetNominalCode() throws Exception
    {
        Transaction tx = new Transaction();
        assertEquals(tx.getNominalCode().length(), 4);

        int nomCode = Integer.parseInt(tx.getNominalCode());
        assertTrue(nomCode >= 0);
        assertTrue(nomCode <= 9999);

    }

    @Test
    public void testGetDescription() throws Exception
    {
        List<String> validDescriptions = new ArrayList<String>(Arrays.asList("Sales", "Assets", "Depreciation", "Cost", "Cash in hand", "Fixed Assets", "Profit/Loss"));
        Transaction tx = new Transaction();
        assertTrue(validDescriptions.contains(tx.getDescription()));
    }

    @Test
    public void testGetAmount() throws Exception
    {
        Transaction tx = new Transaction();
        BigDecimal amount = tx.getAmount();
        assertTrue(amount.doubleValue() > 0);
        assertTrue(amount.doubleValue() < 1000001);

    }

}