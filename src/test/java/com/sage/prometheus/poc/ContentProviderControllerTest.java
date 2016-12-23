package com.sage.prometheus.poc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by phil on 16/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
public class ContentProviderControllerTest
{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws Exception
    {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testTransaction() throws Exception
    {
        ResultActions ra = mockMvc.perform(get("/transaction"));

        ra.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].nominalCode").exists()).andExpect(jsonPath("$[0].nominalCode").isString())
            .andExpect(jsonPath("$[0].description").exists()).andExpect(jsonPath("$[0].description").isString())
            .andExpect(jsonPath("$[0].amount").exists()).andExpect(jsonPath("$[0].amount").isNumber());
    }

    @Test
    public void testTransactions() throws Exception
    {
        ResultActions ra = mockMvc.perform(get("/transactions/10"));

        ra.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(10)));

    }
}