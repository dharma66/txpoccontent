package com.sage.prometheus.poc;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

//@Configuration
public class CustomizedRestMvcConfiguration extends RepositoryRestMvcConfiguration
{
    @Override
    public RepositoryRestConfiguration config() {
        RepositoryRestConfiguration config = super.config();
        config.setMaxPageSize(100000);
        config.setDefaultPageSize(100000);
        return config;
    }
}