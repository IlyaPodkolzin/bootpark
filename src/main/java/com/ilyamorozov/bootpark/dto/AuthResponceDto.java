package com.ilyamorozov.bootpark.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthResponceDto {
    private Long id;
    private String accessToken;
    private String tokenType = "Bearer ";
    private List<String> roles = new ArrayList<>();

    public AuthResponceDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
