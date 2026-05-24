package com.provider.lead_management.Controller;


import com.provider.lead_management.Dto.RegisterProviderRequest;
import com.provider.lead_management.Model.Provider;
import com.provider.lead_management.Model.Lead;
import com.provider.lead_management.Service.ProviderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    // REGISTER PROVIDER

    @PostMapping("/register")
    public Provider registerProvider(

            @Valid
            @RequestBody
            RegisterProviderRequest request

    ) {

        return providerService
                .registerProvider(request);
    }

    // GET ALL PROVIDERS

    @GetMapping
    public List<Provider> getAllProviders() {

        return providerService
                .getAllProviders();
    }

    // GET PROVIDER BY ID

    @GetMapping("/{pc}")
    public Provider getProviderByProviderCode(

            @PathVariable String pc

    ) {

        return providerService
                .getProviderByProviderCode(pc);
    }

    @PutMapping("/reset-quota/{pc}")
    public Provider resetQuota(

            @PathVariable String pc

    ) {

        return providerService
                .resetQuota(pc);
    }

    @GetMapping("/{providerCode}/leads")
    public List<Lead> getAssignedLeads(

            @PathVariable String providerCode

    ) {

        return providerService
                .getAssignedLeads(providerCode);
    }
}