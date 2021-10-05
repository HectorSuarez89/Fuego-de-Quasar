package com.meli.quasar.Quasar.Operation.exceptions;

/**
 * Exception throwed when the number of positions to the message y
 * different with de number of positions to the messages stored.
 */
public class MessageSizeNotValidException extends RuntimeException{
    public MessageSizeNotValidException() {
        super("The size of the new message is different than the stored messages");
    }

}
