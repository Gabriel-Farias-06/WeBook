import { createContext, useContext, useEffect, useState } from "react";
import jwtDecode from "jwt-decode";

const UsuarioContext = createContext();

export function UsuarioProvider({ children }) {
  const [usuario, setUsuario] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded = jwtDecode(token);

        if (decoded.exp && decoded.exp < Date.now() / 1000) {
          localStorage.removeItem("token");
          return;
        }

        setUsuario({ ...decoded, token });
      } catch (err) {
        console.error("Erro ao logar:", err);
        localStorage.removeItem("token");
      }
    }

    setLoading(false);
  }, []);

  useEffect(() => {
    if (!usuario) {
      localStorage.removeItem("token");
    } else localStorage.setItem("token", usuario.token);
  }, [usuario]);

  return (
    <UsuarioContext.Provider value={{ usuario, setUsuario, loading }}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuario() {
  return useContext(UsuarioContext);
}
