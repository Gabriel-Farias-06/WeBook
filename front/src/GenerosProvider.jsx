import React, { createContext, useContext, useState } from 'react';

const GenerosContext = createContext();

export function GenerosProvider({ children }) {

  const [Generos, setGeneros] = useState([
    { genero_id: "0000-zzzz", nome: "Todos" },
    { genero_id: "1001-aaaa", nome: "Suspense" },
    { genero_id: "1002-bbbb", nome: "Mistério" },
    { genero_id: "1003-cccc", nome: "Fantasia" },
    { genero_id: "1004-dddd", nome: "Aventura" },
    { genero_id: "1005-eeee", nome: "Romance" },
    { genero_id: "1006-ffff", nome: "Psicologia" },
    { genero_id: "1007-gggg", nome: "Religião" },
    { genero_id: "1008-hhhh", nome: "Ciência" },
    { genero_id: "0110-zzzz", nome: "Todos" },
    { genero_id: "1111-aaaa", nome: "Suspense" },
    { genero_id: "1112-bbbb", nome: "Mistério" },
    { genero_id: "1113-cccc", nome: "Fantasia" },
    { genero_id: "1114-dddd", nome: "Aventura" },
    { genero_id: "1115-eeee", nome: "Romance" },
  ]);

  return (
    <GenerosContext.Provider value={[ Generos, setGeneros ]}>
      {children}
    </GenerosContext.Provider>
  );
}

export function useGeneros() {
  return useContext(GenerosContext);
}
