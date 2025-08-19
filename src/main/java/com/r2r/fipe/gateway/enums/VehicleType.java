package com.r2r.fipe.gateway.enums;


public enum VehicleType {
    CARROS("carros"),
    MOTOS("motos"),
    CAMINHOES("caminhoes");

    private final String pathSegment;

    VehicleType(String pathSegment) {
        this.pathSegment = pathSegment;
    }

    /** Segmento usado na FIPE: carros|motos|caminhoes */
    public String getPathSegment() {
        return pathSegment;
    }

    /** Converte "carros"/"motos"/"caminhoes" para enum (case-insensitive). */
    public static VehicleType fromPath(String value) {
        if (value == null) return null;
        String v = value.trim().toLowerCase();
        for (VehicleType t : values()) {
            if (t.pathSegment.equals(v)) return t;
        }
        throw new IllegalArgumentException("Tipo de veículo inválido: " + value);
    }
}
