import { createContext, useContext, useEffect, useState } from "react";

const UsuarioContext = createContext();

export function UsuarioProvider({ children }) {
  const [usuario, setUsuario] = useState(null);

  useEffect(() => {
    const salvo = localStorage.getItem("usuario");
    if (!salvo) return;
    const data = JSON.parse(salvo);

    async function loginUser() {
      const login = await fetch(
        `https://webook-8d4j.onrender.com/api/usuario/login`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
          body: JSON.stringify({
            email: data.email,
            senha: data.senha,
          }),
        }
      );
      if (login.status != 200) return;
      setUsuario(await login.json());
    }

    loginUser();
  }, []);

  useEffect(() => {
    if (usuario) {
      localStorage.setItem("usuario", JSON.stringify(usuario));
    } else {
      localStorage.removeItem("usuario");
    }
  }, [usuario]);

  return (
    <UsuarioContext.Provider value={[usuario, setUsuario]}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuario() {
  return useContext(UsuarioContext);
}
