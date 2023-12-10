package com.abp.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    public static final String INVENTORY_URL = "http://localhost:8081/api/v1/bookInventory";
    private final RestTemplate restTemplate;
    private static final String URL_TEMPLATE = UriComponentsBuilder.fromHttpUrl(INVENTORY_URL)
            .queryParam("inventoryId", "{inventoryId}")
            .queryParam("count", "{count}")
            .encode()
            .toUriString();

    @Override
    public boolean bookInventory(Long inventoryId, Long count) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);


        Map<String, Long> params = new HashMap<>();
        params.put("inventoryId", inventoryId);
        params.put("count", count);

        ResponseEntity<String> response = restTemplate
                .exchange(URL_TEMPLATE, HttpMethod.POST, entity, String.class, params);

        boolean isInventoryBookSuccessful = HttpStatus.OK.equals(response.getStatusCode());

        if (!isInventoryBookSuccessful) {
            log.info("Unsuccessful inventory booking. {}", response);
        }

        return isInventoryBookSuccessful;
    }
}
