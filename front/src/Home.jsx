import { useState } from "react";
import "../public/css/style.css";
import { Link } from "react-router-dom";
import { useGeneros } from "./GenerosProvider";
import { useLivros } from "./LivrosProvider";
import Footer from "./Footer";
import Links from "./Links";

function Home() {

  const[generosMock, setGenerosMock] = useGeneros();
  const[livrosMock, setLivrosMock] = useLivros();

  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosMock);
  const [generoAtivo, setGeneroAtivo] = useState(generosMock.at(0));
  const [modalAberto, setModalAberto] = useState(null);
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [usuarioLogado, setUsuarioLogado] = useState(null);

  function filterFilms(genero_id, termo = "") {
    if (genero_id == "0000-zzzz")
      setLivrosFiltrados(
        livrosMock.filter((livro) => livro.titulo.toLowerCase().includes(termo))
      );
    else {
      const filter = livrosMock.filter((livro) =>
        livro.generos.includes(genero_id)
      );

      setLivrosFiltrados(
        filter.filter((livroFiltrado) =>
          livroFiltrado.titulo.toLowerCase().includes(termo)
        )
      );
    }
  }

  async function createUser() {
    const response = await fetch(`webook/api/usuario`, {
      method: "POST",
      body: JSON.stringify({
        email,
        senha,
      }),
    });

    if (response.status != 200) return setModalAberto("create-user-failed");
    setUsuarioLogado(await response.json());
  }

  async function loginUser() {
    const login = await fetch(`webook/api/usuario/${email}/${senha}`);
    if (login.status != 200) return setModalAberto("login-failed");
    setUsuarioLogado(await login.json());
  }

  return (
    <div>
      <header className="container" id="header">
        <a href="/" className="logo">
          <span>W</span>e<span>B</span>ook
        </a>
        <div id="input-wrapper">
          <input
            type="text"
            id="searchInput"
            placeholder="pesquise seu livro"
            autoComplete="on"
            onKeyUp={(e) => {
              if (e.key == "Enter") {
                const termo = e.currentTarget.value.toLowerCase();
                filterFilms(generoAtivo.genero_id, termo);
              }
            }}
          />
          <img
            src="../img/Search.svg"
            className="search-icon"
            onClick={() => {
              const termo = document
                .getElementById("searchInput")
                .value.toLowerCase();
              filterFilms(generoAtivo.genero_id, termo);
            }}
          />
        </div>
        <a href="#">
          <img src="./img/Cart.svg" alt="Carrinho" />
        </a>
        <a href="#">
          <img
            src="./img/Notification.svg"
            alt="Notificações"
            onClick={() => {
              usuarioLogado ? null : setModalAberto("login");
            }}
          />
        </a>
        <Link
          to="/profile"
          id="userLogin"
          onClick={(e) => {
              if(usuarioLogado) {
                  e.preventDefault();
                  setModalAberto("login");
            }
          }}
        >
          <img
            src={
              usuarioLogado && usuarioLogado.caminhoFoto
                ? usuarioLogado.caminhoFoto
                : "./img/UserDefault.png"
            }
            alt="Usuário"
          />
        </Link>
        {modalAberto === "login" && (
          <div
            className="modal"
            id="login-bg"
            onClick={() => setModalAberto(null)}
          >
            <div
              className="modal-content"
              id="login"
              onClick={(e) => e.stopPropagation()}
            >
              <img
                src="../img/Close.svg"
                onClick={() => {
                  setModalAberto(null);
                }}
              />
              <h3>Entrar</h3>
              <p className="protect">Dados pessoais criptografados.</p>
              <label htmlFor="email">Insira seu email</label>
              <input
                type="email"
                id="email"
                autoComplete="email"
                onChange={(e) => setEmail(e.target.value)}
              />
              <label htmlFor="senha">Insira sua senha</label>
              <input
                type="password"
                id="senha"
                autoComplete="current-password"
                onChange={(e) => setSenha(e.target.value)}
              />
              <a
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  loginUser();
                }}
              >
                Entrar
              </a>
              <p className="cadastro-link">
                Não tem uma conta?{" "}
                <a
                  href="#"
                  onClick={() => {
                    setModalAberto("cadastro");
                  }}
                >
                  Cadastre-se aqui
                </a>
              </p>
              <p className="termos">
                Ao continuar você afirma que é maior de idade e que leu e aceita
                os termos da nossa <a href="/terms">política de privacidade.</a>
              </p>
            </div>
          </div>
        )}
        {modalAberto == "cadastro" && (
          <div
            className="modal"
            id="cadastro-bg"
            onClick={() => setModalAberto(null)}
          >
            <div
              className="modal-content"
              id="cadastro"
              onClick={(e) => e.stopPropagation()}
            >
              <img
                src="../img/Close.svg"
                onClick={() => setModalAberto(null)}
              />
              <h3>Cadastro</h3>
              <p className="protect">Dados pessoais criptografados.</p>
              <label htmlFor="email">Insira seu email</label>
              <input
                type="email"
                id="email"
                autoComplete="email"
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
              />
              <label htmlFor="senha">Insira sua senha</label>
              <input
                type="password"
                id="senha"
                autoComplete="new-password"
                onChange={(e) => {
                  setSenha(e.target.value);
                }}
              />
              <a
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  createUser();
                }}
              >
                Fazer Cadastro
              </a>
              <p className="cadastro-link">
                Já possui cadastro?{" "}
                <a
                  href="#"
                  onClick={() => {
                    setModalAberto("login");
                  }}
                >
                  Entre aqui
                </a>
              </p>
              <p className="termos">
                Ao continuar você afirma que é maior de idade e que leu e aceita
                os termos da nossa <a href="/terms">política de privacidade.</a>
              </p>
            </div>
          </div>
        )}
        {modalAberto == "login-failed" && (
          <div
            className="modal modal-error"
            id="cadastro-bg"
            onClick={() => setModalAberto(null)}
          >
            <div
              className="modal-content"
              id="cadastro"
              onClick={(e) => e.stopPropagation()}
            >
              <span></span>
              <img
                src="../public/img/Close.svg"
                onClick={() => setModalAberto(null)}
              />
              <img
                src="../public/img/LoginError.png"
                alt="Ícone de erro no login"
                id="img-error"
              />
              <h3>Ops! Algo deu errado</h3>
              <p>
                Ocorreu um erro ao realizar o login, verifique se os dados estão
                corretos.
              </p>
              <a href="#" onClick={() => setModalAberto("login")}>
                Voltar
              </a>
            </div>
          </div>
        )}
      </header>

      <article
        aria-labelledby="categorias"
        id="categories"
        className="container"
      >
        <ul>
          {generosMock.map((genero) => {
            return (
              <li
                key={genero.genero_id}
                className={
                  generoAtivo.genero_id == genero.genero_id ? "active" : ""
                }
                onClick={() => {
                  document.getElementById("searchInput").value = "";
                  setGeneroAtivo(genero);
                  filterFilms(genero.genero_id);
                }}
              >
                <a href="#">{genero.nome}</a>
              </li>
            );
          })}
        </ul>
      </article>

      {livrosFiltrados.length == 0 ? (
        <h2 id="naoEncontrou">Não há resultados para sua pesquisa</h2>
      ) : (
        <article aria-labelledby="books" id="books" className="container">
          <ul>
            {livrosFiltrados.map((livro) => (
              <li key={livro.livro_id}>
                <a href="#">
                  <img src={"/img/" + livro.caminhoLivro} />
                  <h2>
                    {livro.titulo.length > 18
                      ? livro.titulo.substring(0, 18) + "..."
                      : livro.titulo}
                  </h2>
                  <div>
                    <p>{"R$ " + livro.preco.toFixed(2)}</p>
                    <img src="/img/Star.svg" />
                    <p id="avaliacao">
                      {(
                        livro.usuarios.reduce(
                          (soma, usuario) => soma + usuario.nota,
                          0
                        ) / livro.usuarios.length
                      ).toFixed(1)}
                    </p>
                  </div>
                </a>
              </li>
            ))}
          </ul>
        </article>
      )}

      <Links />

      <Footer />
    </div>
  );
}

export default Home;
