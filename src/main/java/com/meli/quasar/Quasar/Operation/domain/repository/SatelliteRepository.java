package com.meli.quasar.Quasar.Operation.domain.repository;

import com.meli.quasar.Quasar.Operation.domain.Satellite;
import com.meli.quasar.Quasar.Operation.domain.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Handles data operations with Satellites
 */
public interface SatelliteRepository extends CrudRepository<Satellite, Integer> {
    /**
     * Retrieves a Satellite, searching by name.
     *
     * @param name of Satellite searched.
     * @return @{@link Satellite} matched with this name.
     */
    Optional<Satellite> findByNameIgnoreCase(String name);

    /**
     * Retrieves a Position, searching by satellite.
     *
     * @param satelliteId of Satellite with this position.
     * @return @{@link Position} of this satellite.
     */
    PositionOnly findPositionById(int satelliteId);
}
