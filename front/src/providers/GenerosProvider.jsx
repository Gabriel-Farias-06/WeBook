import React, { createContext, useContext, useEffect, useState } from "react";

const GenerosContext = createContext();

export function GenerosProvider({ children }) {
  const [generosLoading, setGenerosLoading] = useState(true);
  const [generos, setGeneros] = useState(null);

  useEffect(() => {
    async function getGenders() {
      try {
        const res = await fetch(
          `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/genero`,
          {
            method: "GET",
          }
        );

        setGeneros(await res.json());
      } catch (e) {
        console.error("Erro no console: ", e);
      } finally {
        setGenerosLoading(false);
      }
    }

    getGenders();
  }, []);
  return (
    <GenerosContext.Provider value={{ generos, setGeneros, generosLoading }}>
      {children}
    </GenerosContext.Provider>
  );
}

export function useGeneros() {
  return useContext(GenerosContext);
}
