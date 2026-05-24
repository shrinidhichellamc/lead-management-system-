package com.provider.lead_management.Model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "allocation_state")
public class AllocationState {

    @Id
    private String poolName;

    private int lastAssignedIndex;
}
