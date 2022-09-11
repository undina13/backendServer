package com.undina.backendserver.service;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.exception.AdvertisementNotFoundException;
import com.undina.backendserver.exception.UserIsNotOwnerException;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.mapper.AdvertisementMapper;
import com.undina.backendserver.model.Advertisement;
import com.undina.backendserver.model.Status;
import com.undina.backendserver.model.User;
import com.undina.backendserver.repository.AdvertisementRepository;
import com.undina.backendserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        Advertisement advertisement = AdvertisementMapper.toAdvertisement(advertisementDto);
        advertisement.setOwner(userRepository.findById(advertisementDto.getOwner())
                .orElseThrow(()-> new UserNotFoundException("user not found")));
        return AdvertisementMapper.toAdvertisementDto(advertisementRepository
                .save(advertisement));
    }

    public AdvertisementDto getAdvertisementById(long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new AdvertisementNotFoundException("advertisement not found"));
        return AdvertisementMapper.toAdvertisementDto(advertisement);
    }

    public AdvertisementDto update( long advertisementId, AdvertisementDto advertisementDto) {
        User owner = userRepository.findById(advertisementDto.getOwner()).orElseThrow(() -> new UserNotFoundException("user not found"));

        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new AdvertisementNotFoundException("advertisement not found"));
        if (!owner.equals(advertisement.getOwner())) {
            throw new UserIsNotOwnerException("Вы пытаетесь изменить чужую вещь");
        }
        Advertisement advertisementToUpdate = AdvertisementMapper.toAdvertisement(advertisementDto);
        advertisementToUpdate.setOwner(owner);
        return AdvertisementMapper.toAdvertisementDto(advertisementRepository
                .save(advertisementToUpdate));
    }

    public List<AdvertisementDto> getAllAdvertisements() {
        return advertisementRepository
                .findAll()
                .stream()
                .map(AdvertisementMapper::toAdvertisementDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisementDto> getAllByStatusActive() {
        return advertisementRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(AdvertisementMapper::toAdvertisementDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisementDto> getAllByUser(long userId) {
        User owner =  userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        return advertisementRepository.findAllByOwner(owner)
                .stream()
                .map(AdvertisementMapper::toAdvertisementDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisementDto> getAllAdvertisementsSortedByName() {
        return advertisementRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Advertisement::getName))
                .map(AdvertisementMapper::toAdvertisementDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisementDto> getAllAdvertisementsSortedByOwner() {
        return advertisementRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(advertisement -> advertisement.getOwner().getId()))
                .map(AdvertisementMapper::toAdvertisementDto)
                .collect(Collectors.toList());
    }
}
