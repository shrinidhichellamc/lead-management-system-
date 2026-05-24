package com.provider.lead_management.Model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "processed_webhooks")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedWebhook {

    @Id
    private String eventId;
    private String providerCode;
    private Instant processedAt;
}
