const BASE_URL =
"https://lead-management-backend-7djc.onrender.com/api";

export async function getProviders() {

  const response = await fetch(
    `${BASE_URL}/providers`
  );

  return response.json();
}

export async function getLeads() {

  const response = await fetch(
    `${BASE_URL}/leads`
  );

  return response.json();
}

export async function getProviderLeads(
    providerCode
  ) {
  
    const response = await fetch(
  
      `${BASE_URL}/providers/${providerCode}/leads`
    );
  
    return response.json();
  }

  export async function generateLeads() {

    const response = await fetch(
  
      `${BASE_URL}/test/generate-leads`,
      {
        method: "POST"
      }
    );
  
    return response.text();
  }
  
  export async function resetQuota(
    providerCode
  ) {
  
    const response = await fetch(
  
      `${BASE_URL}/webhooks/payment-success`,
      {
        method: "POST",
  
        headers: {
          "Content-Type":
            "application/json"
        },
  
        body: JSON.stringify({
  
          providerCode
        })
      }
    );
  
    return response.text();
  }
  
  export async function spamWebhook(
    providerCode
  ) {
  
    for (let i = 0; i < 5; i++) {
  
      await fetch(
  
        `${BASE_URL}/webhooks/payment-success`,
        {
          method: "POST",
  
          headers: {
            "Content-Type":
              "application/json"
          },
  
          body: JSON.stringify({
  
            eventId: "PAYMENT_001",
  
            providerCode
          })
        }
      );
    }
  
    return "Webhook spam test completed";
  }

  export async function createLead(
    leadData
  ) {
  
    const response = await fetch(
  
      `${BASE_URL}/leads`,
  
      {
        method: "POST",
  
        headers: {
          "Content-Type":
            "application/json"
        },
  
        body: JSON.stringify(
          leadData
        )
      }
    );
  
    return response.json();
  }
