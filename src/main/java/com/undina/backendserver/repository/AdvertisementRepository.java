package com.undina.backendserver.repository;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.model.Advertisement;
import com.undina.backendserver.model.Status;
import com.undina.backendserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement>findAllByStatus(Status status);

    List<Advertisement>findAllByOwner(User user);
}
