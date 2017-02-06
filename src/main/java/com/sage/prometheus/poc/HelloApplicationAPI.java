package com.sage.prometheus.poc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created by iain.wardle on 02/02/2017.
 */
public class HelloApplicationAPI {
    private static final String endpoint = "https://api.service.hmrc.gov.uk/hello/application";

    public static String GetHelloApplication(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/vnd.hmrc.1.0+json" );
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> response = rt.exchange(endpoint, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
