package com.provider.lead_management.Model;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "providers")
public class Provider {

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String serviceType;
    private String address;
    private int totalQuota;
    private String ownerName;
    private int remainingQuota;
    private String providerCode;
    private int leadsReceived;

    @Builder.Default
    private Instant createdAt = Instant.now();


}