package com.undina.backendserver.service;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.mapper.AdvertisementMapper;
import com.undina.backendserver.mapper.UserMapper;
import com.undina.backendserver.model.Advertisement;
import com.undina.backendserver.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }


    @Transactional
    public AdvertisementDto create(AdvertisementDto advertisementDto) {
        return AdvertisementMapper.toAdvertisementDto(advertisementRepository.save(AdvertisementMapper.toAdvertisement(advertisementDto)));
    }


}
