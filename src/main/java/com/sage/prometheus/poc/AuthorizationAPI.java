package com.sage.prometheus.poc;

import com.jaunt.UserAgent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created by iain.wardle on 02/02/2017.
 */
public class AuthorizationAPI {
    private static final String endpoint = "https://api.service.hmrc.gov.uk/oauth/authorize?response_type=code&" +
            "client_id=jG6JN_dfjntBjHIkCWCLqmOnUBYa&" +
            "scope=read:marriage-allowance&" +
            "redirect_uri=http://localhost:8080/auth";

    public static String GetAuthToken(String code) throws com.jaunt.ResponseException
    {
        // Get Auth code
        String url = String.format("https://api.service.hmrc.gov.uk/oauth/token?client_secret=55466d46-06a3-4f3b-8c5f-b90f9b2230eb&" +
                "client_id=jG6JN_dfjntBjHIkCWCLqmOnUBYa&" +
                "grant_type=authorization_code&" +
                "redirect_uri=http://localhost:8080/auth&" +
                "code=%s", code);
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept","application/vnd.hmrc.1.0+json" );
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();

//        UserAgent userAgent = new UserAgent();
//        userAgent.visit(url);
//        System.out.println(userAgent.doc.innerHTML());
        // Find the username box

        // Find the password box

        // Find the submit button
        //return ""; //userAgent.doc.innerHTML();
    }

    public static String GetOAuthString()
    {
        return endpoint;
    }

}
