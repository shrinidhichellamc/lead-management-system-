package com.provider.lead_management.Repository;



import com.provider.lead_management.Model.AllocationState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AllocationStateRepo
        extends MongoRepository<AllocationState, String> {
}