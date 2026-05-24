import { redirect } from "next/navigation";


export default function Home() {
  redirect("/dashboard");
  return (

    <div className="p-10">

      <h1 className="text-4xl font-bold">

        Lead Management Dashboard

      </h1>

    </div>
  );
}