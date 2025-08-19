package com.r2r.fipe.gateway.dto;

import com.r2r.fipe.gateway.enums.VehicleType;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class VehicleResponseDTO {
    private Long id;                 // id interno
    private Integer code;            // fipeModelCode
    private String model;            // modelName
    private String brandCode;        // Brand.fipeCode
    private String brandName;        // Brand.name
    private VehicleType type;        // tipo
    private String observacoes;      // editável
}