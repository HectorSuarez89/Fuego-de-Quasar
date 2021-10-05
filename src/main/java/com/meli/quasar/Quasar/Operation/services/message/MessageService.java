package com.meli.quasar.Quasar.Operation.services.message;

import com.meli.quasar.Quasar.Operation.domain.Message;

/**
 * Service to handle message operations
 */
public interface MessageService {
  /**
   * Retrieves the message decoded as String.
   *
   * @param message as array of Strings to be decoded.
   * @return message decoded as String object.
   */
  String getMessage(String[] message);

  /**
   * Save the message associated with a satellite.
   *
   * @param message and sateliteId to be saved.
   * @return @{@link Message} saved.
   */
  Message save(String[] message, int sateliteId);

  /**
   * Delete information of stored messages.
   */
    void destroyMessages();
}
