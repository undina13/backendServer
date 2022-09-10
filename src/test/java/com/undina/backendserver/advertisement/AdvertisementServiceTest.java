package com.undina.backendserver.advertisement;

import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.exception.AdvertisementNotFoundException;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.model.Status;
import com.undina.backendserver.service.AdvertisementService;
import com.undina.backendserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.undina.backendserver.advertisement.AdwertismentTestData.*;
import static com.undina.backendserver.user.UserTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementServiceTest {

    @Autowired
    private AdvertisementService advertisementService;

    @Test
    @DirtiesContext
    void testCreate() {
        advertisementService.create(advertisementDtoCreated);
        List<AdvertisementDto> advertisements = advertisementService.getAllAdvertisements();
        assertThat(advertisements.size(), equalTo(4));
        assertThat(advertisements, equalTo(List.of(advertisementDto1, advertisementDto2, advertisementDto3
                , advertisementDtoCreated)));
    }

    @Test
    void testGetAll() {
        List<AdvertisementDto> advertisements = advertisementService.getAllAdvertisements();
        assertThat(advertisements.size(), equalTo(3));
        assertThat(advertisements, equalTo(List.of(advertisementDto1, advertisementDto2, advertisementDto3)));
    }

    @Test
    @DirtiesContext
    void testUpdateAdvertisement() {
        advertisementDto2.setStatus(Status.ACTIVE);
        AdvertisementDto advertisementDtoFromSQL = advertisementService.update(1, 2, advertisementDto2);
        assertThat(advertisementDtoFromSQL, equalTo(advertisementDto2));
        advertisementDto2.setStatus(Status.CANCELED);
    }

    @Test
    void testUpdateAdvertisementNotFound() {
        assertThrows(AdvertisementNotFoundException.class,
                () -> advertisementService.update(1, 20L, advertisementDto2));
    }


    @Test
    void testGetAdvertisementById() {
        AdvertisementDto advertisementDtoFromSQL = advertisementService.getAdvertisementById(1L);
        assertThat(advertisementDtoFromSQL, equalTo(advertisementDto1));
    }

    @Test
    void testGetAdvertisementByWrongId() {
        assertThrows(AdvertisementNotFoundException.class, () -> advertisementService.getAdvertisementById(100L));
    }

    @Test
    void testGetAllByStatusActive() {
        List<AdvertisementDto> advertisements = advertisementService.getAllByStatusActive();
        assertThat(advertisements.size(), equalTo(2));
        assertThat(advertisements, equalTo(List.of(advertisementDto1,  advertisementDto3)));
    }

    @Test
    void testGetAllByOwner() {
        List<AdvertisementDto> advertisements = advertisementService.getAllByUser(1L);
        assertThat(advertisements.size(), equalTo(2));
        assertThat(advertisements, equalTo(List.of(advertisementDto1,  advertisementDto2)));
    }
}
