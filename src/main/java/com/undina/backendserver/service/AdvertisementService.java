package com.undina.backendserver.service;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.exception.AdvertisementNotFoundException;
import com.undina.backendserver.exception.UserIsNotOwnerException;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.mapper.AdvertisementMapper;
import com.undina.backendserver.model.Advertisement;
import com.undina.backendserver.model.User;
import com.undina.backendserver.repository.AdvertisementRepository;
import com.undina.backendserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AdvertisementDto create(AdvertisementDto advertisementDto) {
        return AdvertisementMapper.toAdvertisementDto(advertisementRepository
                .save(AdvertisementMapper.toAdvertisement(advertisementDto)));
    }

    public AdvertisementDto getAdvertisementById(long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new AdvertisementNotFoundException("advertisement not found"));
        return AdvertisementMapper.toAdvertisementDto(advertisement);
    }

    public AdvertisementDto update(long userId, long advertisementId, AdvertisementDto advertisementDto) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        // нельзя изменить вещь, если ее нет в хранилище  или нет такого пользователя или вещь чужая
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new AdvertisementNotFoundException("advertisement not found"));
        if (!owner.equals(advertisement.getUser())) {
            throw new UserIsNotOwnerException("Вы пытаетесь изменить чужую вещь");
        }
        return AdvertisementMapper.toAdvertisementDto(advertisementRepository
                .save(AdvertisementMapper.toAdvertisement(advertisementDto)));
    }
}
