import { useState } from "react";
import { useUsuario } from "./providers/UsuarioProvider";
import { useLivrosUsuario } from "./providers/LivrosUsuarioProvider";
import "../public/css/profile.css";
import Footer from "./Footer";
import Links from "./Links";
import { v4 as uuidv4 } from "uuid";

function Profile() {
  const [modalAberto, setModalAberto] = useState(null);
  const [changePassword, setChangePassword] = useState(false);
  const [changeUsername, setChangeUsername] = useState(false);
  const [actualPassword, setActualPassword] = useState(null);
  const [newPassword, setNewPassword] = useState(null);
  const [newUsername, setUsername] = useState(null);
  const [newProfilePhoto, setNewProfilePhoto] = useState(null);
  const [usuarioLogado] = useUsuario();
  const [livrosUsuarioMock] = useLivrosUsuario();
  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosUsuarioMock);

  function filterFilms(termo = "") {
    setLivrosFiltrados(
      livrosUsuarioMock.filter((livroFiltrado) =>
        livroFiltrado.titulo.toLowerCase().includes(termo)
      )
    );
  }

  async function handleUpload(file) {
    const formData = new FormData();
    const uuid = uuidv4();
    formData.append("image-" + file.url, file);

    const res = await fetch(
      "https://api.imgbb.com/ " +
        uuid +
        "/upload?key=02649a0bafaed4123cfcc89e63003b10",
      {
        method: "POST",
        body: formData,
      }
    );

    const data = await res.json();
    return data;
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
                type="password"
                id="password"
                autoComplete="newUsername"
                disabled={!changeUsername}
                onChange={(e) => {
                  setChangeUsername(e.target.value);
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
                id="password"
                autoComplete="newPassword"
                disabled={changePassword}
                onChange={(e) => {
                  setChangePassword(e.target.value);
                }}
              />
              <label htmlFor="file-upload">Escolha uma foto de perfil</label>
              <label htmlFor="file-upload" class="custom-file-upload">
                Escolher imagem
              </label>
              <input
                type="file"
                accept="image/*"
                id="file-upload"
                onChange={(e) => handleUpload(e.target.files[0])}
              />
              <a
                href="#"
                onClick={(e) => {
                  e.preventDefault();
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
              <span>
                <img src="/img/Logout.svg" alt="" />
                <p>sair </p>
              </span>
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
