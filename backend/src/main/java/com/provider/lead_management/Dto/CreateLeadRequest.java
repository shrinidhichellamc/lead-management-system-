package com.provider.lead_management.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLeadRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String serviceType;

    @NotBlank
    private String description;
}