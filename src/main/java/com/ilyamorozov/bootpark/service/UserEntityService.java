package com.ilyamorozov.bootpark.service;

import com.ilyamorozov.bootpark.dto.UserEntityDto;

public interface UserEntityService {
    UserEntityDto getUserById(Long id);
    void deleteUserById(Long id);
}
