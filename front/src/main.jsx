import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { GenerosProvider } from "./providers/GenerosProvider.jsx";
import Home from "./Home.jsx";
import { LivrosProvider } from "./providers/LivrosProvider.jsx";
import { LivrosUsuarioProvider } from "./providers/LivrosUsuarioProvider.jsx";
import Profile from "./Profile";
import { UsuarioProvider } from "./providers/UsuarioProvider.jsx";

createRoot(document.getElementById("root")).render(
  <GenerosProvider>
    <LivrosProvider>
      <UsuarioProvider>
        <LivrosUsuarioProvider>
          <Router>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/profile" element={<Profile />} />
            </Routes>
          </Router>
        </LivrosUsuarioProvider>
      </UsuarioProvider>
    </LivrosProvider>
  </GenerosProvider>
);
