package com.provider.lead_management.Service;

import com.provider.lead_management.Dto.CreateLeadRequest;
import com.provider.lead_management.Model.Lead;
import com.provider.lead_management.Repository.LeadRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepo leadRepo;

    private final AllocationService allocationService;

    public Lead createLead(
            CreateLeadRequest request
    ) {

        String leadId =
                "L" + (System.currentTimeMillis() % 1000000);

        Lead lead = Lead.builder()
                .id(leadId)
                .name(request.getName())
                .phone(request.getPhone())
                .city(request.getCity())
                .serviceType(request.getServiceType())
                .description(request.getDescription())
                .build();

        try {

            allocationService.assignLead(lead);

        } catch (RuntimeException e) {

            throw new RuntimeException(
                    "Provider quota maxed out"
            );
        }

        return leadRepo.save(lead);
    }

    public List<Lead> getAllLeads() {

        return leadRepo.findAll();
    }
}