"use client";
import Link from "next/link";
import {
  useEffect,
  useState
} from "react";

import {

  getProviders,
  getLeads,
  getProviderLeads,
  createLead,

} from "../../services/api";


export default function Dashboard() {

  const [providers, setProviders] =
    useState([]);

  const [leads, setLeads] =
    useState([]);

  const [selectedProvider, setSelectedProvider] =
    useState(null);

  const [providerLeads, setProviderLeads] =
    useState([]);

    const [leadForm, setLeadForm] =
  useState({

    name: "",
    phone: "",
    city: "",
    serviceType: "Service 1",
    description: ""
  });

  async function fetchDashboard() {

    try {

      const providersData =
        await getProviders();

      const leadsData =
        await getLeads();

      setProviders(providersData);

      setLeads(leadsData);

    } catch (error) {

      console.error(error);
    }
  }

  async function openProviderLeads(
    provider
  ) {

    setSelectedProvider(provider);

    try {

      const data =
        await getProviderLeads(
          provider.providerCode
        );

      setProviderLeads(data);

    } catch (error) {

      console.error(error);
    }
  }
  async function handleCreateLead() {

    try {
  
      await createLead(leadForm);
  
      alert("Lead created");
  
      setLeadForm({
  
        name: "",
        phone: "",
        city: "",
        serviceType: "Service 1",
        description: ""
      });
  
      fetchDashboard();
  
    } catch (error) {
  
      console.error(error);
  
      alert("Failed to create lead");
    }
  }

  useEffect(() => {

    fetchDashboard();

    const interval = setInterval(() => {

      fetchDashboard();

    }, 3000);

    return () => clearInterval(interval);

  }, []);

  return (

    <div className="p-10">

      <h1 className="text-4xl font-bold mb-10">

        Live Dashboard

      </h1>

      <div className="
  border
  rounded-2xl
  p-6
  shadow-lg
  mb-10
">

  <h2 className="
    text-2xl
    font-bold
    mb-5
  ">

    Create Lead

  </h2>

  <div className="
    grid
    grid-cols-2
    gap-4
  ">

    <input

      placeholder="Name"

      value={leadForm.name}

      onChange={(e) =>

        setLeadForm({

          ...leadForm,

          name: e.target.value
        })
      }

      className="
        border
        p-3
        rounded-lg
      "
    />

    <input

      placeholder="Phone"

      value={leadForm.phone}

      onChange={(e) =>

        setLeadForm({

          ...leadForm,

          phone: e.target.value
        })
      }

      className="
        border
        p-3
        rounded-lg
      "
    />

    <input

      placeholder="City"

      value={leadForm.city}

      onChange={(e) =>

        setLeadForm({

          ...leadForm,

          city: e.target.value
        })
      }

      className="
        border
        p-3
        rounded-lg
      "
    />

    <select

      value={leadForm.serviceType}

      onChange={(e) =>

        setLeadForm({

          ...leadForm,

          serviceType: e.target.value
        })
      }

      className="
        border
        p-3
        rounded-lg
      "
    >

      <option>
        Service 1
      </option>

      <option>
        Service 2
      </option>

      <option>
        Service 3
      </option>

    </select>

  </div>

  <textarea

    placeholder="Description"

    value={leadForm.description}

    onChange={(e) =>

      setLeadForm({

        ...leadForm,

        description: e.target.value
      })
    }

    className="
      border
      p-3
      rounded-lg
      w-full
      mt-4
    "
  />

  <button

    onClick={handleCreateLead}

    className="
      bg-green-600
      text-white
      px-6
      py-3
      rounded-xl
      mt-5
    "
  >

    Create Lead

  </button>

</div>

      <div className="grid grid-cols-3 gap-6">

        {providers.map((provider) => (

          <div

            key={provider.id}

            onClick={() =>
              openProviderLeads(provider)
            }

            className="
              border
              rounded-xl
              p-5
              shadow-lg
              cursor-pointer
              hover:scale-105
              transition
            "
          >

            <h2 className="text-2xl font-bold">

              {provider.name}

            </h2>

            <p className="mt-2">

              Provider Code:
              {" "}
              {provider.providerCode}

            </p>

            <p>

              Remaining Quota:
              {" "}
              {provider.remainingQuota}

            </p>

            <p>

              Leads Received:
              {" "}
              {provider.leadsReceived}

            </p>

          </div>
        ))}
      </div>
      <Link href="/test-tools">
      <div className="
  flex
  gap-4
  mb-8
">

  <Link href="/leads">

    <button
      className="
        bg-blue-600
        text-white
        px-6
        py-3
        rounded-xl
      "
    >

      Leads Page

    </button>

  </Link>

  <Link href="/test-tools">

   

  </Link>

</div>
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

  Open Test Tools

</button>

</Link>
     
        
        

      {/* MODAL */}

      {selectedProvider && (

        <div
          className="
            fixed
            inset-0
            bg-black/50
            flex
            items-center
            justify-center
          "
        >

          <div
            className="
              bg-white
              p-8
              rounded-2xl
              w-[500px]
              max-h-[600px]
              overflow-y-auto
            "
          >

            <div className="
              flex
              justify-between
              items-center
              mb-6
            ">

              <h2 className="
                text-3xl
                font-bold
              ">

                {selectedProvider.name}

              </h2>

              <button

                onClick={() =>
                  setSelectedProvider(null)
                }

                className="
                  bg-red-500
                  text-white
                  px-4
                  py-2
                  rounded-lg
                "
              >

                Close

              </button>
            </div>

            <h3 className="
              text-xl
              font-semibold
              mb-4
            ">

              Assigned Leads

            </h3>

            <div className="space-y-3">

              {providerLeads.map((lead) => (

                <div

                  key={lead.id}

                  className="
                    border
                    rounded-lg
                    p-4
                  "
                >

                  <p className="font-bold">

                    {lead.name}

                  </p>

                  <p>

                    {lead.serviceType}

                  </p>

                  <p>

                    {lead.city}

                  </p>

                </div>
              ))}

            </div>
          </div>
        </div>
      )}


    </div>
  );
}