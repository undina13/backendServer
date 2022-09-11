package com.undina.backendserver.mapper;

import com.undina.backendserver.dto.MessageDto;
import com.undina.backendserver.model.Message;
import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class MessageMapper {
    public static Message toMessage(MessageDto messageDto){
        return Message.builder()
                .id(messageDto.getId())
                .msg(messageDto.getMsg())
                .build();
    }

    public static MessageDto toMessageDto(Message message){
        return MessageDto.builder()
                .id(message.getId())
                .sender(message.getSender().getId())
                .recipient(message.getRecipient().getId())
                .msg(message.getMsg())
                .time(message.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .build();
    }
}
