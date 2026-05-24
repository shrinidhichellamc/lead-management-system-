package com.provider.lead_management.Dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class RegisterProviderRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Service type is required")
    private String serviceType;
    @NotBlank(message = "Provider Code is required")
    private String providerCode;

    @Min(
            value = 1,
            message = "Quota must be at least 1"
    )

    @Max(
            value = 10,
            message = "Quota cannot exceed 10"
    )


    private int totalQuota;



}