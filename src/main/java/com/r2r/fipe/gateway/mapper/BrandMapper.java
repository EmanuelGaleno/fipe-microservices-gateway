package com.r2r.fipe.gateway.mapper;

import com.r2r.fipe.gateway.dto.BrandResponseDTO;
import com.r2r.fipe.gateway.entity.BrandEntity;

public final class BrandMapper {
    private BrandMapper() {}

    public static BrandResponseDTO toDto(BrandEntity e) {
        return BrandResponseDTO.builder()
                .code(e.getFipeCode())
                .name(e.getName())
                .type(e.getType())
                .build();
    }
}