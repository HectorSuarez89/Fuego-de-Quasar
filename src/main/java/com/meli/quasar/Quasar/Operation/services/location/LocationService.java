package com.meli.quasar.Quasar.Operation.services.location;

import com.meli.quasar.Quasar.Operation.domain.Distance;

/**
 * Service to handle location operations
 */
public interface LocationService {
    /**
     * Retrieves the coordinates to the trilateration calculated.
     *
     * @param distance of current satellite.
     * @return the coordinates to the trilateration calculated.
     */
    double[] getLocation(double distance);
    /**
     * Retrieves a Distance saved.
     *
     * @param distance and satelliteId of current satellite to be saved.
     * @return the @{@link Distance} saved.
     */
    Distance saveDistance(double distance, int satellite_id);
    /**
     * Delete information of stored distances.
     */
    void destroyDistances();
}
