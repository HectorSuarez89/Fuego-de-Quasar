package com.meli.quasar.Quasar.Operation.exceptions;

/**
 * Exception throwed when the position or the message,
 * need more information to be builded.
 */
public class NotEnoughInformation extends RuntimeException{
    public NotEnoughInformation() {
        super("There is not enough information");
    }
}
