import React, { createContext, useContext, useEffect, useState } from "react";
import { useUsuario } from "./UsuarioProvider";
import { useLivros } from "./LivrosProvider";

const LivrosUsuarioContext = createContext();

export function LivrosUsuarioProvider({ children }) {
  const { usuario } = useUsuario();
  const { livros } = useLivros();
  const [livrosUsuario, setLivrosUsuario] = useState();

  useEffect(() => {
    if (!usuario || !livros) return;

    setLivrosUsuario(
      livros.filter((livro) =>
        livro.usuarios?.some(
          (usuarioLivro) => usuarioLivro.usuario_id === usuario.usuario_id
        )
      )
    );
  }, [livros, usuario]);

  return (
    <LivrosUsuarioContext.Provider value={[livrosUsuario, setLivrosUsuario]}>
      {children}
    </LivrosUsuarioContext.Provider>
  );
}

export function useLivrosUsuario() {
  return useContext(LivrosUsuarioContext);
}
