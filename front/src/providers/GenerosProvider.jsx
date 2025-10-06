import React, { createContext, useContext, useEffect, useState } from "react";

const GenerosContext = createContext();

export function GenerosProvider({ children }) {
  const [generosLoading, setGenerosLoading] = useState(true);
  const [generos, setGeneros] = useState(null);

  useEffect(() => {
    async function getGenders() {
      try {
        const res = await fetch(`https://webook-8d4j.onrender.com/api/genero`, {
          method: "GET",
        });

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
