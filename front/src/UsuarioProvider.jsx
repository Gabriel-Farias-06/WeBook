import React, { createContext, useContext, useState } from "react";

const UsuarioContext = createContext();

export function UsuarioProvider({ children }) {
  const [usuarioLogado, setUsuarioLogado] = useState(null);

  return (
    <UsuarioContext.Provider value={[usuarioLogado, setUsuarioLogado]}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuario() {
  return useContext(UsuarioContext);
}
