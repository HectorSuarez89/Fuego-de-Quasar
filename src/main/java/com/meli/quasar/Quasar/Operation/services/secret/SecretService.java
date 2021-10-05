package com.meli.quasar.Quasar.Operation.services.secret;

import com.meli.quasar.Quasar.Operation.domain.Secret;
import com.meli.quasar.Quasar.Operation.domain.dto.SatelliteDTO;

import java.util.List;
/**
 * Service to collect information and build secret.
 */
public interface SecretService {
    /**
     * Retrieves a Secret intercepted to the imperial space.
     *
     * @param satellites with confidential information.
     * @return @{@link Secret} builded.
     */
    Secret findSecret(List<SatelliteDTO> satellites);

    /**
     * Retrieves a Secret intercepted to the imperial space.
     *
     * @param satellite with confidential information.
     * @return @{@link Secret} builded.
     */
    Secret findSecret(SatelliteDTO satellite);
}
