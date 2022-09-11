package com.undina.backendserver.message;


import com.undina.backendserver.dto.MessageDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTestData {
    public static final MessageDto messageDto1 =
            MessageDto.builder().id(1L).sender(1L).recipient(2L).msg("hello1")
                    .time(LocalDateTime.of(2022, 9, 10, 12, 15)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                    .build();
    public static final MessageDto messageDto2 =
            MessageDto.builder().id(2L).sender(2L).recipient(1L).msg("hello2")
                    .time(LocalDateTime.of(2022, 9, 10, 12, 17)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                    .build();
    public static final MessageDto messageDto3 =
            MessageDto.builder().id(3L).sender(1L).recipient(2L).msg("hello3")
                    .time(LocalDateTime.of(2022, 9, 10, 12, 20)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                    .build();
    public static final MessageDto messageDtoCreated =
            MessageDto.builder().id(4L).sender(2L).recipient(1L).msg("hello1")
                    .time(LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                    .build();


}
