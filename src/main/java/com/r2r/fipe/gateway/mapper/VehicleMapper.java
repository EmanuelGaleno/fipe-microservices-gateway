package com.r2r.fipe.gateway.mapper;

import com.r2r.fipe.gateway.dto.VehicleResponseDTO;
import com.r2r.fipe.gateway.entity.VehicleEntity;

public final class VehicleMapper {
    private VehicleMapper() {}

    public static VehicleResponseDTO toDto(VehicleEntity e) {
        return VehicleResponseDTO.builder()
                .id(e.getId())
                .code(e.getFipeModelCode())
                .model(e.getModelName())
                .brandCode(e.getBrand().getFipeCode())
                .brandName(e.getBrand().getName())
                .type(e.getType())
                .observacoes(e.getObservacoes())
                .build();
    }
}