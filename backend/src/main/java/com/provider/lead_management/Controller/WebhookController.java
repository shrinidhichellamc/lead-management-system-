package com.provider.lead_management.Controller;

import com.provider.lead_management.Service.WebhookService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    // PAYMENT SUCCESS WEBHOOK

    @PostMapping("/payment-success")
    public String paymentSuccess(

            @RequestBody
            Map<String, String> body

    ) {

        String providerCode =
                body.get("providerCode");

        String eventId =
                body.get("eventId");

        return webhookService
                .processPaymentWebhook(
                        eventId,
                        providerCode
                );

    }
}
