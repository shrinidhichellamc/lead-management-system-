package com.provider.lead_management.Config;



import java.util.List;
import java.util.Map;

public class AllocationConfig {

    // Mandatory providers

    public static final Map<String, List<String>>
            MANDATORY_PROVIDERS = Map.of(

            "Service 1",
            List.of(
                    "Provider 1"
            ),

            "Service 2",
            List.of(
                    "Provider 5"
            ),

            "Service 3",
            List.of(
                    "Provider 1",
                    "Provider 4"
            )
    );

    // Fair allocation pools

    public static final Map<String, List<String>>
            FAIR_POOLS = Map.of(

            "Service 1",
            List.of(
                    "Provider 2",
                    "Provider 3",
                    "Provider 4"
            ),

            "Service 2",
            List.of(
                    "Provider 6",
                    "Provider 7",
                    "Provider 8"
            ),

            "Service 3",
            List.of(
                    "Provider 2",
                    "Provider 3",
                    "Provider 5",
                    "Provider 6",
                    "Provider 7",
                    "Provider 8"
            )
    );

    // Each lead must go to exactly 3 providers

    public static final int
            TOTAL_ASSIGNMENTS = 3;
}