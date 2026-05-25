package com.provider.lead_management.Controller;



import com.provider.lead_management.Dto.CreateLeadRequest;
import com.provider.lead_management.Model.Lead;
import com.provider.lead_management.Service.*;
import com.provider.lead_management.Repository.LeadRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public Lead createLead(
            @Valid @RequestBody CreateLeadRequest request
    ) {

        return leadService.createLead(request);
    }

    @GetMapping
    public List<Lead> getAllLeads() {

        return leadService.getAllLeads();
    }
}