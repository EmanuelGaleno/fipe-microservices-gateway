package com.r2r.fipe.gateway.controller;

import com.r2r.fipe.gateway.dto.BrandResponseDTO;
import com.r2r.fipe.gateway.enums.VehicleType;
import com.r2r.fipe.gateway.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Marcas")
@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Lista marcas armazenadas no banco por tipo (carros/motos/caminhoes)")
    @GetMapping
    public List<BrandResponseDTO> list(@RequestParam("type") VehicleType type) {
        return brandService.listByType(type);
    }
}