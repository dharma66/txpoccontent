package com.sage.prometheus.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContentProviderController
{
    private static final Logger logger = LoggerFactory.getLogger(ContentProviderController.class);

    @Autowired
    private TransactionRepository repo;

    @RequestMapping("/marriage-allowance/sa/{utr}/{taxYear}/status")
    public String getMarriageAllowance(@PathVariable String utr, @PathVariable String taxYear,
                                       @CookieValue(value = "hmrcToken", defaultValue= "") String hmrcToken)
            throws com.jaunt.ResponseException, java.io.UnsupportedEncodingException, java.io.IOException
    {
        if (hmrcToken.equals(""))
        {
            return "Sign In First";
        }
        String content = URLDecoder.decode(hmrcToken, "UTF-8");
        ObjectNode node = new ObjectMapper().readValue(content, ObjectNode.class);
        String token = node.get("access_token").asText();
        return MarriageAllowanceAPI.GetMarriageAllowanceStatus(token, utr, taxYear);
    }

    @RequestMapping("/helloworld")
    public String getHelloWorld()
    {
        return HelloWorldAPI.GetHelloWorld();
    }

    @RequestMapping("/helloapplication")
    public String getHelloApplication(@CookieValue(value = "hmrcToken", defaultValue= "") String hmrcToken)
            throws com.jaunt.ResponseException, java.io.UnsupportedEncodingException, java.io.IOException
    {
        String content = URLDecoder.decode(hmrcToken, "UTF-8");
        ObjectNode node = new ObjectMapper().readValue(content, ObjectNode.class);
        String token = node.get("access_token").asText();
        return HelloApplicationAPI.GetHelloApplication(token);
    }

    @RequestMapping("/oauth")
    public RedirectView startOAuth()
    {
        String authEndpoint = AuthorizationAPI.GetOAuthString();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(authEndpoint);
        return redirectView;
    }

    @RequestMapping("/auth")
    public String getAuth(HttpServletResponse response, @RequestParam String code) throws com.jaunt.ResponseException, java.io.UnsupportedEncodingException
    {
        String token = AuthorizationAPI.GetAuthToken(code);
        response.addCookie(new Cookie("hmrcToken", URLEncoder.encode(token, "UTF-8")));
        return token;
    }

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