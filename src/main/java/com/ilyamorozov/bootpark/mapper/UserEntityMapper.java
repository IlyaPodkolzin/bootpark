package com.ilyamorozov.bootpark.mapper;

import com.ilyamorozov.bootpark.dto.UserEntityDto;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserEntityMapper {

    public static UserEntityDto toUserEntityDto(UserEntity userEntity) {
        return new UserEntityDto(
                userEntity.getUsername()
        );
    }
}
