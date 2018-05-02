
package com.hq.myretail.remotehttp;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hq.myretail.exception.ProductNotFoundException;
import com.hq.myretail.exception.ServiceException;

/**
 */
@Component
public class InteractHttpClient {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Value("${product.external.endpoint}")
    private String externalEndpointUrl;

    private static final String PRODUCT_URI = "/v2/pdp/tcin/";

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map<String, Map> getProductInfoFromRemote(String productId) throws IOException {
        logger.info(" In InteractHttpClient.getProductInfoFromRemote() : {}", productId);
        final ObjectMapper mapper = new ObjectMapper();

        final UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(externalEndpointUrl + PRODUCT_URI + productId)
                .queryParam(
                        "excludes",
                        "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");

        final ResponseEntity<String> response = restTemplate
                .getForEntity(builder.build().encode().toUri().toString(), String.class);

        logger.info("Response from external endpoint : {}", response);

        if (response.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
            logger.error("Invalid response from external endpoint : {}", response.getBody());
            throw new ProductNotFoundException();
        } else if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            logger.error("Invalid response from external endpoint : {}", response.getBody());
            throw new ServiceException("Invalid response from external endpoint");
        }
        return mapper.readValue(response.getBody(), Map.class);
    }
}
