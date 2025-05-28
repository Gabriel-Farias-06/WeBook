import { useState } from "react";
import { useUsuario } from "./providers/UsuarioProvider";
import { useLivrosUsuario } from "./providers/LivrosUsuarioProvider";
import "../public/css/profile.css";
import Footer from "./Footer";
import Links from "./Links";
import { useGeneros } from "./providers/GenerosProvider";

function Profile() {
  const [modalAberto, setModalAberto] = useState(null);
  const [changePassword, setChangePassword] = useState(false);
  const [changeUsername, setChangeUsername] = useState(false);
  const [actualPassword, setActualPassword] = useState(null);
  const [newPassword, setNewPassword] = useState(null);
  const [newUsername, setNewUsername] = useState(null);
  const [newProfilePhoto, setNewProfilePhoto] = useState(null);
  const [usuarioLogado, setUsuarioLogado] = useUsuario();
  const [livrosUsuarioMock] = useLivrosUsuario();
  const [generosMock] = useGeneros();
  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosUsuarioMock);
  const [alarmPassword, setAlarmPassword] = useState(false);
  const [generosOptions, setGenerosOptions] = useState(null);

  function filterFilms(termo = "") {
    setLivrosFiltrados(
      livrosUsuarioMock.filter((livroFiltrado) =>
        livroFiltrado.titulo.toLowerCase().includes(termo)
      )
    );
  }

  async function handleUpload() {
    if (!newProfilePhoto) return;

    const formData = new FormData();
    formData.append("image", newProfilePhoto);
    formData.append("key", "02649a0bafaed4123cfcc89e63003b10");

    try {
      const res = await fetch("https://api.imgbb.com/1/upload", {
        method: "POST",
        body: formData,
      });

      const data = await res.json();
      return data;
    } catch (err) {
      console.error("Erro de rede:", err);
    }
  }

  async function updateUser() {
    if (usuarioLogado.senha !== actualPassword) return;
    const nome = newUsername ? newUsername : usuarioLogado.nome;
    const senha = newPassword ? newPassword : usuarioLogado.senha;
    let caminhoFoto;
    if (newProfilePhoto) {
      const data = await handleUpload();
      caminhoFoto = data.data.url;
    }

    console.log(usuarioLogado);

    const res = await fetch("https://webook-8d4j.onrender.com/api/usuario", {
      method: "PUT",
      body: JSON.stringify({
        nome,
        email: usuarioLogado.email,
        senha,
        caminhoFoto,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    });

    setUsuarioLogado({
      nome,
      email: usuarioLogado.email,
      senha,
      caminhoFoto,
    });
    setModalAberto(null);

    return await res.json();
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
              document.body.style.overflow = "hidden";
              setModalAberto("shopping");
            }}
          />
        </a>
        <a href="#">
          <img
            src="/img/Notification.svg"
            alt="Notificações"
            onClick={() => {
              document.body.style.overflow = "hidden";
              setModalAberto("notifications");
            }}
          />
        </a>
        <a href="#">
          <img
            src="/img/Settings.svg"
            alt="Configurações"
            onClick={() => {
              setModalAberto("config");
            }}
          />
        </a>
        {modalAberto == "edit-perfil" && (
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
              <h3>Alterar dados</h3>
              <div id="container-flex">
                <p className="protect">Dados pessoais criptografados.</p>
              </div>
              <label htmlFor="pastPassword">Insira sua senha atual</label>
              <input
                type="password"
                id="pastPassword"
                autoComplete="current-password"
                onChange={(e) => {
                  setActualPassword(e.target.value);
                }}
              />
              <div id="checkbox-wrapper">
                <input
                  type="checkbox"
                  id="optionUsername"
                  checked={changeUsername}
                  onChange={() => {
                    setChangeUsername(!changeUsername);
                  }}
                />
                <span
                  id="checkmark"
                  onClick={() => {
                    setChangeUsername(!changeUsername);
                  }}
                ></span>
                <label htmlFor="optionUsername">
                  Deseja alterar seu nome de usuário?
                </label>
              </div>
              <label htmlFor="newUsername">
                Insira seu novo nome de usuário
              </label>
              <input
                type="text"
                id="newUsername"
                autoComplete="newUsername"
                disabled={!changeUsername}
                onChange={(e) => {
                  setNewUsername(e.target.value);
                }}
              />
              <div id="checkbox-wrapper">
                <input
                  type="checkbox"
                  id="optionPassword"
                  checked={!changePassword}
                  onChange={() => {
                    setChangePassword(!changePassword);
                  }}
                />
                <span
                  id="checkmark"
                  onClick={() => {
                    setChangePassword(!changePassword);
                  }}
                ></span>
                <label htmlFor="optionPassword">
                  Deseja alterar sua senha?
                </label>
              </div>
              <label htmlFor="newPassword">Insira sua nova senha</label>
              <input
                type="password"
                id="newPassword"
                autoComplete="newPassword"
                disabled={changePassword}
                onChange={(e) => {
                  const regexp =
                    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+=[\]{}|;:'",.<>?/`~-])(?!.*\s).{8,}$/;
                  if (!regexp.test(e.target.value)) setAlarmPassword(true);
                  else setAlarmPassword(false);
                  setNewPassword(e.target.value);
                }}
              />
              {alarmPassword && !changePassword && (
                <p>
                  A senha deve conter 8 caracteres, maiúsculas, minúsculas,
                  números e símbolos!
                </p>
              )}

              <label htmlFor="file-upload">Escolha uma foto de perfil</label>
              <div id="flex-upload">
                <label htmlFor="file-upload" className="custom-file-upload">
                  Escolher imagem
                </label>
                {newProfilePhoto && <p>{newProfilePhoto.name}</p>}
              </div>
              <input
                type="file"
                accept="image/*"
                id="file-upload"
                onChange={(e) => setNewProfilePhoto(e.target.files[0])}
              />
              <a
                href="#"
                onClick={async (e) => {
                  e.preventDefault();
                  e.target.classList.add("inative");
                  e.currentTarget.innerText = "Carregando";
                  await updateUser();
                  e.target.classList.remove("inative");
                }}
              >
                Atualizar os dados
              </a>
            </div>
          </div>
        )}
        {modalAberto == "config" && (
          <div
            className="modal config"
            id="cadastro-bg"
            onClick={() => setModalAberto(null)}
          >
            <div className="modal-content" onClick={(e) => e.stopPropagation()}>
              <span
                onClick={() => {
                  setModalAberto("cadastro-livro");
                  document.body.style.overflow = "hidden";
                }}
              >
                <img src="/img/Logout.svg" alt="" />
                <p>adicionar livro</p>
              </span>
              <span>
                <img src="/img/Logout.svg" alt="" />
                <p>sair </p>
              </span>
            </div>
          </div>
        )}
        {modalAberto == "cadastro-livro" && (
          <div
            className="modal"
            id="cadastro-bg"
            onClick={() => {
              setModalAberto(null);
              document.body.style.overflow = "auto";
            }}
          >
            <div
              className="modal-content"
              id="cadastro"
              onClick={(e) => e.stopPropagation()}
            >
              <h3>Cadastrar livros</h3>
              <div id="container-flex">
                <p className="protect">Dados pessoais criptografados.</p>
              </div>
              <label htmlFor="isbn">Preencha a isbn</label>
              <input type="text" id="isbn" />
              <label htmlFor="titulo">Preencha o título</label>
              <input type="text" id="titulo" />
              <label htmlFor="sinopse">Preencha a sinopse</label>
              <input type="text" id="sinopse" />
              <label htmlFor="numeroPaginas">
                Preencha o número de páginas
              </label>
              <input type="number" id="numeroPaginas" />
              <label htmlFor="preco">Preencha o preço</label>
              <input type="text" id="preco" />
              <label htmlFor="capa">Envie a foto da capa</label>
              <input type="file" id="capa" />
              <label htmlFor="classificacao">
                Preencha a classificação indicativa
              </label>
              <select value="" name="classificacao" id="classificacao">
                <option value="LIVRE">Livre</option>
                <option value="DEZ">10+</option>
                <option value="DOZE">12+</option>
                <option value="QUATORZE">14+</option>
                <option value="DEZESSEIS">16+</option>
                <option value="DEZOITO">18+</option>
              </select>
              <label htmlFor="autor">
                Preencha o nome e sobrenome do autor
              </label>
              <input type="text" id="autor" />
              <label htmlFor="generos">Selecione os gêneros do livro</label>
              <div id="flex-generos">
                {generosMock.map((genero) =>
                  genero == generosMock[0] ? null : (
                    <label className="generos">
                      <input
                        type="checkbox"
                        value={genero.nome}
                        onChange={(e) => {
                          if (e.target.checked)
                            setGenerosOptions(
                              ...generosOptions,
                              e.target.value
                            );
                          else
                            setGenerosOptions(
                              generosOptions.filter(
                                (genero) => genero != e.target.value
                              )
                            );
                        }}
                      />
                      {genero.nome}
                    </label>
                  )
                )}
              </div>
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
                  {livrosUsuarioMock.map((livro) => (
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
                  {livrosUsuarioMock.map((livro) => (
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
      </header>
      <main>
        <img
          src={
            usuarioLogado.caminhoFoto
              ? usuarioLogado.caminhoFoto
              : "/img/UserDefaultBigger.png"
          }
        />
        <h2>{usuarioLogado.nome ? usuarioLogado.nome : "userDeafultName"}</h2>
        <p>{usuarioLogado.email}</p>
        <a
          href="#"
          onClick={(e) => {
            e.preventDefault();
            setModalAberto("edit-perfil");
          }}
        >
          Editar
        </a>
      </main>
      <article aria-labelledby="livros" id="booksUser">
        <h2>Minha Coleção</h2>
        <p>Livros comprados</p>
        <ul>
          {livrosFiltrados.map((livro) => (
            <li key={livro.livro_id}>
              <a href="#">
                <img src={"/img/" + livro.caminhoLivro} />
              </a>
            </li>
          ))}
        </ul>
      </article>

      <Links />
      <Footer />
    </div>
  );
}

export default Profile;
