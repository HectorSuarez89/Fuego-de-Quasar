package com.meli.quasar.Quasar.Operation.services.message;

import com.meli.quasar.Quasar.Operation.domain.Message;
import com.meli.quasar.Quasar.Operation.domain.repository.MessageRepository;
import com.meli.quasar.Quasar.Operation.exceptions.MessageContentNotMatchException;
import com.meli.quasar.Quasar.Operation.exceptions.MessageSizeNotValidException;
import com.meli.quasar.Quasar.Operation.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public String getMessage(String[] message){
        String messageSecret = null;
        List<Message> messages = messageRepository.findAll();
        if (!messages.isEmpty()) {
            List<String[]> messagesLag = divideMessage(messages);
            if (!isValidLength(message, messagesLag)) {
                deleteMessage(message);
                throw new MessageSizeNotValidException();
            }
            messagesLag.add(message);
            messageSecret = decodeMessage(messagesLag);
        } else {
            if (Arrays.stream(message).noneMatch(m -> m.length() == 0)) {
                messageSecret = String.join(Constants.SPACE,message);
            }
        }
        return !messageSecret.contains("null") ? messageSecret : null;
    }

    private boolean isValidLength(String[] message, List<String[]> messagesLag) {
        return message.length == messagesLag.get(0).length;
    }

    private void deleteMessage(String[] msg) {
        Message message = messageRepository.findByMessage(String.join(Constants.SEPARATOR, msg));
        messageRepository.delete(message);
    }


    private List<String[]> divideMessage(List<Message> messages) {
        return messages
                .stream()
                .map(m -> m.getMessage().split(Constants.SEPARATOR))
                .collect(Collectors.toList());
    }

    @Override
    public Message save(String[] messages, int sateliteId) {
        String msg = String.join(Constants.SEPARATOR, messages);
        if(msg.endsWith(Constants.SEPARATOR)){
            msg = msg.concat(Constants.SPACE);
        }
        Message message = new Message();
        message.setMessage(msg);
        message.setSatelliteId(sateliteId);
        return messageRepository.save(message);
    }

    private String decodeMessage(List<String[]> messages){
        String[] secretMessage = new String[messages.get(0).length];
        for (String[] strings : messages) {
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].trim();
                if (!strings[i].isEmpty()) {
                    if (secretMessage[i] != null) {
                        if (!secretMessage[i].equals(strings[i])) {
                            log.error(String.format("Message doesnt match with words: input: %s and stored: %s", secretMessage[i],strings[i]));
                            throw new MessageContentNotMatchException();
                        }
                    } else {
                        secretMessage[i] = strings[i];
                    }
                }
            }
        }
        return String.join(Constants.SPACE, secretMessage);
    }

    @Override
    public void destroyMessages() {
        messageRepository.deleteAll();
    }
}
