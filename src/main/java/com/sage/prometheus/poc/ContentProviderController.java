package com.sage.prometheus.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContentProviderController
{
    private static final Logger logger = LoggerFactory.getLogger(ContentProviderController.class);

    @Autowired
    private TransactionRepository repo;

    @RequestMapping("/transaction")
    public List<Transaction> transaction()
    {
        Transaction tx = new Transaction();
        List<Transaction> result = new ArrayList<>();
        repo.save(tx);
        result.add(tx);

        return result;
    }

    @RequestMapping(value = "/transactions/{count}")
    public List<Transaction> transactions(@PathVariable int count)
    {
        List<Transaction> entries = new ArrayList<>();

        for(int i = 0; i < count; i++)
        {
            Transaction tx = new Transaction();
            repo.save(tx);
            entries.add(tx);
        }

        return entries;
    }
}