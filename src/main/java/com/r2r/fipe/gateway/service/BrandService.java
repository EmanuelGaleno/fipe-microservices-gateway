package com.r2r.fipe.gateway.service;

import com.r2r.fipe.gateway.dto.BrandResponseDTO;
import com.r2r.fipe.gateway.enums.VehicleType;
import com.r2r.fipe.gateway.mapper.BrandMapper;
import com.r2r.fipe.gateway.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    /** 1.6 - Lista marcas do banco por tipo (cacheável) */
    @Cacheable(cacheNames = "brandsByType", key = "'type:' + #type.name()")
    public List<BrandResponseDTO> listByType(VehicleType type) {
        return brandRepository.findByTypeOrderByNameAsc(type)
                .stream()
                .map(BrandMapper::toDto)
                .toList();
    }
}