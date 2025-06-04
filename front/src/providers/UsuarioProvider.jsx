import { createContext, useContext, useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

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
          setUsuario(null);
          return;
        }

        async function getUser() {
          const response = await fetch(
            `https://webook-8d4j.onrender.com/api/usuario/${decoded.usuario_id}`,
            {
              method: "GET",
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );

          const data = await response.json();

          if (data) setUsuario({ ...data, token });
        }

        getUser();
      } catch (err) {
        console.error("Erro ao logar:", err);
        localStorage.removeItem("token");
      } finally {
        setLoading(false);
      }
    } else setLoading(false);
  }, []);

  useEffect(() => {
    if (!usuario) localStorage.removeItem("token");
  });

  return (
    <UsuarioContext.Provider value={{ usuario, setUsuario, loading }}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuario() {
  return useContext(UsuarioContext);
}
