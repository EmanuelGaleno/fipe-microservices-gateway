package com.r2r.fipe.gateway.controller;

import com.r2r.fipe.gateway.dto.PageResponseDTO;
import com.r2r.fipe.gateway.dto.UpdateVehicleRequestDTO;
import com.r2r.fipe.gateway.dto.VehicleResponseDTO;
import com.r2r.fipe.gateway.enums.VehicleType;
import com.r2r.fipe.gateway.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Veículos")
@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(summary = "Lista códigos, modelos e observações por marca (brand=code) e tipo")
    @GetMapping
    public PageResponseDTO<VehicleResponseDTO> listByBrand(
            @RequestParam("type") VehicleType type,
            @RequestParam("brand") String brandCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return vehicleService.listByBrandAndType(brandCode, type, page, size);
    }

    @Operation(summary = "Atualiza modelo/observações do veículo")
    @PutMapping("/{id}")
    public VehicleResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateVehicleRequestDTO body
    ) {
        return vehicleService.updateVehicle(id, body);
    }
}