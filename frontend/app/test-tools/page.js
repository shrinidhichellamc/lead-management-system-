"use client";

import Link from "next/link";
import { useState } from "react";

export default function TestTools() {

  const [providerCode, setProviderCode] =
    useState("P001");

  const [eventId, setEventId] =
    useState("PAYMENT_001");

    const API_URL =
    "https://lead-management-backend-7djc.onrender.com";

  async function handleGenerateLeads() {

    const response = await fetch(

      `${API_URL}/api/test/generate-leads`,

      {
        method: "POST"
      }
    );

    const result =
      await response.text();

    alert(result);
  }

  async function handleResetQuota() {

    const response = await fetch(

      `${API_URL}/api/webhooks/payment-success`,

      {
        method: "POST",

        headers: {
          "Content-Type":
            "application/json"
        },

        body: JSON.stringify({

          eventId,
          providerCode
        })
      }
    );

    const result =
      await response.text();

    alert(result);
  }

  async function handleSpamWebhook() {

    for (let i = 0; i < 5; i++) {

      await fetch(

        `${API_URL}/api/webhooks/payment-success`,

        {
          method: "POST",

          headers: {
            "Content-Type":
              "application/json"
          },

          body: JSON.stringify({

            eventId,
            providerCode
          })
        }
      );
    }

    alert(
      "Webhook called multiple times"
    );
  }

  return (

    <div className="p-10">

      <Link href="/dashboard">

        <button
          className="
            bg-black
            text-white
            px-6
            py-3
            rounded-xl
            mb-8
          "
        >

          Back to Dashboard

        </button>

      </Link>

      <h1 className="
        text-4xl
        font-bold
        mb-10
      ">

        Test Tools

      </h1>

      <div className="
        flex
        flex-col
        gap-5
        w-[400px]
      ">

        <input

          value={providerCode}

          onChange={(e) =>

            setProviderCode(
              e.target.value
            )
          }

          placeholder="Provider Code"

          className="
            border
            p-3
            rounded-lg
          "
        />

        <input

          value={eventId}

          onChange={(e) =>

            setEventId(
              e.target.value
            )
          }

          placeholder="Event ID"

          className="
            border
            p-3
            rounded-lg
          "
        />

        <button

          onClick={handleResetQuota}

          className="
            bg-blue-500
            text-white
            p-4
            rounded-xl
          "
        >

          Reset Provider Quota

        </button>

        <button

          onClick={handleSpamWebhook}

          className="
            bg-red-500
            text-white
            p-4
            rounded-xl
          "
        >

          Call Webhook Multiple Times

        </button>

        <button

          onClick={handleGenerateLeads}

          className="
            bg-green-500
            text-white
            p-4
            rounded-xl
          "
        >

          Generate 10 Leads

        </button>

      </div>
    </div>
  );
}