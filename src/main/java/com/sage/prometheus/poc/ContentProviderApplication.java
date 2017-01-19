package com.sage.prometheus.poc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContentProviderApplication
{
	@Autowired
	private TransactionRepository repo;

	public static void main(String[] args)
	{
		SpringApplication.run(ContentProviderApplication.class, args);

	}
}
