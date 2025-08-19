package com.r2r.fipe.gateway.dto;

import com.r2r.fipe.gateway.enums.VehicleType;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class BrandResponseDTO {
    private String code;         // fipeCode
    private String name;         // nome da marca
    private VehicleType type;    // CARROS/MOTOS/CAMINHOES
}