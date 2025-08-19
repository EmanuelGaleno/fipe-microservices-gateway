package com.r2r.fipe.gateway.dto;

import com.r2r.fipe.gateway.enums.VehicleType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class InitialLoadRequestDTO {
    /** Quais tipos carregar: CARROS/MOTOS/CAMINHOES */
    @NotEmpty(message = "Informe pelo menos um tipo de veículo.")
    private Set<VehicleType> types;
}
