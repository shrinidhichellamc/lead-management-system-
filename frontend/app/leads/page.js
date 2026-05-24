"use client";

import {

  useEffect,
  useState

} from "react";

import Link from "next/link";

import {

  getLeads,
  createLead

} from "../../services/api";

export default function LeadsPage() {

  const [leads, setLeads] =
    useState([]);

  const [leadForm, setLeadForm] =
    useState({

      name: "",
      phone: "",
      city: "",
      serviceType: "Service 1",
      description: ""
    });

  async function fetchLeads() {

    try {

      const data =
        await getLeads();

      setLeads(data);

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

      fetchLeads();

    } catch (error) {

      console.error(error);

      alert("Failed to create lead");
    }
  }

  useEffect(() => {

    fetchLeads();

    const interval = setInterval(() => {

      fetchLeads();

    }, 3000);

    return () => clearInterval(interval);

  }, []);

  return (

    <div className="p-10">

      <div className="
        flex
        gap-4
        mb-8
      ">

        <Link href="/dashboard">

          <button
            className="
              bg-black
              text-white
              px-5
              py-3
              rounded-xl
            "
          >

            Dashboard

          </button>

        </Link>

        <Link href="/test-tools">

          <button
            className="
              bg-gray-700
              text-white
              px-5
              py-3
              rounded-xl
            "
          >

            Test Tools

          </button>

        </Link>

      </div>

      <h1 className="
        text-4xl
        font-bold
        mb-10
      ">

        Leads Management

      </h1>

      {/* CREATE LEAD FORM */}

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

      {/* LEADS LIST */}

      <div className="space-y-4">

        {leads.map((lead) => (

          <div

            key={lead.id}

            className="
              border
              rounded-xl
              p-5
              shadow
            "
          >

            <h3 className="
              text-2xl
              font-bold
            ">

              {lead.name}

            </h3>

            <p>

              {lead.serviceType}

            </p>

            <p>

              {lead.city}

            </p>

            <p>

              Providers:
              {" "}
              {lead.assignedProviders?.join(", ")}

            </p>

          </div>
        ))}
      </div>
    </div>
  );
}