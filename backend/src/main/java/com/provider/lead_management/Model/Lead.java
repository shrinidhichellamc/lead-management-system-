package com.provider.lead_management.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "leads")

@CompoundIndex(
        name = "phone_service_unique",
        def = "{'phone':1, 'serviceType':1}",
        unique = true
)

public class Lead {

    @Id
    private String id;

    private String name;

    private String phone;

    private String city;

    private String serviceType;

    private String description;
    private List<String> assignedProviders;
    @Builder.Default
    private Instant createdAt = Instant.now();
}