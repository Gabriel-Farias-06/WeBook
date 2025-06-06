import React, { createContext, useContext, useState, useEffect } from "react";

const LivrosContext = createContext();

export function LivrosProvider({ children }) {
  const [livrosLoading, setLivrosLoading] = useState(true);
  const [livros, setLivros] = useState(null);
  const [updateLivro, setUpdateLivro] = useState(false);

  useEffect(() => {
    async function getBooks() {
      try {
        const res = await fetch(`https://webook-8d4j.onrender.com/api/livro`, {
          method: "GET",
        });

        setLivros(await res.json());
      } catch (e) {
        console.error("Erro no console: ", e);
      } finally {
        setLivrosLoading(false);
      }
    }

    getBooks();
  }, [updateLivro]);

  return (
    <LivrosContext.Provider
      value={{ livros, setLivros, livrosLoading, setUpdateLivro }}
    >
      {children}
    </LivrosContext.Provider>
  );
}

export function useLivros() {
  return useContext(LivrosContext);
}
