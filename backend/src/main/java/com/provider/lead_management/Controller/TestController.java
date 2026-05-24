package com.provider.lead_management.Controller;

import com.provider.lead_management.Dto.CreateLeadRequest;
import com.provider.lead_management.Service.LeadService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final LeadService leadService;

    @PostMapping("/generate-leads")
    public String generateLeads() {

        long timestamp =
                System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {

            CreateLeadRequest request =
                    new CreateLeadRequest();

            request.setName(
                    "Test User " + timestamp + i
            );

            request.setPhone(
                    "9" + (timestamp + i)
            );

            request.setCity(
                    "Chennai"
            );

            request.setServiceType(

                    switch (i % 3) {

                        case 0 -> "Service 1";

                        case 1 -> "Service 2";

                        default -> "Service 3";
                    }
            );

            request.setDescription(
                    "Auto generated lead"
            );

            leadService.createLead(request);
        }

        return "10 leads generated successfully";
    }
}