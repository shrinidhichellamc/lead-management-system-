package com.provider.lead_management.Repository;

import com.provider.lead_management.Model.ProcessedWebhook;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessedWebhookRepo

        extends MongoRepository<
        ProcessedWebhook,
        String
        > {
}