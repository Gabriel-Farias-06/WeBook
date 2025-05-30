import { useState } from "react";
import "../public/css/style.css";
import { Link } from "react-router-dom";
import { useGeneros } from "./providers/GenerosProvider";
import { useLivros } from "./providers/LivrosProvider";
import Footer from "./components/Footer";
import Links from "./components/Links";
import Loading from "./components/Loading";
import { useUsuario } from "./providers/UsuarioProvider";
import StripeContainer from "./components/StripeContainer";

function Home() {
  const [generosMock] = useGeneros();
  const [livrosMock] = useLivros();
  const [clientSecret, setClientSecret] = useState(null);

  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosMock);
  const [generoAtivo, setGeneroAtivo] = useState(generosMock.at(0));
  const [modalAberto, setModalAberto] = useState(null);
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const { usuario, loading, setUsuario } = useUsuario();
  const [modalLivro, setModalLivro] = useState(null);
  const [alarmPassword, setAlarmPassword] = useState(false);
  const [livro, setLivro] = useState(null);

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
    const response = await fetch(
      `https://webook-8d4j.onrender.com/api/usuario/signup`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify({
          email,
          senha,
        }),
      }
    );

    if (response.status != 201) return setModalAberto("create-user-failed");
    setModalAberto(null);
    setUsuario(await response.json());
  }

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
          email,
          senha,
        }),
      }
    );
    if (login.status != 200) return setModalAberto("login-failed");
    setModalAberto(null);
    setUsuario(await login.json());
  }

  if (loading) return <Loading />;

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
            src="/img/Search.svg"
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
          <img
            src="/img/Cart.svg"
            alt="Carrinho"
            onClick={() => {
              if (usuario) {
                document.body.style.overflow = "hidden";
                setModalAberto("shopping");
              } else setModalAberto("login");
            }}
          />
        </a>
        <a href="#">
          <img
            src="/img/Notification.svg"
            alt="Notificações"
            onClick={() => {
              if (usuario) {
                document.body.style.overflow = "hidden";
                setModalAberto("notifications");
              } else setModalAberto("login");
            }}
          />
        </a>
        <Link
          to="/profile"
          id="userLogin"
          onClick={(e) => {
            if (!usuario) {
              e.preventDefault();
              setModalAberto("login");
            }
          }}
        >
          <img
            id="profilePhoto"
            src={
              usuario && usuario.caminhoFoto
                ? usuario.caminhoFoto
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
                src="/img/Close.svg"
                onClick={() => {
                  setModalAberto(null);
                }}
              />
              <h3>Entrar</h3>
              <div id="container-flex">
                <p className="protect">Dados pessoais criptografados.</p>
              </div>
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
                onClick={async (e) => {
                  e.preventDefault();
                  e.target.classList.add("inative");
                  e.currentTarget.innerText = "Carregando";
                  await loginUser();
                  e.target.classList.remove("inative");
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
              <img src="/img/Close.svg" onClick={() => setModalAberto(null)} />
              <h3>Cadastro</h3>
              <div id="container-flex">
                <p className="protect">Dados pessoais criptografados.</p>
              </div>
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
                  const regexp =
                    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+=[\]{}|;:'",.<>?/`~-])(?!.*\s).{8,}$/;
                  if (!regexp.test(e.target.value)) setAlarmPassword(2);
                  else setAlarmPassword(0);
                  setSenha(e.target.value);
                }}
              />
              {alarmPassword == 2 && (
                <p>
                  A senha deve conter 8 caracteres, maiúsculas, minúsculas,
                  números e símbolos!
                </p>
              )}
              <a
                href="#"
                onClick={async (e) => {
                  e.preventDefault();
                  e.target.classList.add("inative");
                  e.currentTarget.innerText = "Carregando";
                  await createUser();
                  e.target.classList.remove("inative");
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
              <img src="/img/Close.svg" onClick={() => setModalAberto(null)} />
              <img
                src="/img/LoginError.png"
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
        {modalAberto == "shopping" && (
          <div
            className="modal"
            id="shopping"
            onClick={() => {
              setModalAberto(null);
              document.body.style.overflow = "auto";
            }}
          >
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <p>Produtos adicionados recentemente</p>
              <div>
                <ul>
                  {livrosMock.map((livro) => (
                    <li key={livro.livro_id}>
                      <img
                        src={"/img/" + livro.caminhoLivro}
                        alt={"Capa do livro " + livro.titulo}
                      />
                      <p id="titulo">{livro.titulo}</p>
                      <p>{livro.preco.toFixed(2)}</p>
                    </li>
                  ))}
                </ul>
              </div>
              <p id="more">Ver tudo</p>
            </div>
          </div>
        )}
        {modalAberto == "notifications" && (
          <div
            className="modal"
            id="notifications"
            onClick={() => {
              setModalAberto(null);
              document.body.style.overflow = "auto";
            }}
          >
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <p>Notificações recebidas recentemente</p>
              <div>
                <ul>
                  {livrosMock.map((livro) => (
                    <li key={livro.livro_id}>
                      <img
                        src={"/img/" + livro.caminhoLivro}
                        alt={"Capa do livro " + livro.titulo}
                      />
                      <p id="titulo">{livro.titulo}</p>
                      <p>{livro.sinopse}</p>
                    </li>
                  ))}
                </ul>
              </div>
              <p id="more">Ver tudo</p>
            </div>
          </div>
        )}
        {modalLivro && (
          <div
            className="modal"
            id="modal-livro"
            onClick={() => setModalLivro(null)}
          >
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <img
                src="/img/Close.svg"
                id="close"
                onClick={() => setModalLivro(null)}
              />
              <img src={"/img/" + modalLivro.caminhoLivro} />
              <div id="livro-meio">
                <h2>{modalLivro.titulo}</h2>
                <div id="first-avaliation">
                  <p>
                    {(
                      modalLivro.usuarios.reduce(
                        (soma, usuario) => soma + usuario.nota,
                        0
                      ) / modalLivro.usuarios.length
                    ).toFixed(1)}
                  </p>
                  <img src="/img/Stars.svg" alt="Avaliações" />
                  <p>{modalLivro.usuarios.length} avaliações</p>
                </div>
                <p id="sinopse">{modalLivro.sinopse}</p>
                <ul id="extra-info">
                  <li>
                    <p>Idade de leitura</p>
                    <p>{modalLivro.idadeLeitura}+</p>
                  </li>
                  <li>
                    <p>Nº de páginas</p>
                    <p>{modalLivro.numeroPaginas}+</p>
                  </li>
                  <li>
                    <p>Autor</p>
                    <p>{modalLivro.autor.nome}</p>
                  </li>
                  <li>
                    <p>Editora</p>
                    <p>{modalLivro.editora.nome}</p>
                  </li>
                </ul>
                <div id="avaliacao">
                  <div>
                    <h3>Avaliações ({modalLivro.usuarios.length})</h3>
                    <p>
                      {(
                        modalLivro.usuarios.reduce(
                          (soma, usuario) => soma + usuario.nota,
                          0
                        ) / modalLivro.usuarios.length
                      ).toFixed(1)}
                    </p>
                    <img src="/img/Stars.svg" alt="Avaliações" />
                  </div>
                  <ul>
                    {[5, 4, 3, 2, 1].map((i) => (
                      <li key={i}>
                        <p>{i} estrelas</p>
                        <img src="/img/Bar.svg" alt="Barra de estrelas" />
                        <p>
                          {
                            modalLivro.usuarios.filter(
                              (usuario) => usuario.nota === i
                            ).length
                          }
                        </p>
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
              <div id="livro-compra">
                <p>R$ {modalLivro.preco.toFixed(2)}</p>
                <div>
                  <h3>R$ {(modalLivro.preco * 0.8).toFixed(2)}</h3>
                  <p>20% off.</p>
                </div>
                <ul>
                  <li>
                    <div className="flex">
                      <img src="/img/Book.svg" alt="Ícone de livro" />
                      <h4>Política de troca e devolução</h4>
                    </div>
                    <ul>
                      <li>Para compras feitas a menos de 2 semanas.</li>
                      <li>Não há estorno de valor na troca de item.</li>
                    </ul>
                  </li>
                  <li>
                    <div className="flex">
                      <img src="/img/Security.svg" alt="Ícone de proteção" />
                      <h4>Segurança e privacidade</h4>
                    </div>
                    <ul>
                      <li>Dados de transações protegidos.</li>
                      <li id="ultimo">Dados pessoais criptografados.</li>
                    </ul>
                  </li>
                </ul>
                <a href="#">Adicionar ao carrinho</a>
                <a
                  href="#"
                  onClick={async (e) => {
                    e.preventDefault();
                    e.target.classList.add("inative");
                    e.currentTarget.innerText = "Carregando";
                    const response = await fetch(
                      "https://webook-8d4j.onrender.com/api/pagamento",
                      {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(modalLivro.preco * 100),
                      }
                    );

                    const data = await response.json();
                    setClientSecret(data.clientSecret);
                    setModalAberto("payment");
                    setLivro(modalLivro.livro_id);
                    setModalLivro(null);
                    e.target.classList.remove("inative");
                  }}
                >
                  Comprar agora
                </a>
                <h5>Formas de Pagamento</h5>
                <img src="/img/Payments.png" alt="Formas de pagamento" />
              </div>
            </div>
          </div>
        )}
        {modalAberto == "payment" && clientSecret && (
          <StripeContainer
            clientSecret={clientSecret}
            aoClique={() => setModalAberto(null)}
            idLivro={livro}
            idUsuario={usuario.usuario_id}
          />
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
              <li key={livro.livro_id} onClick={() => setModalLivro(livro)}>
                <a href="#">
                  <img src={"/img/" + livro.caminhoLivro} />
                  <h2>
                    {livro.titulo.length > 21
                      ? livro.titulo.substring(0, 21) + "..."
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
