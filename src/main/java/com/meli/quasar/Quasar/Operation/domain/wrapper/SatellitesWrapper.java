package com.meli.quasar.Quasar.Operation.domain.wrapper;

import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
/**
 * Wrapper to list @{@link SatelliteDTO}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatellitesWrapper {
    @NonNull
    List<SatelliteDTO> satellites;
}
