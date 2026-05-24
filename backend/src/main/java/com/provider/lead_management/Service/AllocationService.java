package com.provider.lead_management.Service;

import com.provider.lead_management.Config.AllocationConfig;
import com.provider.lead_management.Model.AllocationState;
import com.provider.lead_management.Model.Lead;
import com.provider.lead_management.Model.Provider;
import com.provider.lead_management.Repository.AllocationStateRepo;
import com.provider.lead_management.Repository.LeadRepo;
import com.provider.lead_management.Repository.ProviderRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final LeadRepo leadRepo;

    private final ProviderRepo providerRepository;

    private final AllocationStateRepo
            allocationStateRepository;

    public List<Provider> assignLead(
            Lead lead
    ) {

        String serviceType =
                lead.getServiceType();

        List<Provider> assignedProviders =
                new ArrayList<>();

        // STEP 1
        // Mandatory Providers

        List<String> mandatoryProviderNames =

                AllocationConfig
                        .MANDATORY_PROVIDERS
                        .get(serviceType);

        for (String providerName
                : mandatoryProviderNames) {

            System.out.println(
                    "SEARCHING PROVIDER: "
                            + providerName
            );

            Provider provider =
                    providerRepository
                            .findByName(providerName)
                            .orElseThrow(() ->

                                    new RuntimeException(
                                            "Provider not found: "
                                                    + providerName
                                    )
                            );

            if (provider.getRemainingQuota() <= 0) {

                throw new RuntimeException(
                        provider.getName()
                                + " quota exhausted"
                );
            }

            updateProvider(provider);

            assignedProviders.add(provider);
        }

        // STEP 2
        // Remaining Slots

        int remainingSlots =

                AllocationConfig
                        .TOTAL_ASSIGNMENTS

                        - assignedProviders.size();

        // STEP 3
        // Fair Pool Round Robin

        List<String> fairPoolNames =

                AllocationConfig
                        .FAIR_POOLS
                        .get(serviceType);

        List<Provider> fairProviders =
                new ArrayList<>();

        for (String providerName
                : fairPoolNames) {

            Provider provider =
                    providerRepository
                            .findByName(providerName)
                            .orElseThrow(() ->

                                    new RuntimeException(
                                            "Provider not found: "
                                                    + providerName
                                    )
                            );

            if (provider.getRemainingQuota() > 0) {

                fairProviders.add(provider);
            }
        }

        if (fairProviders.isEmpty()) {

            throw new RuntimeException(
                    "No providers available"
            );
        }

        AllocationState state =

                allocationStateRepository
                        .findById(serviceType)
                        .orElse(

                                AllocationState
                                        .builder()
                                        .poolName(serviceType)
                                        .lastAssignedIndex(-1)
                                        .build()
                        );

        int currentIndex =
                state.getLastAssignedIndex();

        for (int i = 0;
             i < remainingSlots;
             i++) {

            currentIndex =
                    (currentIndex + 1)
                            % fairProviders.size();

            Provider selectedProvider =
                    fairProviders.get(currentIndex);

            updateProvider(selectedProvider);

            assignedProviders.add(
                    selectedProvider
            );
        }

        // SAVE ROUND ROBIN STATE

        state.setLastAssignedIndex(
                currentIndex
        );

        allocationStateRepository
                .save(state);

        // SAVE ASSIGNED PROVIDERS INSIDE LEAD

        List<String> providerCodes =

                assignedProviders.stream()

                        .map(Provider::getProviderCode)

                        .toList();

        lead.setAssignedProviders(
                providerCodes
        );

        leadRepo.save(lead);

        return assignedProviders;
    }

    // UPDATE PROVIDER QUOTA

    private void updateProvider(
            Provider provider
    ) {

        // PREVENT NEGATIVE QUOTA

        if (provider.getRemainingQuota() <= 0) {

            throw new RuntimeException(

                    provider.getName()
                            + " quota exhausted"
            );
        }

        // REDUCE QUOTA

        provider.setRemainingQuota(

                provider.getRemainingQuota() - 1
        );

        // INCREASE LEADS RECEIVED

        provider.setLeadsReceived(

                provider.getLeadsReceived() + 1
        );

        providerRepository.save(provider);
    }
}
