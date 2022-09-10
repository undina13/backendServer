package com.undina.backendserver.advertisement;


import com.undina.backendserver.dto.AdvertisementDto;
import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.model.Advertisement;
import com.undina.backendserver.model.Role;
import com.undina.backendserver.model.Status;
import com.undina.backendserver.model.User;

import static com.undina.backendserver.user.UserTestData.user1;

public class AdwertismentTestData {
    public static final AdvertisementDto advertisementDto1 =
            AdvertisementDto.builder().id(1L).name("name1").description("description1").contact("contact1")
                    .status(Status.ACTIVE).owner(1L).build();
    public static final AdvertisementDto advertisementDto2 =
            AdvertisementDto.builder().id(2L).name("name2").description("description2").contact("contact2")
                    .status(Status.CANCELED).owner(1L).build();
    public static final AdvertisementDto advertisementDto3 =
            AdvertisementDto.builder().id(3L).name("name3").description("description3").contact("contact3")
                    .status(Status.ACTIVE).owner(2L).build();
    public static final AdvertisementDto advertisementDtoCreated =
            AdvertisementDto.builder().id(4L).name("name4").description("description4").contact("contact4")
                    .status(Status.ACTIVE).owner(2L).build();

    public static final Advertisement advertisement1 =   Advertisement.builder().id(1L).name("name1").description("description1").contact("contact1")
            .status(Status.ACTIVE).owner(user1).build();
    public static final Advertisement advertisement2 =   Advertisement.builder().id(2L).name("name2").description("description2").contact("contact2")
            .status(Status.CANCELED).owner(user1).build();
}
