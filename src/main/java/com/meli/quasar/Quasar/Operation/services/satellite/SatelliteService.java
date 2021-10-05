package com.meli.quasar.Quasar.Operation.services.satellite;

import com.meli.quasar.Quasar.Operation.domain.Position;
import com.meli.quasar.Quasar.Operation.domain.Satellite;

/**
 * Service to handle satellite operations
 */
public interface SatelliteService {
    /**
     * Retrieves a Satellite, searching by name.
     *
     * @throws @{@link com.meli.quasar.Quasar.Operation.exceptions.SatelliteNotFoundException}
     * if satellite not found.
     *
     * @param name of Satellite searched.
     * @return @{@link Satellite} matched with this name.
     */
    Satellite findByName(String name);
    /**
     * Retrieves a Position, searching by satelliteId.
     *
     * @param satelliteId content to Message searched.
     * @return @{@link Position} of this satellite.
     */
    Position findPositionBySatelllite(int satelliteId);
}
