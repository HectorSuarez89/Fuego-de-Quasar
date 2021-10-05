package com.meli.quasar.Quasar.Operation.services;

import com.meli.quasar.Quasar.Operation.domain.Message;
import com.meli.quasar.Quasar.Operation.domain.repository.MessageRepository;
import com.meli.quasar.Quasar.Operation.services.message.MessageService;
import com.meli.quasar.Quasar.Operation.services.message.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    private MessageService service;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    public void setUp() {
        service = new MessageServiceImpl(messageRepository);
    }

    @Test
    public void checkCorrectDecodeMessage(){
        //Given
        String[] message = {"este", "", "un", "mensaje",""};
        String expectedMessage = "este es un mensaje secreto";
        Message msgStored1 = new Message();
        msgStored1.setMessage("-es--mensaje- ");
        msgStored1.setSatelliteId(1);

        Message msgStored2 = new Message();
        msgStored2.setMessage("---mensaje-secreto");
        msgStored2.setSatelliteId(2);

        List<Message> storedMessage = List.of(msgStored1, msgStored2);
        given(messageRepository.findAll())
                .willReturn(storedMessage);

        //when
        String result = service.getMessage(message);

        //then
        then(expectedMessage).equals(result);
    }
}
