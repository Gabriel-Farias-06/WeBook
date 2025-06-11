import { useState, useEffect, useRef } from "react";
import ePub from "epubjs";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "../../public/css/style.css";
import { useUsuario } from "../providers/UsuarioProvider";

function Livro() {
  const location = useLocation();
  const { titulo } = location.state || {};
  const viewerRef = useRef();
  const fileUrl = location.state.caminhoEbook;
  console.log(fileUrl);
  const renditionRef = useRef();
  const bookRef = useRef();
  const [fontSize, setFontSize] = useState(100);
  const [darkMode, setDarkMode] = useState(false);
  const [progress, setProgress] = useState(0);
  const [openSumary, setOpenSumary] = useState(false);
  const [fade, setFade] = useState(false);
  const [changeImage, setChangeImage] = useState(false);
  const [toc, setToc] = useState([]);
  const navigate = useNavigate();
  const { usuario } = useUsuario();
  if (!usuario) navigate("/");

  useEffect(() => {
    const book = ePub(fileUrl);
    const rendition = book.renderTo(viewerRef.current, {
      width: "100%",
      height: 484,
      allowScriptedContent: true,
    });

    rendition.display();

    bookRef.current = book;
    renditionRef.current = rendition;

    rendition.themes.register("dark", {
      body: {
        background: "#141414",
        color: "#e0e0e0",
      },
    });

    rendition.themes.register("light", {
      body: {
        background: "#e0e0e0",
        color: "#141414",
      },
    });

    book.ready.then(() => book.locations.generate(1000));

    book.loaded.navigation.then(({ toc }) => setToc(toc));

    rendition.on("relocated", (location) => {
      const currentCfi = location.start.cfi;
      setProgress(book.locations.percentageFromCfi(currentCfi));
    });

    return () => {
      book.destroy();
    };
  }, [fileUrl]);

  useEffect(() => {
    renditionRef.current?.themes.fontSize(`${fontSize}%`);
  }, [fontSize]);

  useEffect(() => {
    renditionRef.current?.themes.select(darkMode ? "dark" : "light");
    setFade(true);
    const timeout = setTimeout(() => {
      setChangeImage(!changeImage);
      setFade(false);
    }, 500);

    return () => clearTimeout(timeout);
  }, [darkMode]);

  return (
    <div
      id="livro"
      style={
        darkMode
          ? { background: "#141414", color: "#f0f0f0" }
          : { background: "#e0e0e0", color: "#141414" }
      }
    >
      <div id="flex-start-book">
        <Link to="/profile">
          <img src="./img/Back.svg" alt="Voltar para o perfil" />
        </Link>
        <span id="sumary" onClick={() => setOpenSumary(true)}>
          <img src="./img/Sumary.svg" alt="Abrir sumário" />
        </span>
        <p>{titulo}</p>
        <button onClick={() => setFontSize((size) => Math.max(size - 10, 80))}>
          <img
            src="./img/DecreaseFontSize.svg"
            alt="Diminuir tamanho da fonte"
          />
        </button>
        <button onClick={() => setFontSize((size) => Math.min(size + 10, 150))}>
          <img
            src="./img/IncreaseFontSize.svg"
            alt="Aumentar tamanho da fonte"
          />
        </button>
        <button onClick={() => setDarkMode(!darkMode)}>
          <img
            className={`image-fade ${fade ? "hidden" : ""}`}
            src={changeImage ? "./img/DarkMode.svg" : "./img/LightMode.svg"}
            alt="Alternar entre modo claro e escuro"
          />
        </button>
      </div>
      <div ref={viewerRef}></div>
      <div id="flex-end-book">
        <button onClick={() => renditionRef.current?.prev()}>
          Voltar página
        </button>
        <p>{`${(progress * 100).toFixed(1)}% lido`}</p>

        <button onClick={() => renditionRef.current?.next()}>
          Avançar página
        </button>
      </div>
      {openSumary && (
        <div
          id="sumary-open"
          className="modal"
          onClick={() => setOpenSumary(false)}
        >
          <div
            className="modal-content"
            style={
              darkMode
                ? { background: "#cccccc", color: "#141414" }
                : { background: "#141414", color: "#f0f0f0" }
            }
            onClick={(e) => e.stopPropagation()}
          >
            <h2>Sumário</h2>
            <ul>
              {toc.map((item) => {
                return (
                  <li key={item.href}>
                    <button
                      style={
                        darkMode
                          ? { background: "#141414", color: "#f0f0f0" }
                          : { background: "#f0f0f0", color: "#141414" }
                      }
                      onClick={() => renditionRef.current.display(item.href)}
                    >
                      {item.label}
                    </button>
                  </li>
                );
              })}
            </ul>
          </div>
        </div>
      )}
    </div>
  );
}

export default Livro;
