import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { GenerosProvider } from "./providers/GenerosProvider.jsx";
import Home from "./Home.jsx";
import { LivrosProvider } from "./providers/LivrosProvider.jsx";
import Profile from "./Profile";
import Livro from "./components/Livro.jsx";
import { UsuarioProvider } from "./providers/UsuarioProvider.jsx";

createRoot(document.getElementById("root")).render(
  <GenerosProvider>
    <UsuarioProvider>
      <LivrosProvider>
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/livro" element={<Livro />} />
          </Routes>
        </Router>
      </LivrosProvider>
    </UsuarioProvider>
  </GenerosProvider>
);
