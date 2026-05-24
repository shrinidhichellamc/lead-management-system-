package com.provider.lead_management.Model;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "provider_assignments")
public class ProviderAssignment {

    @Id
    private String id;

    private String leadId;

    private String providerId;

    private String serviceCategory;

    @Builder.Default
    private Instant assignedAt = Instant.now();
}
