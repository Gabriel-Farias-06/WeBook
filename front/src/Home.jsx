import { useState, useEffect } from "react";
import "../public/css/style.css";
import { Link } from "react-router-dom";
import { useGeneros } from "./providers/GenerosProvider";
import { useLivros } from "./providers/LivrosProvider";
import Footer from "./components/Footer";
import Links from "./components/Links";
import Loading from "./components/Loading";
import { useUsuario } from "./providers/UsuarioProvider";
import StripeContainer from "./components/StripeContainer";
import { jwtDecode } from "jwt-decode";

function Home() {
  const { generos, generosLoading } = useGeneros();
  const { livros, livrosLoading } = useLivros();
  const { usuario, setUsuario, loading } = useUsuario();

  const [generoAtivo, setGeneroAtivo] = useState();
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [alarmPassword, setAlarmPassword] = useState(false);
  const [modalAberto, setModalAberto] = useState(null);
  const [clientSecret, setClientSecret] = useState(null);
  const [modalLivro, setModalLivro] = useState(null);
  const [livro, setLivro] = useState(null);
  const [livrosFiltrados, setLivrosFiltrados] = useState(null);
  const [carrinho, setCarrinho] = useState([]);
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    if (generos) setGeneroAtivo({ nome: "Todos", genero_id: "unique" });
  }, [generos]);

  useEffect(() => {
    if (livros) setLivrosFiltrados(livros);
  }, [livros]);

  useEffect(() => {
    const salvo = localStorage.getItem("carrinho");
    if (salvo) {
      setCarrinho(JSON.parse(salvo));
    }
  }, []);

  useEffect(() => {
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
  }, [carrinho]);

  useEffect(() => {
    const timeout = setTimeout(
      () => setNotifications((prev) => prev.slice(1)),
      60000 * 60
    );

    return () => clearTimeout(timeout);
  }, [notifications]);

  useEffect(() => {
    if (usuario && usuario.livros.length)
      addNotification(
        `Sua compra do livro ${usuario.livros[usuario.livros.length - 1].titulo} foi realizada com sucesso`,
        "/img/LivroIcone.png"
      );
  }, [usuario]);

  useEffect(() => {
    if (livros)
      addNotification(
        "Livros novos chegando na área, venha conferir os lançamentos da semana.",
        "/img/NewIcone.png"
      );
  }, [livros]);

  function addNotification(message, typePhoto) {
    setNotifications((prev) => {
      if (prev.some((notification) => notification.message === message))
        return prev;
      return [...prev, { message_id: Date.now(), message, typePhoto }];
    });
  }

  function filterFilms(termo = "") {
    if (generoAtivo.nome == "Todos")
      setLivrosFiltrados(
        livros.filter((livro) => livro.titulo.toLowerCase().includes(termo))
      );
    else {
      const filter = livros.filter((livro) =>
        livro.generos.includes(generoAtivo.genero_id)
      );

      setLivrosFiltrados(
        filter.filter((livroFiltrado) =>
          livroFiltrado.titulo.toLowerCase().includes(termo)
        )
      );
    }
  }

  async function getUser(token) {
    console.log(token);
    const decoded = jwtDecode(token);
    console.log(decoded);
    if (decoded.exp && decoded.exp < Date.now() / 1000) {
      setUsuario(null);
      return;
    }

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
    localStorage.setItem("token", token);
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
    const json = await response.json();
    getUser(json.token);
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
    const json = await login.json();
    getUser(json.token);
  }

  if (loading || generosLoading || livrosLoading) return <Loading />;

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
                filterFilms(termo);
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
              filterFilms(termo);
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
            <form
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
            </form>
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
              <p>
                {carrinho.length
                  ? "Produtos adicionados recentemente"
                  : "O carrinho está vazio"}
              </p>
              <div>
                {carrinho.length != 0 && (
                  <ul>
                    {carrinho.map((livro) => (
                      <li key={livro.livro_id}>
                        <img
                          src={livro.caminhoLivro}
                          alt={"Capa do livro " + livro.titulo}
                        />
                        <p id="titulo">{livro.titulo}</p>
                        <p>{livro.preco.toFixed(2)}</p>
                        <img
                          id="remove"
                          src="/img/Remove.svg"
                          alt="Remover livro"
                          onClick={() => {
                            setCarrinho((prev) =>
                              prev.filter(
                                (item) => item.livro_id != livro.livro_id
                              )
                            );
                          }}
                        />
                      </li>
                    ))}
                  </ul>
                )}
              </div>
              <a id="more">
                {carrinho.length
                  ? "Comprar agora"
                  : "Adicione produtos ao carrinho"}
              </a>
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
              <p>
                {notifications.length
                  ? "Notificações recebidas recentemente"
                  : "Você não tem notificações recentes"}
              </p>
              <div>
                <ul>
                  {notifications.map((message) => (
                    <li key={message.message_id}>
                      <img src={message.typePhoto} alt="Ícone de mensagem" />
                      <p>{message.message}</p>
                    </li>
                  ))}
                </ul>
              </div>
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
              <img src={modalLivro.caminhoLivro} />
              <div id="livro-meio">
                <h2>{modalLivro.titulo}</h2>
                <div id="first-avaliation">
                  <p>
                    {modalLivro.usuarios
                      ? (
                          modalLivro.usuarios.reduce(
                            (soma, usuario) => soma + usuario.nota,
                            0
                          ) / modalLivro.usuarios.length
                        ).toFixed(1)
                      : "0.0"}
                  </p>
                  <img src="/img/Stars.svg" alt="Avaliações" />
                  <p>
                    {modalLivro.usuarios ? modalLivro.usuarios.length : "0"}{" "}
                    avaliações
                  </p>
                </div>
                <p id="sinopse">{modalLivro.sinopse}</p>
                <ul id="extra-info">
                  <li>
                    <p>Idade para Leitura</p>
                    <p>{modalLivro.classificacaoIndicativa}</p>
                  </li>
                  <li>
                    <p>Nº de páginas</p>
                    <p>{modalLivro.numeroPaginas}</p>
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
                    <h3>
                      Avaliações (
                      {modalLivro.usuarios ? modalLivro.usuarios.length : "0"})
                    </h3>
                    <p>
                      {modalLivro.usuarios
                        ? (
                            modalLivro.usuarios.reduce(
                              (soma, usuario) => soma + usuario.nota,
                              0
                            ) / modalLivro.usuarios.length
                          ).toFixed(1)
                        : "0.0"}
                    </p>
                    <img src="/img/Stars.svg" alt="Avaliações" />
                  </div>
                  <ul>
                    {[5, 4, 3, 2, 1].map((i) => (
                      <li key={i}>
                        <p>{i} estrelas</p>
                        <img src="/img/Bar.svg" alt="Barra de estrelas" />
                        <p>
                          {modalLivro.usuarios
                            ? modalLivro.usuarios.filter(
                                (usuario) => usuario.nota === i
                              ).length
                            : "0"}
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
                <a
                  className={
                    carrinho.some(
                      (item) => item.livro_id === modalLivro.livro_id
                    )
                      ? "inative"
                      : ""
                  }
                  href="#"
                  onClick={() => {
                    if (
                      !carrinho.some(
                        (item) => item.livro_id === modalLivro.livro_id
                      )
                    )
                      setCarrinho((prev) => {
                        return [...prev, modalLivro];
                      });
                  }}
                >
                  {carrinho.some(
                    (item) => item.livro_id === modalLivro.livro_id
                  )
                    ? "Produto no carrinho"
                    : "Adicionar ao carrinho"}
                </a>
                <a
                  href="#"
                  onClick={async (e) => {
                    e.preventDefault();
                    if (!usuario) {
                      setModalLivro(null);
                      setModalAberto("login");
                      return;
                    }
                    e.target.classList.add("inative");
                    e.currentTarget.innerText = "Carregando";
                    const response = await fetch(
                      "https://webook-8d4j.onrender.com/api/pagamento",
                      {
                        method: "POST",
                        headers: {
                          "Content-Type": "application/json",
                          Authorization: `Bearer ${usuario.token}`,
                        },
                        body: JSON.stringify(modalLivro.preco * 100),
                      }
                    );

                    const data = await response.json();
                    setClientSecret(data.clientSecret);
                    setLivro(modalLivro.livro_id);
                    setModalAberto("payment");
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
            idLivro={livro}
            idUsuario={usuario.usuario_id}
            setModalAberto={setModalAberto}
          />
        )}

        {modalAberto == "payment-error" && (
          <div
            className="modal modal-error"
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
                Ocorreu um erro ao realizar tentar realizar o pagamento,
                verifique seus dados e se não houver resolução, nos contate.
              </p>
              <a href="#" onClick={() => setModalAberto("payment")}>
                Voltar
              </a>
            </div>
          </div>
        )}
      </header>

      {generos && (
        <article
          aria-labelledby="categorias"
          id="categories"
          className="container"
        >
          <ul>
            <li
              key="unique"
              className={generoAtivo?.genero_id == "unique" ? "active" : ""}
              onClick={() => {
                document.getElementById("searchInput").value = "";
                setGeneroAtivo({ nome: "Todos", genero_id: "unique" });
                filterFilms();
              }}
            >
              <a href="#">Todos</a>
            </li>
            {generos.map((genero) => {
              return (
                <li
                  key={genero.genero_id}
                  className={
                    generoAtivo?.genero_id == genero.genero_id ? "active" : ""
                  }
                  onClick={() => {
                    document.getElementById("searchInput").value = "";
                    setGeneroAtivo(genero);
                    filterFilms();
                  }}
                >
                  <a href="#">{genero.nome}</a>
                </li>
              );
            })}
          </ul>
        </article>
      )}

      {livrosFiltrados && livrosFiltrados.length == 0 ? (
        <h2 id="naoEncontrou">Não há resultados para sua pesquisa</h2>
      ) : (
        <article aria-labelledby="books" id="books" className="container">
          <ul>
            {livrosFiltrados &&
              livrosFiltrados.map((livro) => (
                <li key={livro.livro_id} onClick={() => setModalLivro(livro)}>
                  <a href="#">
                    <img src={livro.caminhoLivro} />
                    <h2>
                      {livro.titulo.length > 21
                        ? livro.titulo.substring(0, 21) + "..."
                        : livro.titulo}
                    </h2>
                    <div>
                      <p>{"R$ " + livro.preco.toFixed(2)}</p>
                      <img src="/img/Star.svg" />
                      <p id="avaliacao">
                        {livro.usuarios
                          ? (
                              livro.usuarios.reduce(
                                (soma, usuario) => soma + usuario.nota,
                                0
                              ) / livro.usuarios.length
                            ).toFixed(1)
                          : "0.0"}
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
