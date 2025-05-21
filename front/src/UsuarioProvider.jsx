import React, { createContext, useContext, useState } from 'react';

const UsuarioContext = createContext();

export function UsuarioProvider({ children }) {
  const [usuarioLogado, setUsuarioLogado] = useState({
    nome: "userTest",
    caminhoFoto: "../public/img/UserDefaultBigger.png",
    email: "test@gmail.com",
  });

  return (
    <UsuarioContext.Provider value={[ usuarioLogado, setUsuarioLogado ]}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuario() {
  return useContext(UsuarioContext);
}
