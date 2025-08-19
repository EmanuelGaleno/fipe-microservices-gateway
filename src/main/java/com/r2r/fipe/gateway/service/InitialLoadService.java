package com.r2r.fipe.gateway.service;

import com.r2r.fipe.gateway.client.FipeClient;
import com.r2r.fipe.gateway.dto.BrandMessageDTO;
import com.r2r.fipe.gateway.dto.InitialLoadRequestDTO;
import com.r2r.fipe.gateway.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitialLoadService {

    private final FipeClient fipeClient;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.exchange:fipe.exchange}")
    private String exchange;

    @Value("${app.rabbit.routing.brands:brands}")
    private String routingKey;

    /**
     * 1.1/1.2/1.3: para cada tipo solicitado, busca marcas na FIPE e publica 1 mensagem por marca na fila.
     * A API-2 consumirá BrandMessageDTO e fará (1.4) e (1.5).
     */
    public void triggerInitialLoad(InitialLoadRequestDTO req) {
        for (VehicleType type : req.getTypes()) {
            var brands = fipeClient.getBrands(type);
            log.info("FIPE: {} marcas encontradas para {}", brands.size(), type);

            brands.forEach(b -> {
                var msg = BrandMessageDTO.builder()
                        .brandCode(b.codigo())
                        .brandName(b.nome())
                        .type(type)
                        .build();

                rabbitTemplate.convertAndSend(exchange, routingKey, msg);
            });
        }
    }
}