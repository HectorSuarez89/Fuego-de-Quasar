package com.meli.quasar.Quasar.Operation.exceptions;

/**
 * Exception throwed when the word on the position to the new message,
 * does not match with the word on the same position to the stored
 * message.
 */
public class MessageContentNotMatchException extends RuntimeException{
    public MessageContentNotMatchException() {
        super("The content of the messages do not match");
    }
}
