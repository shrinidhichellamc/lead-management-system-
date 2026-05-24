import React, { useEffect, useState } from "react";
import { Event } from "./types/Event";
import { getEvents, createEvent, deleteEvent } from "./api/eventApi";

function App() {
  const [events, setEvents] = useState<Event[]>([]);
  const [form, setForm] = useState<Event>({
    name: "",
    rNo: 0,
    eventName: "",
    eventLocation: "",
    eventDescription: "",
  });

  const loadEvents = async () => {
    const data = await getEvents();
    setEvents(data);
  };

  useEffect(() => {
    loadEvents();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({
      ...form,
      [e.target.name]:
        e.target.name === "rNo" ? Number(e.target.value) : e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await createEvent(form);
    loadEvents();
  };

  const handleDelete = async (rNo: number) => {
    await deleteEvent(rNo);
    loadEvents();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Event Manager</h1>

      <form onSubmit={handleSubmit}>
        <input name="name" placeholder="Name" onChange={handleChange} />
        <input name="rNo" placeholder="Roll No" type="number" onChange={handleChange} />
        <input name="eventName" placeholder="Event Name" onChange={handleChange} />
        <input name="eventLocation" placeholder="Location" onChange={handleChange} />
        <input name="eventDescription" placeholder="Description" onChange={handleChange} />
        <button type="submit">Add Event</button>
      </form>

      <hr />

      <h2>All Events</h2>
      {events.map((event) => (
        <div key={event.rNo} style={{ marginBottom: "10px" }}>
          <p>
            <b>{event.name}</b> ({event.rNo}) - {event.eventName}
          </p>
          <p>{event.eventLocation}</p>
          <p>{event.eventDescription}</p>
          <button onClick={() => handleDelete(event.rNo)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

export default App;