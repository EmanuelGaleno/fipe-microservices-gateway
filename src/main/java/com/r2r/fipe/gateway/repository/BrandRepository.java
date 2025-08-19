package com.r2r.fipe.gateway.repository;

import com.r2r.fipe.gateway.entity.BrandEntity;
import com.r2r.fipe.gateway.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    // 1.6 – listar marcas por tipo (sem paginação, ordenadas por nome)
    List<BrandEntity> findByTypeOrderByNameAsc(VehicleType type);

    // variação paginada (se quiser expor /marcas paginado)
    Page<BrandEntity> findByType(VehicleType type, Pageable pageable);

    // utilitário: localizar marca exata por tipo + código FIPE (ex.: CARROS + "59")
    Optional<BrandEntity> findByTypeAndFipeCode(VehicleType type, String fipeCode);

    // utilitário: evitar duplicar marca na carga, se futuramente salvar pela API-1
    boolean existsByTypeAndFipeCode(VehicleType type, String fipeCode);

    // filtro opcional por nome (p/ buscar "volks" etc.)
    Page<BrandEntity> findByTypeAndNameContainingIgnoreCase(VehicleType type, String name, Pageable pageable);
}