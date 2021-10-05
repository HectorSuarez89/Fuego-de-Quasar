package com.meli.quasar.Quasar.Operation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Satellite with intercepted information.
 */
@Data
@AllArgsConstructor
public class SatelliteDTO {
    String name;
    double distance;
    String[] message;
}
