import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { GenerosProvider } from "./GenerosProvider.jsx";
import Home from "./Home.jsx";
import { LivrosProvider } from "./LivrosProvider.jsx";
import { LivrosUsuarioProvider } from "./LivrosUsuarioProvider.jsx";
import Profile from "./Profile";
import { UsuarioProvider } from "./UsuarioProvider.jsx";

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
