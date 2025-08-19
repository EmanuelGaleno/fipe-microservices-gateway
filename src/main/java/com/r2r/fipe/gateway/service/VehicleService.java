package com.r2r.fipe.gateway.service;

import com.r2r.fipe.gateway.dto.PageResponseDTO;
import com.r2r.fipe.gateway.dto.UpdateVehicleRequestDTO;
import com.r2r.fipe.gateway.dto.VehicleResponseDTO;
import com.r2r.fipe.gateway.entity.VehicleEntity;
import com.r2r.fipe.gateway.enums.VehicleType;
import com.r2r.fipe.gateway.exception.BadRequestException;
import com.r2r.fipe.gateway.exception.NotFoundException;
import com.r2r.fipe.gateway.mapper.VehicleMapper;
import com.r2r.fipe.gateway.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    /** 1.7 - Lista veículos por marca (brandCode) + tipo, paginado (cacheável) */
    @Cacheable(
            cacheNames = "vehiclesByBrand",
            key = "'type:' + #type.name() + ':brand:' + #brandCode + ':page:' + #page + ':size:' + #size"
    )
    public PageResponseDTO<VehicleResponseDTO> listByBrandAndType(String brandCode, VehicleType type, int page, int size) {
        Page<VehicleEntity> p = vehicleRepository
                .findByBrandFipeCodeAndTypeOrderByModelNameAsc(brandCode, type, PageRequest.of(page, size));

        return PageResponseDTO.<VehicleResponseDTO>builder()
                .content(p.map(VehicleMapper::toDto).getContent())
                .page(p.getNumber())
                .size(p.getSize())
                .totalElements(p.getTotalElements())
                .totalPages(p.getTotalPages())
                .last(p.isLast())
                .build();
    }

    /** 1.8 - Atualiza modelo/observações de um veículo e limpa cache relacionado */
    @CacheEvict(cacheNames = "vehiclesByBrand", allEntries = true)
    public VehicleResponseDTO updateVehicle(Long id, UpdateVehicleRequestDTO req) {
        var entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado: id=" + id));

        boolean changed = false;

        if (req.getModel() != null && !req.getModel().isBlank()) {
            entity.setModelName(req.getModel().trim());
            changed = true;
        }
        if (req.getObservacoes() != null) {
            entity.setObservacoes(req.getObservacoes());
            changed = true;
        }
        if (!changed) {
            throw new BadRequestException("Informe ao menos um campo para atualização (model ou observacoes).");
        }

        var saved = vehicleRepository.save(entity);
        return VehicleMapper.toDto(saved);
    }
}