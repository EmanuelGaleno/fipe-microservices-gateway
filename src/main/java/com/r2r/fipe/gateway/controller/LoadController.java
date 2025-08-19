package com.r2r.fipe.gateway.controller;

import com.r2r.fipe.gateway.dto.InitialLoadRequestDTO;
import com.r2r.fipe.gateway.service.InitialLoadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Carga Inicial")
@RestController
@RequestMapping("/carga-inicial")
@RequiredArgsConstructor
public class LoadController {

    private final InitialLoadService initialLoadService;

    @Operation(summary = "Dispara a carga inicial de marcas na fila para processamento assíncrono")
    @PostMapping
    public ResponseEntity<Void> trigger(@RequestBody @Valid InitialLoadRequestDTO body) {
        initialLoadService.triggerInitialLoad(body);
        return ResponseEntity.accepted().build(); // 202 Accepted: processamento assíncrono
    }
}