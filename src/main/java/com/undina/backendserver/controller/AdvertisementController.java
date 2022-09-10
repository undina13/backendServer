package com.undina.backendserver.controller;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/advertisements")
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    @Value("${upload.path}")
    private String uploadPath;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    AdvertisementDto create(@RequestBody AdvertisementDto advertisementDto,
                            @RequestParam("file") MultipartFile file) throws IOException {
        log.info("create advertisement");
        if (file != null) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
        }
        String uuidFileName = UUID.randomUUID().toString();
        String resultFileName = uuidFileName + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFileName));
        advertisementDto.setImageFileName(resultFileName);
        return advertisementService.create(advertisementDto);
    }

    @GetMapping("/{advertisementId}")
    AdvertisementDto getAdvertisementById(@PathVariable long advertisementId) {
        log.info("get advertisementId id={}", advertisementId);
        return advertisementService.getAdvertisementById(advertisementId);
    }

    @PatchMapping("/{advertisementId}")
    AdvertisementDto update(@NotBlank @RequestHeader("X-Sharer-User-Id") long userId,
                            @PathVariable long advertisementId,
                            @RequestBody AdvertisementDto advertisementDto) {
        log.info("update advertisement id={}", advertisementId);
        return advertisementService.update(userId, advertisementId, advertisementDto);
    }

}
