package com.ilyamorozov.bootpark.service.impl;

import com.ilyamorozov.bootpark.dto.UserEntityDto;
import com.ilyamorozov.bootpark.entity.UserEntity;
import com.ilyamorozov.bootpark.exception.ResourceNotFoundException;
import com.ilyamorozov.bootpark.mapper.UserEntityMapper;
import com.ilyamorozov.bootpark.repository.UserRepository;
import com.ilyamorozov.bootpark.service.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private UserRepository userRepository;

    @Override
    public UserEntityDto getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return UserEntityMapper.toUserEntityDto(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        userRepository.delete(userEntity);
    }
}
