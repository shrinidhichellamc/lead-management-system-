package com.provider.lead_management.Repository;


import com.provider.lead_management.Model.ProviderAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProviderAssignmentRepo
        extends MongoRepository<ProviderAssignment, String> {

    List<ProviderAssignment>
    findByLeadId(String leadId);

}
