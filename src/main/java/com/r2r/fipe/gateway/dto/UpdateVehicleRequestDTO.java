package com.r2r.fipe.gateway.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateVehicleRequestDTO {

    /** Novo nome de modelo (opcional) */
    @Nullable
    @Size(min = 1, max = 180, message = "Modelo deve ter entre 1 e 180 caracteres.")
    private String model;

    /** Observações livres (opcional) */
    @Nullable
    @Size(max = 5000, message = "Observações pode ter no máximo 5000 caracteres.")
    private String observacoes;
}