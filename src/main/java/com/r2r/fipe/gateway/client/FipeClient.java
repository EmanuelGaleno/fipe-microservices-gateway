package com.r2r.fipe.gateway.client;

import com.r2r.fipe.gateway.enums.VehicleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FipeClient {

    private final RestClient rest;
    private final String baseUrl;

    public FipeClient(RestClient rest,
                      @Value("${app.fipe.base-url:https://parallelum.com.br/fipe/api/v1}") String baseUrl) {
        this.rest = rest;
        this.baseUrl = baseUrl;
    }

    /** Retorna lista de marcas (codigo/nome) para o tipo informado. */
    public List<FipeBrandItem> getBrands(VehicleType type) {
        String url = "%s/%s/marcas".formatted(baseUrl, type.getPathSegment());
        return rest.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    /** Modelo simples para a resposta da FIPE. */
    public record FipeBrandItem(String codigo, String nome) {}
}