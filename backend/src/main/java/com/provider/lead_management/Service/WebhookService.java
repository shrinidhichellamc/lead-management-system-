package com.provider.lead_management.Service;


import com.provider.lead_management.Model.ProcessedWebhook;
import com.provider.lead_management.Model.Provider;

import com.provider.lead_management.Repository.ProcessedWebhookRepo;
import com.provider.lead_management.Repository.ProviderRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final ProviderRepo providerRepo;

    private final ProcessedWebhookRepo
            processedWebhookRepo;

    public String processPaymentWebhook(

            String eventId,
            String providerCode

    ) {

        // =========================
        // IDEMPOTENCY CHECK
        // =========================

        boolean alreadyProcessed =

                processedWebhookRepo
                        .existsById(eventId);

        if (alreadyProcessed) {

            return "Webhook already processed";
        }

        // =========================
        // FIND PROVIDER
        // =========================

        Provider provider =

                providerRepo
                        .findByProviderCode(
                                providerCode
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Provider not found"
                                )
                        );

        // =========================
        // RESET QUOTA
        // =========================

        provider.setRemainingQuota(
                provider.getTotalQuota()
        );

        providerRepo.save(provider);

        // =========================
        // SAVE PROCESSED EVENT
        // =========================

        ProcessedWebhook webhook =

                ProcessedWebhook
                        .builder()

                        .eventId(eventId)

                        .providerCode(providerCode)

                        .processedAt(
                                Instant.now()
                        )

                        .build();

        processedWebhookRepo
                .save(webhook);

        return "Quota reset successful";
    }
}