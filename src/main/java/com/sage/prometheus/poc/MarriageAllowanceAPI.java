package com.sage.prometheus.poc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iain.wardle on 02/02/2017.
 */
public class MarriageAllowanceAPI {
    private static final String endpoint = "https://api.service.hmrc.gov.uk/marriage-allowance/sa/%s/status";

    public static String GetMarriageAllowanceStatus(String token, String utr, String taxYear)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/vnd.hmrc.1.0+json" );
        headers.add("Authorization", "Bearer " + token);

        String url = String.format(endpoint, utr);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("taxYear", taxYear);

        String b = builder.build().encode().toUriString();
        System.out.println(b);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate rt = new RestTemplate();
        HttpEntity<String> response = rt.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
