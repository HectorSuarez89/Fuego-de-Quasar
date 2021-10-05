package com.meli.quasar.Quasar.Operation.domain.repository;

import com.meli.quasar.Quasar.Operation.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Handles data operations with Messages
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * Retrieves a Message, searching by message content.
     *
     * @param message content to Message searched.
     * @return @{@link Message} matched with this content.
     */
    Message findByMessage(String message);
}
