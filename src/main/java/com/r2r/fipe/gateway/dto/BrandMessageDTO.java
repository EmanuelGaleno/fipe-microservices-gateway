package com.r2r.fipe.gateway.dto;

import com.r2r.fipe.gateway.enums.VehicleType;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class BrandMessageDTO {
    private String brandCode;     // ex.: "59"
    private String brandName;     // "VW - VolksWagen"
    private VehicleType type;     // CARROS/MOTOS/CAMINHOES
}