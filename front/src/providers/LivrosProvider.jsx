import React, { createContext, useContext, useState, useEffect } from "react";
import { useUsuario } from "./UsuarioProvider";

const LivrosContext = createContext();

export function LivrosProvider({ children }) {
  const [livrosLoading, setLivrosLoading] = useState(true);
  const [livros, setLivros] = useState(null);
  const { usuario } = useUsuario();
  const [updateLivro, setUpdateLivro] = useState(false);
  const [livrosUsuario, setLivroUsuario] = useState([]);

  useEffect(() => {
    async function getBooks() {
      try {
        const res = await fetch(`https://webook-8d4j.onrender.com/api/livro`, {
          method: "GET",
        });

        const json = await res.json();

        setLivros(json);

        if (usuario) {
          setLivroUsuario(
            livros.filter((livro) => {
              if (!livro.usuarios) return false;
              else if (
                livro.usuarios.some(
                  (usuarioLivro) =>
                    usuarioLivro.usuario_id === usuario.usuario_id
                )
              )
                return true;
              else return false;
            })
          );
          setLivros(
            livros.filter((livro) => {
              if (!livro.usuarios) return true;
              else if (
                livro.usuarios.some(
                  (usuarioLivro) =>
                    usuarioLivro.usuario_id === usuario.usuario_id
                )
              )
                return false;
              else return true;
            })
          );
        }
      } catch (e) {
        console.error("Erro no console: ", e);
      } finally {
        setLivrosLoading(false);
      }
    }

    getBooks();
  }, [updateLivro, usuario, livros]);

  return (
    <LivrosContext.Provider
      value={{
        livros,
        setLivros,
        livrosLoading,
        setUpdateLivro,
        livrosUsuario,
      }}
    >
      {children}
    </LivrosContext.Provider>
  );
}

export function useLivros() {
  return useContext(LivrosContext);
}
