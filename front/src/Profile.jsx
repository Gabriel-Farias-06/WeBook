import { useEffect, useState } from "react";
import { useUsuario } from "./providers/UsuarioProvider";
import "./css/profile.css";
import Footer from "./components/Footer";
import Links from "./components/Links";
import Loading from "./components/Loading";
import { useGeneros } from "./providers/GenerosProvider";
import { IMaskInput } from "react-imask";
import { Link, Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { createClient } from "@supabase/supabase-js";
import { useLivros } from "./providers/LivrosProvider";

function Profile() {
  const navigate = useNavigate();
  const [clientSecret, setClientSecret] = useState(null);
  const [livrosAComprar, setLivrosAComprar] = useState(null);

  const [notifications, setNotifications] = useState([]);
  const [modalAberto, setModalAberto] = useState(null);
  const [changePassword, setChangePassword] = useState(false);
  const [changeUsername, setChangeUsername] = useState(false);
  const [actualPassword, setActualPassword] = useState(null);
  const [newPassword, setNewPassword] = useState(null);
  const [newUsername, setNewUsername] = useState(null);
  const [newProfilePhoto, setNewProfilePhoto] = useState(null);
  const [newBookPhoto, setNewBookPhoto] = useState(null);
  const [newEbook, setNewEbook] = useState(null);
  const [newBook, setNewBook] = useState(null);
  const [isbn, setIsbn] = useState("");
  const { usuario, loading, setUsuario, setUpdateUsuario } = useUsuario();
  const { generos, generosLoading } = useGeneros();
  const [alarmPassword, setAlarmPassword] = useState(false);
  const [generosOptions, setGenerosOptions] = useState([]);
  const [carrinho, setCarrinho] = useState([]);
  const [correctPassword, setCorrectPassword] = useState(true);
  const [deletePassword, setDeletePassword] = useState(null);
  const [deleteIsbn, setDeleteIsbn] = useState(null);
  const { livros, livrosLoading, setUpdateLivro } = useLivros();

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
        `Sua compra do livro ${
          usuario.livros[usuario.livros.length - 1].titulo
        } foi realizada com sucesso`,
        "./img/LivroIcone.png"
      );
  }, [usuario]);

  useEffect(() => {
    if (livros)
      addNotification(
        "Livros novos chegando na área, venha conferir os lançamentos da semana.",
        "./img/NewIcone.png"
      );
  }, [livros]);

  if (loading || generosLoading || livrosLoading) <Loading />;

  const [livrosUsuario, setLivrosUsuario] = useState([]);
  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosUsuario);

  useEffect(() => {
    if (usuario) setLivrosUsuario(usuario.livros);
  }, [usuario]);

  useEffect(() => {
    if (livrosUsuario) setLivrosFiltrados(livrosUsuario);
  }, [livrosUsuario]);

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
        `Sua compra do livro ${
          usuario.livros[usuario.livros.length - 1].titulo
        } foi realizada com sucesso`,
        "./img/LivroIcone.png"
      );
  }, [usuario]);

  useEffect(() => {
    if (livros)
      addNotification(
        "Livros novos chegando na área, venha conferir os lançamentos da semana.",
        "./img/NewIcone.png"
      );
  }, [livros]);

  function addNotification(message, typePhoto) {
    setNotifications((prev) => {
      if (prev.some((notification) => notification.message === message))
        return prev;
      return [...prev, { message_id: Date.now(), message, typePhoto }];
    });
  }

  function filterBooks(termo = "") {
    setLivrosFiltrados(
      livrosUsuario.filter((livroFiltrado) =>
        livroFiltrado.titulo.toLowerCase().includes(termo)
      )
    );
  }

  async function deleteBook() {
    try {
      const res = await fetch(
        `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/livro/${deleteIsbn}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${usuario.token}`,
          },
        }
      );

      if (res.status == 200) {
        setUpdateLivro(true);
      } else setModalAberto("error-delete");
    } catch (e) {
      console.error("Erro ao deletar o perfil: " + e);
    }
  }

  async function deleteProfile() {
    try {
      const res = await fetch(
        `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/usuario/${usuario.email}/${deletePassword}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${usuario.token}`,
          },
        }
      );

      if (res.status == 200) {
        setUsuario(null);
        navigate("/");
      } else setModalAberto("error-delete");
    } catch (e) {
      console.error("Erro ao deletar o perfil: " + e);
    }
  }

  async function handleUploadProfilePhoto() {
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

  async function uploadNewEbook() {
    const supabase = createClient(
      "https://uryeqjptemdyznogbeus.supabase.co",
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVyeWVxanB0ZW1keXpub2diZXVzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkzMDI4OTcsImV4cCI6MjA2NDg3ODg5N30.h-xARu8XNys8En6VbKaH_hiBO-oBRPOzUxkgSh3dOPw"
    );

    const { data, error } = await supabase.storage
      .from("ebooks")
      .upload(`epubs/${Date.now()}-${newEbook.name}`, newEbook);

    if (error) return null;
    return `https://uryeqjptemdyznogbeus.supabase.co/storage/v1/object/public/ebooks/${data.path}`;
  }

  async function updateUser() {
    const nome = newUsername ? newUsername : usuario.nome;
    const senha = newPassword ? newPassword : null;
    let caminhoFoto = usuario.caminhoFoto;
    if (newProfilePhoto) {
      const resul = await handleUploadProfilePhoto();
      caminhoFoto = resul.data.url;
    }

    const res = await fetch(
      "https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/usuario",
      {
        method: "PUT",
        body: JSON.stringify({
          nome,
          email: usuario.email,
          senhaAtual: actualPassword,
          senhaNova: senha,
          caminhoFoto,
        }),
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${usuario.token}`,
        },
      }
    );

    if (res.status == 401) {
      setCorrectPassword(false);
      return;
    }

    setCorrectPassword(true);

    setUsuario({
      nome,
      email: usuario.email,
      senha,
      caminhoFoto,
      livros: usuario.livros,
      token: usuario.token,
    });

    setModalAberto(null);
  }

  function handleChangeNewBook(e) {
    const { name, value } = e.target;
    setNewBook((rest) => ({
      ...rest,
      [name]: value,
    }));
  }

  async function uploadNewBookPhoto() {
    if (!newBookPhoto) return false;

    const formData = new FormData();
    formData.append("image", newBookPhoto);
    formData.append("key", "02649a0bafaed4123cfcc89e63003b10");

    try {
      const res = await fetch("https://api.imgbb.com/1/upload", {
        method: "POST",
        body: formData,
      });

      const data = await res.json();
      return data;
    } catch (err) {
      console.error("Erro de rede: ", err);
    }
  }

  async function createBook() {
    const data = await uploadNewBookPhoto();
    const bookPath = await uploadNewEbook();

    if (!data || !bookPath) return;

    const nomeAutor = newBook.autor.trim().split(" ")[0];
    const sobrenomeAutor = newBook.autor.trim().split(" ").slice(1).join(" ");

    const newAutor = await fetch(
      `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/autor`,
      {
        method: "POST",
        body: JSON.stringify({
          nome: nomeAutor.toUpperCase(),
          sobrenome: sobrenomeAutor.toUpperCase(),
        }),
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${usuario.token}`,
        },
      }
    );

    const autor = await newAutor.json();
    const autor_id = autor.autor_id;

    const editoraResponse = await fetch(
      `https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/editora`,
      {
        method: "POST",
        body: JSON.stringify({
          nome: newBook.editora.toUpperCase(),
        }),
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${usuario.token}`,
        },
      }
    );

    const editora = await editoraResponse.json();
    const editora_id = editora.editora_id;

    const book = {
      isbn: newBook.isbn,
      titulo: newBook.titulo,
      sinopse: newBook.sinopse,
      numeroPaginas: parseInt(newBook.numeroPaginas),
      preco: parseFloat(newBook.preco),
      classificacaoIndicativa: newBook.classificacaoIndicativa,
      caminhoLivro: data.data.url,
      generos_id: generosOptions,
      autor_id,
      editora_id,
      caminhoEbook: bookPath,
    };

    setGenerosOptions([]);

    try {
      const res = await fetch(
        "https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/livro",
        {
          method: "POST",
          body: JSON.stringify(book),
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${usuario.token}`,
          },
        }
      );

      if (res.status == 201) {
        setUpdateLivro(true);
        setModalAberto("livro-criado");
      } else {
        setModalAberto("livro-error");
      }
    } catch (e) {
      console.error("Erro de rede: ", e);
    }
  }

  if (!usuario) return <Navigate to="/" />;

  return (
    <div>
      <header className="container" id="header">
        <Link to="/" id="logo">
          <span>W</span>e<span>B</span>ook
        </Link>
        <div id="input-wrapper">
          <input
            type="text"
            id="searchInput"
            placeholder="pesquise seu livro"
            autoComplete="on"
            onKeyUp={(e) => {
              if (e.key == "Enter") {
                const termo = e.currentTarget.value.toLowerCase();
                filterBooks(termo);
              }
            }}
          />
          <img
            src="./img/Search.svg"
            className="search-icon"
            onClick={() => {
              const termo = document
                .getElementById("searchInput")
                .value.toLowerCase();
              filterBooks(termo);
            }}
          />
        </div>
        <div id="symbols">
          <a href="#">
            <img
              src="./img/Cart.svg"
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
              src="./img/Notification.svg"
              alt="Notificações"
              onClick={() => {
                if (usuario) {
                  document.body.style.overflow = "hidden";
                  setModalAberto("notifications");
                } else setModalAberto("login");
              }}
            />
          </a>
          <a href="#">
            <img
              src="./img/Settings.svg"
              alt="Configurações"
              onClick={() => {
                setModalAberto("config");
              }}
            />
          </a>
        </div>
        {modalAberto == "edit-perfil" && (
          <div
            className="modal"
            id="cadastro-bg"
            onClick={() => setModalAberto(null)}
          >
            <img src="./img/Close.svg" onClick={() => setModalAberto(null)} />
            <form
              className="modal-content"
              id="cadastro"
              onClick={(e) => e.stopPropagation()}
            >
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
              {!correctPassword && <p>Senha Incorreta</p>}
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
              <div className="flex-upload">
                <label htmlFor="file-upload" className="custom-file-upload">
                  Escolher imagem
                </label>
                {newProfilePhoto && <p>{newProfilePhoto.name}</p>}
              </div>
              <input
                type="file"
                accept=".jpg, .jpeg, .png, .gif, .bmp, .webp, .tiff, .ico"
                id="file-upload"
                onChange={(e) => setNewProfilePhoto(e.target.files[0])}
              />
              <button
                type="submit"
                onClick={async (e) => {
                  e.preventDefault();
                  e.target.classList.add("inative");
                  e.currentTarget.innerText = "Carregando";
                  await updateUser();
                  e.target.classList.remove("inative");
                }}
              >
                Atualizar os dados
              </button>
            </form>
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
                <img src="./img/ConfigBook.svg" alt="" />
                <p>adicionar livro</p>
              </span>
              <span
                onClick={() => {
                  setModalAberto("excluir-livro");
                }}
              >
                <img src="./img/Delete.svg" alt="" />
                <p>excluir livro </p>
              </span>
              <span
                onClick={() => {
                  setModalAberto("excluir-conta");
                }}
              >
                <img src="./img/Delete.svg" alt="Excluir conta ícone" />
                <p>excluir conta </p>
              </span>
              <span
                onClick={() => {
                  setUsuario(null);
                  navigate("/");
                }}
              >
                <img src="./img/Logout.svg" alt="" />
                <p>sair </p>
              </span>
            </div>
          </div>
        )}
        {modalAberto == "excluir-conta" && (
          <div className="modal" onClick={() => setModalAberto(null)}>
            <img src="./img/Close.svg" onClick={() => setModalAberto(null)} />
            <div
              className="modal-content delete"
              onClick={(e) => e.stopPropagation()}
            >
              <span></span>
              <h3>Tem certeza que deseja excluir seu perfil?</h3>
              <p>
                Todos os dados da sua conta serão perdidos, inclusive seus
                livros comprados, marcados como favorito e não serão
                reembolsados.
              </p>
              <label htmlFor="passwordDelete">Insira sua senha</label>
              <input
                type="password"
                name="passwordDelete"
                onChange={(e) => {
                  setDeletePassword(e.target.value);
                }}
              />
              <div>
                <a href="#" onClick={() => setModalAberto(null)}>
                  Voltar
                </a>
                <a
                  href="#"
                  onClick={() => {
                    deleteProfile();
                    setModalAberto(null);
                  }}
                >
                  Excluir perfil
                </a>
              </div>
            </div>
          </div>
        )}
        {modalAberto == "excluir-livro" && (
          <div className="modal" onClick={() => setModalAberto(null)}>
            <img src="./img/Close.svg" onClick={() => setModalAberto(null)} />
            <div
              className="modal-content delete"
              onClick={(e) => e.stopPropagation()}
            >
              <span></span>
              <h3>Tem certeza que deseja excluir seu livro?</h3>
              <p>
                Não será possível novas compras do livro mas os leitores atuais
                continuarão a ter acesso a ele.
              </p>
              <label htmlFor="isbnDelete">Insira a isbn do livro</label>
              <IMaskInput
                mask="000-0-00-000000-0"
                definitions={{
                  0: /[0-9]/,
                }}
                value={deleteIsbn}
                unmask={false}
                id="isbn"
                onAccept={(val) => setDeleteIsbn(val)}
                name="isbnDelete"
                maxLength={17}
                required
              />
              <div>
                <a href="#" onClick={() => setModalAberto(null)}>
                  Voltar
                </a>
                <a
                  href="#"
                  onClick={() => {
                    deleteBook();
                    setModalAberto(null);
                  }}
                >
                  Excluir livro
                </a>
              </div>
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
            <form
              className="modal-content"
              id="cadastro"
              onClick={(e) => e.stopPropagation()}
            >
              <h3>Cadastrar livros</h3>
              <div id="container-flex">
                <p className="protect">Dados pessoais criptografados.</p>
              </div>
              <label htmlFor="isbn">Preencha a isbn</label>
              <IMaskInput
                mask="000-0-00-000000-0"
                definitions={{
                  0: /[0-9]/,
                }}
                value={isbn}
                unmask={false}
                id="isbn"
                onAccept={(val) => setIsbn(val)}
                onChange={handleChangeNewBook}
                name="isbn"
                maxLength={17}
                required
              />
              <label htmlFor="titulo">Preencha o título</label>
              <input
                type="text"
                id="titulo"
                onChange={handleChangeNewBook}
                name="titulo"
                required
              />
              <label htmlFor="sinopse">Preencha a sinopse</label>
              <input
                type="text"
                id="sinopse"
                onChange={handleChangeNewBook}
                name="sinopse"
                required
              />
              <label htmlFor="numeroPaginas">
                Preencha o número de páginas
              </label>
              <input
                type="number"
                id="numeroPaginas"
                onChange={handleChangeNewBook}
                name="numeroPaginas"
                required
              />
              <label htmlFor="preco">Preencha o preço</label>
              <input
                type="text"
                id="preco"
                onChange={handleChangeNewBook}
                name="preco"
                required
              />
              <div className="flex-upload">
                <label htmlFor="capa" className="custom-file-upload">
                  Envie a foto da capa
                </label>
                {newBookPhoto && <p>{newBookPhoto.name}</p>}
                {!newBookPhoto && <p>Escolha a imagem do livro</p>}
              </div>
              <input
                type="file"
                accept=".jpg, .jpeg, .png, .gif, .bmp, .webp, .tiff, .ico"
                name="capa"
                id="capa"
                onChange={(e) => setNewBookPhoto(e.target.files[0])}
              />
              <label htmlFor="classificacao">
                Preencha a classificação indicativa
              </label>
              <select
                default=""
                name="classificacaoIndicativa"
                id="classificacao"
                onChange={(e) => {
                  document
                    .getElementById("defaultClassification")
                    .setAttribute("disabled", true);
                  handleChangeNewBook(e);
                }}
                required
              >
                <option value="" id="defaultClassification"></option>
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
              <input
                type="text"
                id="autor"
                name="autor"
                onChange={handleChangeNewBook}
                required
              />
              <label htmlFor="editora">Preencha o nome da editora</label>
              <input
                type="text"
                id="editora"
                name="editora"
                onChange={handleChangeNewBook}
                required
              />
              <div className="flex-upload">
                <label htmlFor="epub" className="custom-file-upload">
                  Envie o livro em epub
                </label>
                {newEbook && <p>{newEbook.name}</p>}
                {!newEbook && <p>Escolha o arquivo do livro</p>}
              </div>
              <input
                type="file"
                accept=".epub"
                name="epub"
                id="epub"
                onChange={(e) => setNewEbook(e.target.files[0])}
              />
              <label htmlFor="generos">Selecione os gêneros do livro</label>
              <div id="flex-generos">
                {generos.map((genero) =>
                  genero == generos[0] ? null : (
                    <label className="generos" key={genero.genero_id}>
                      <input
                        type="checkbox"
                        value={genero.genero_id}
                        onChange={(e) => {
                          if (e.target.checked)
                            setGenerosOptions([
                              ...generosOptions,
                              e.target.value,
                            ]);
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
              <button
                type="submit"
                onClick={async (e) => {
                  e.preventDefault();
                  if (!newBookPhoto) return;
                  e.target.classList.add("inative");
                  e.target.innerText = "Carregando";
                  await createBook();
                  e.target.classList.remove("inative");
                }}
              >
                Cadastrar livro
              </button>
            </form>
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
                          src="./img/Remove.svg"
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
              <a
                id="more"
                onClick={async (e) => {
                  e.preventDefault();
                  if (carrinho.length == 0) return setModalAberto(null);
                  e.target.classList.add("inative");
                  e.currentTarget.innerText = "Carregando";
                  const total =
                    carrinho.reduce(
                      (acumulator, produto) => produto.preco + acumulator,
                      0
                    ) * 0.8;

                  const response = await fetch(
                    "https://app-d94fb6f2-81df-40a2-b55c-f206a66d9298.cleverapps.io/api/pagamento",
                    {
                      method: "POST",
                      headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${usuario.token}`,
                      },
                      body: JSON.stringify(total * 100),
                    }
                  );

                  const data = await response.json();
                  setClientSecret(data.clientSecret);
                  setLivrosAComprar(carrinho.map((livro) => livro.livro_id));
                  setModalAberto("payment");
                  e.target.classList.remove("inative");
                }}
              >
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
        {modalAberto == "payment" && clientSecret && (
          <StripeContainer
            clientSecret={clientSecret}
            idLivros={livrosAComprar}
            idUsuario={usuario.usuario_id}
            setModalAberto={setModalAberto}
            token={usuario.token}
            setUpdateLivro={setUpdateLivro}
            setUpdateUsuario={setUpdateUsuario}
          />
        )}
      </header>
      <main>
        <img
          src={
            usuario.caminhoFoto
              ? usuario.caminhoFoto
              : "./img/UserDefaultBigger.png"
          }
        />
        <h2>{usuario.nome ? usuario.nome : "userDeafultName"}</h2>
        <p>{usuario.email}</p>
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
        {livrosFiltrados.length ? (
          <ul>
            {livrosFiltrados.map((livro) => (
              <li key={livro.livro_id}>
                <div
                  href="#"
                  onClick={() => {
                    navigate("/livro", { state: livro });
                  }}
                >
                  <img src={livro.caminhoLivro} />
                </div>
              </li>
            ))}
          </ul>
        ) : (
          <h2 id="no-books">Seus livros estarão disponíveis aqui</h2>
        )}
      </article>

      <Links />
      <Footer />
    </div>
  );
}

export default Profile;
