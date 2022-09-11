package com.undina.backendserver.dto;

import com.undina.backendserver.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    private Long id;

    private String name;

    private String description;

    private String contact;

    private Status status;

    private Long owner;

    private String imageFileName;
}
