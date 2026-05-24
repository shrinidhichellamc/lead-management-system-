package com.provider.lead_management.Service;


import com.provider.lead_management.Dto.RegisterProviderRequest;
import com.provider.lead_management.Model.Lead;
import com.provider.lead_management.Model.Provider;
import com.provider.lead_management.Repository.LeadRepo;
import com.provider.lead_management.Repository.ProviderRepo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final LeadRepo leadRepo;
    private final ProviderRepo providerRepo;

    // REGISTER PROVIDER

    public Provider registerProvider(
            com.provider.lead_management.Dto.@Valid RegisterProviderRequest request
    ) {

        // OPTIONAL DUPLICATE EMAIL CHECK

        if (
                providerRepo.existsByEmail(
                        request.getEmail()
                )
        ) {

            throw new RuntimeException(
                    "Provider email already exists"
            );
        }

        long count = providerRepo.count() + 1;

        String providerId =
                "P" + (System.currentTimeMillis()%100000);

        Provider provider = Provider.builder()
                .id(providerId)
                .providerCode(
                        request.getProviderCode()
                )

                .name(
                        request.getName()
                )

                .ownerName(
                        request.getOwnerName()
                )
                .email(
                        request.getEmail()
                )

                .phone(
                        request.getPhone()
                )

                .address(
                        request.getAddress()
                )

                .totalQuota(
                        request.getTotalQuota()
                )

                .remainingQuota(
                        request.getTotalQuota()
                )
                .serviceType(
                        request.getServiceType()
                )
                .leadsReceived(0)

                .build();

        return providerRepo.save(provider);
    }

    // GET ALL PROVIDERS

    public List<Provider> getAllProviders() {

        return providerRepo.findAll();
    }

    // GET PROVIDER BY PROVIDERCODE

    public Provider getProviderByProviderCode(
            String pc
    ) {

        return providerRepo.findByProviderCode(pc)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Provider not found"
                        )
                );
    }

    // GET PROVIDER BY NAME

    public Provider getProviderByName(
            String name
    ) {

        return providerRepo.findByName(name)

                .orElseThrow(() ->

                        new RuntimeException(
                                "Provider not found"
                        )
                );
    }

    // RESET MONTHLY QUOTA

    public Provider resetQuota(
            String pc
    ) {

        Provider provider =
                getProviderByProviderCode(pc);

        provider.setRemainingQuota(
                provider.getTotalQuota()
        );

        return providerRepo.save(provider);
    }

    public List<Lead> getAssignedLeads(String providerCode) {
        return leadRepo.findByAssignedProvidersContaining(providerCode);

    }
}