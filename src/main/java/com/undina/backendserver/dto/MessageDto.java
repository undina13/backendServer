package com.undina.backendserver.dto;

import com.undina.backendserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;

    private Long sender;

    private Long recipient;

    private String msg;

    private String time;
}
