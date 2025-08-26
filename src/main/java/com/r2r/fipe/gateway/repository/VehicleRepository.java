package com.r2r.fipe.gateway.repository;

import com.r2r.fipe.gateway.entity.VehicleEntity;
import com.r2r.fipe.gateway.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    // 1.7 – listar veículos por brandCode + tipo (ex.: brand=59&type=CARROS), ordenados por modelo
    @Query("SELECT v FROM VehicleEntity v JOIN v.brand b WHERE b.fipeCode = :brandCode AND v.type = :type")
    Page<VehicleEntity> findByBrandFipeCodeAndTypeOrderByModelNameAsc(@Param("brandCode") String brandCode, @Param("type") VehicleType type, Pageable pageable);

    // variação simples sem paginação (p/ autosuggests ou listas curtas)
    @Query("SELECT v FROM VehicleEntity v JOIN v.brand b WHERE b.fipeCode = :brandCode AND v.type = :type ORDER BY v.modelName ASC")
    List<VehicleEntity> findTop25ByBrandFipeCodeAndTypeOrderByModelNameAsc(@Param("brandCode") String brandCode, @Param("type") VehicleType type);

    // utilitário: localizar um veículo pelo brandId + código do modelo (útil em consistência)
    Optional<VehicleEntity> findByBrandIdAndFipeModelCode(Long brandId, Integer fipeModelCode);

    // utilitário: listar por brandId (uso interno caso já tenha resolvido a marca)
    Page<VehicleEntity> findByBrandId(Long brandId, Pageable pageable);

    // filtro opcional por texto (busca livre dentro de um tipo)
    Page<VehicleEntity> findByTypeAndModelNameContainingIgnoreCase(VehicleType type, String query, Pageable pageable);
}