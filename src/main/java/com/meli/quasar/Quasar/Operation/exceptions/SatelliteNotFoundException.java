package com.meli.quasar.Quasar.Operation.exceptions;

/**
 * Exception throwed when the satellite searched is not found.
 */
public class SatelliteNotFoundException extends RuntimeException{
    public SatelliteNotFoundException(String name) {
        super(String.format("The satellite with name %s not found", name));
    }
}
