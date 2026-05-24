import { Event } from "../types/Event";

const BASE_URL = "http://localhost:8081/events";

export const getEvents = async (): Promise<Event[]> => {
  const res = await fetch(BASE_URL);
  return res.json();
};

export const createEvent = async (event: Event): Promise<Event> => {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(event),
  });
  return res.json();
};

export const deleteEvent = async (rNo: number) => {
  await fetch(`${BASE_URL}/${rNo}`, {
    method: "DELETE",
  });
};