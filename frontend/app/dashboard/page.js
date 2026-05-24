"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import axios from "axios";

const BASE_URL =
  "https://lead-management-backend-7djc.onrender.com/api";

export default function Dashboard() {

  const [providers, setProviders] = useState([]);
  const [selectedProvider, setSelectedProvider] = useState(null);
  const [assignedLeads, setAssignedLeads] = useState([]);

  const fetchProviders = async () => {

    try {

      const response = await axios.get(
        `${BASE_URL}/providers`
      );

      setProviders(response.data);

    } catch (error) {
      console.error(error);
    }
  };

  const fetchAssignedLeads = async (providerCode) => {

    try {

      const response = await axios.get(
        `${BASE_URL}/providers/${providerCode}/leads`
      );

      setAssignedLeads(response.data);

      setSelectedProvider(providerCode);

    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {

    fetchProviders();

    const interval = setInterval(() => {
      fetchProviders();
    }, 5000);

    return () => clearInterval(interval);

  }, []);

  return (

    <div className="p-10">

      <h1 className="text-4xl font-bold mb-8">
        Lead Management Dashboard
      </h1>

      <div className="flex gap-4 mb-8">

        <Link href="/test-tools">
          <button className="
            bg-blue-500
            text-white
            px-5
            py-3
            rounded
          ">
            Test Tools
          </button>
        </Link>

        <Link href="/leads">
          <button className="
            bg-green-500
            text-white
            px-5
            py-3
            rounded
          ">
            Leads
          </button>
        </Link>

      </div>

      <div className="
        grid
        grid-cols-1
        md:grid-cols-2
        lg:grid-cols-3
        gap-6
      ">

        {providers.map((provider) => (

          <div
            key={provider.providerCode}
            onClick={() =>
              fetchAssignedLeads(provider.providerCode)
            }
            className="
              border
              rounded-2xl
              p-6
              shadow-md
              cursor-pointer
              hover:bg-gray-100
            "
          >

            <h2 className="text-3xl font-bold mb-4">
              {provider.name}
            </h2>

            <p className="text-xl">
              Provider Code: {provider.providerCode}
            </p>

            <p className="text-xl">
              Remaining Quota: {provider.remainingQuota}
            </p>

            <p className="text-xl">
              Leads Received: {provider.leadsReceived}
            </p>

          </div>

        ))}

      </div>

      {selectedProvider && (

        <div className="
          fixed
          inset-0
          bg-black/50
          flex
          justify-center
          items-center
        ">

          <div className="
            bg-white
            p-8
            rounded-2xl
            w-[600px]
            max-h-[80vh]
            overflow-y-auto
          ">

            <div className="
              flex
              justify-between
              items-center
              mb-6
            ">

              <h2 className="text-3xl font-bold">
                Assigned Leads
              </h2>

              <button
                onClick={() => setSelectedProvider(null)}
                className="
                  bg-red-500
                  text-white
                  px-4
                  py-2
                  rounded
                "
              >
                Close
              </button>

            </div>

            {assignedLeads.length === 0 ? (

              <p>No leads assigned.</p>

            ) : (

              assignedLeads.map((lead) => (

                <div
                  key={lead.id}
                  className="
                    border
                    p-4
                    rounded
                    mb-4
                  "
                >

                  <p>
                    <strong>Name:</strong> {lead.name}
                  </p>

                  <p>
                    <strong>Service:</strong> {lead.serviceType}
                  </p>

                  <p>
                    <strong>Email:</strong> {lead.email}
                  </p>

                  <p>
                    <strong>Phone:</strong> {lead.phone}
                  </p>

                </div>

              ))

            )}

          </div>

        </div>

      )}

    </div>
  );
}