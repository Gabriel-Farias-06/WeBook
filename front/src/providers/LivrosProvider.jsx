import React, { createContext, useContext, useState, useEffect } from "react";
import { useUsuario } from "./UsuarioProvider";

const LivrosContext = createContext();

export function LivrosProvider({ children }) {
  const [livrosLoading, setLivrosLoading] = useState(true);
  const [livros, setLivros] = useState(null);
  const { usuario } = useUsuario();
  const [updateLivro, setUpdateLivro] = useState(false);

  useEffect(() => {
    async function getBooks() {
      try {
        const res = await fetch(
          `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/livro`,
          {
            method: "GET",
          }
        );

        const json = await res.json();

        setLivros(json);
      } catch (e) {
        console.error("Erro no console: ", e);
      } finally {
        setLivrosLoading(false);
        setUpdateLivro(false);
      }
    }

    getBooks();
  }, [updateLivro, usuario]);

  return (
    <LivrosContext.Provider
      value={{
        livros,
        setLivros,
        livrosLoading,
        setUpdateLivro,
      }}
    >
      {children}
    </LivrosContext.Provider>
  );
}

export function useLivros() {
  return useContext(LivrosContext);
}
