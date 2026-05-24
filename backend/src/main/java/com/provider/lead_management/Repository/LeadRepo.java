package com.provider.lead_management.Repository;



import com.provider.lead_management.Model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface LeadRepo
        extends MongoRepository<Lead, String> {
    List<Lead> findByAssignedProvidersContaining(String providerCode);
}