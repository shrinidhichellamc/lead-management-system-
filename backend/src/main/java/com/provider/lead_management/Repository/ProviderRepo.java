package com.provider.lead_management.Repository;

import com.provider.lead_management.Model.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface ProviderRepo
        extends MongoRepository<Provider, String> {

    Optional<Provider>
    findByName(String providerName);

    boolean existsByEmail(String email);

     Optional<Provider> findByProviderCode(String pc);
}