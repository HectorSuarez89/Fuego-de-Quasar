package com.meli.quasar.Quasar.Operation.domain.repository;

import com.meli.quasar.Quasar.Operation.domain.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceRepository extends JpaRepository<Distance, Integer> {

    /**
     * Retrieves a Distance, searching by length field.
     *
     * @param distance the length of distance
     * @return the @{@link Distance} with this length.
     */
    Distance findByLength(double distance);

    /**
     * Retrieves a Distance, searching by satelliteId.
     *
     * @param satelliteId of satellite with this distance
     * @return the @{@link Distance} of this satellite, searching by satelliteId.
     */
    Distance findBySatelliteId(int satelliteId);
}
