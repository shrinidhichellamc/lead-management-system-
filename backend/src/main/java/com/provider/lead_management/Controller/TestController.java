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

        int successCount = 0;

        for (int i = 1; i <= 10; i++) {

            try {

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

                successCount++;

            } catch (RuntimeException e) {

                System.out.println(
                        "Skipping lead: "
                                + e.getMessage()
                );
            }
        }

        return successCount
                + " leads generated successfully";
    }
}