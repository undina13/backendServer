package com.undina.backendserver.controller;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.service.AdvertisementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/advertisements")
@Tag(name = "Advertisement Controller", description = "Отвечает за размещение и показ объявлений")
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    @Value("${upload.path}")
    private String uploadPath;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Operation(
            summary = "Создание объявления",
            description = "Позволяет создать объявление"
    )
    @PostMapping
    @PreAuthorize("hasAuthority('user')")
    AdvertisementDto create(@RequestBody AdvertisementDto advertisementDto,
                            @RequestParam("file") MultipartFile file) throws IOException {
        log.info("create advertisement");
        if (file != null) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFileName = UUID.randomUUID().toString();
            String resultFileName = uuidFileName + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            advertisementDto.setImageFileName(resultFileName);
        }
        return advertisementService.create(advertisementDto);
    }

    @Operation(
            summary = "Получение объявления",
            description = "Позволяет получить объявление по Id"
    )
    @GetMapping("/{advertisementId}")
    @PreAuthorize("hasAuthority('user')")
    AdvertisementDto getAdvertisementById(@PathVariable long advertisementId) {
        log.info("get advertisementId id={}", advertisementId);
        return advertisementService.getAdvertisementById(advertisementId);
    }

    @Operation(
            summary = "Получение списка объявлений для главной страницы",
            description = "Позволяет получить список всех объявлений "
    )
    @GetMapping()
    @PreAuthorize("hasAuthority('user')")
    List<AdvertisementDto> getAll() {
        log.info("get all advertisements ");
        return advertisementService.getAllAdvertisements();
    }

    @Operation(
            summary = "Получение списка объявлений для главной страницы",
            description = "Позволяет получить список всех объявлений с сортировкой по наименованию"
    )
    @GetMapping("/sort/name")
    @PreAuthorize("hasAuthority('user')")
    List<AdvertisementDto> getAllSortedByName() {
        log.info("get all advertisements sorted by name ");
        return advertisementService.getAllAdvertisementsSortedByName();
    }

    @Operation(
            summary = "Получение списка объявлений для главной страницы",
            description = "Позволяет получить список всех объявлений с сортировкой по создателю объявления"
    )
    @GetMapping("/sort/owner")
    @PreAuthorize("hasAuthority('user')")
    List<AdvertisementDto> getAllSortedByOwner() {
        log.info("get all advertisements sorted by owner ");
        return advertisementService.getAllAdvertisementsSortedByOwner();
    }

    @Operation(
            summary = "Получение списка объявлений для главной страницы",
            description = "Позволяет получить список всех активных объявлений"
    )
    @GetMapping("/active")
    @PreAuthorize("hasAuthority('user')")
    List<AdvertisementDto> getAllByStatusActive() {
        log.info("get all advertisements by Statius ACTIVE ");
        return advertisementService.getAllByStatusActive();
    }

    @Operation(
            summary = "Получение списка объявлений для юзера",
            description = "Позволяет получить список всех объявлений, созданных определенным пользователем "
    )
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('user')")
    List<AdvertisementDto> getAllByUser(@PathVariable long userId) {
        log.info("get all advertisements ");
        return advertisementService.getAllByUser(userId);
    }

    @Operation(
            summary = "Изменение объявления",
            description = "Позволяет изменить объявление"
    )
    @PatchMapping("/{advertisementId}")
    @PreAuthorize("hasAuthority('user')")
    AdvertisementDto update(@PathVariable long advertisementId,
                            @RequestBody AdvertisementDto advertisementDto) {
        log.info("update advertisement id={}", advertisementId);
        return advertisementService.update(advertisementId, advertisementDto);
    }
}
