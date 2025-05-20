import { useState } from "react";
import "../public/css/style.css";

const generosMock = [
  { genero_id: "0000-zzzz", nome: "Todos" },
  { genero_id: "1001-aaaa", nome: "Suspense" },
  { genero_id: "1002-bbbb", nome: "Mistério" },
  { genero_id: "1003-cccc", nome: "Fantasia" },
  { genero_id: "1004-dddd", nome: "Aventura" },
  { genero_id: "1005-eeee", nome: "Romance" },
  { genero_id: "1006-ffff", nome: "Psicologia" },
  { genero_id: "1007-gggg", nome: "Religião" },
  { genero_id: "1008-hhhh", nome: "Ciência" },
  { genero_id: "0110-zzzz", nome: "Todos" },
  { genero_id: "1111-aaaa", nome: "Suspense" },
  { genero_id: "1112-bbbb", nome: "Mistério" },
  { genero_id: "1113-cccc", nome: "Fantasia" },
  { genero_id: "1114-dddd", nome: "Aventura" },
  { genero_id: "1115-eeee", nome: "Romance" },
];

const livrosMock = [
  {
    livro_id: "1a2b3c4d-0001-aaaa-bbbb-111122223333",
    isbn: "978-3-16-148410-0",
    titulo: "Orgulho e preconceito",
    sinopse:
      "Um grupo de jovens descobre segredos sombrios em uma casa antiga.",
    numeroPaginas: 320,
    preco: 49.9,
    caminhoLivro: "/livros/misterio-casa-abandonada.png",
    classificacaoIndicativa: "12+",
    autor: { nome: "Lucas Silva", nacionalidade: "Brasileiro" },
    editora: { nome: "Editora Sombras", cnpj: "12.345.678/0001-90" },
    usuarios: [
      { nome: "Joana", email: "joana@email.com", nota: 5 },
      { nome: "Carlos", email: "carlos@email.com", nota: 4 },
    ],
    generos: ["1001-aaaa", "1002-bbbb"], // Suspense, Mistério
  },
  {
    livro_id: "1a2b3c4d-0002-bbbb-cccc-sasad",
    isbn: "978-0-12-345678-9",
    titulo: "A empregada",
    sinopse: "Uma aventura épica em um mundo de máquinas e magia.",
    numeroPaginas: 528,
    preco: 59.9,
    caminhoLivro: "/livros/cronicas-ferro.png",
    classificacaoIndicativa: "14+",
    autor: { nome: "Ana Torres", nacionalidade: "Portuguesa" },
    editora: { nome: "Editora Fagulha", cnpj: "98.765.432/0001-12" },
    usuarios: [
      { nome: "Marcos", email: "marcos@email.com", nota: 4 },
      { nome: "Beatriz", email: "beatriz@email.com", nota: 5 },
    ],
    generos: ["1003-cccc", "1004-dddd"], // Fantasia, Aventura
  },
  {
    livro_id: "1a2b3c4d-0003-cccc-ytryt-777788889999",
    isbn: "978-1-23-456789-0",
    titulo: "Café com Deus pai",
    sinopse:
      "Descubra os mistérios do comportamento humano com base na neurociência.",
    numeroPaginas: 412,
    preco: 39.5,
    caminhoLivro: "/livros/codigo-mente.png",
    classificacaoIndicativa: "Livre",
    autor: { nome: "Renato Costa", nacionalidade: "Brasileiro" },
    editora: { nome: "Editora Saber", cnpj: "11.222.333/0001-44" },
    usuarios: [
      { nome: "Luciana", email: "luciana@email.com", nota: 5 },
      { nome: "Pedro", email: "pedro@email.com", nota: 3 },
    ],
    generos: ["1005-eeee", "1001-aaaa"], // Romance, Suspense
  },
  {
    livro_id: "1a2b3c4d-654654-zzzz-yyyy-000011112222",
    isbn: "978-1-23-456789-0",
    titulo: "A empregada",
    sinopse:
      "Descubra os mistérios do comportamento humano com base na neurociência.",
    numeroPaginas: 412,
    preco: 39.5,
    caminhoLivro: "/livros/cronicas-ferro.png",
    classificacaoIndicativa: "Livre",
    autor: { nome: "Renato Costa", nacionalidade: "Brasileiro" },
    editora: { nome: "Editora Saber", cnpj: "11.222.333/0001-44" },
    usuarios: [
      { nome: "Luciana", email: "luciana@email.com", nota: 5 },
      { nome: "Pedro", email: "pedro@email.com", nota: 3 },
    ],
    generos: ["1006-ffff", "1007-gggg"], // Psicologia, Religião
  },
  {
    livro_id: "1a2b3c4d-0005-4346-wwww-123456789000",
    isbn: "978-1-23-456789-0",
    titulo: "Orgulho e Preconceito",
    sinopse:
      "Descubra os mistérios do comportamento humano com base na neurociência.",
    numeroPaginas: 412,
    preco: 39.5,
    caminhoLivro: "/livros/misterio-casa-abandonada.png",
    classificacaoIndicativa: "Livre",
    autor: { nome: "Renato Costa", nacionalidade: "Brasileiro" },
    editora: { nome: "Editora Saber", cnpj: "11.222.333/0001-44" },
    usuarios: [
      { nome: "Luciana", email: "luciana@email.com", nota: 5 },
      { nome: "Pedro", email: "pedro@email.com", nota: 3 },
    ],
    generos: ["1006-ffff", "1008-hhhh"], // Psicologia, Ciência
  },
];

function App() {
  const [livrosFiltrados, setLivrosFiltrados] = useState(livrosMock);
  const [generoAtivo, setGeneroAtivo] = useState(generosMock.at(0));

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
          <img src="./img/Notification.svg" alt="Notificações" />
        </a>
        <a href="#">
          <img src="./img/UserDefault.png" alt="Usuário" />
        </a>
      </header>

      <article aria-labelledby="categories" id="categories">
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
                  <h2>{livro.titulo}</h2>
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

      <section id="links-bg">
        <article aria-labelledby="links" className="container" id="links">
          <div>
            <img src="./img/Linkedin.svg" alt="Linkedin" />
            <a href="https://www.linkedin.com/in/gabriel-do-rego-farias-138378322/">
              Gabriel
            </a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://www.linkedin.com/in/hugo-resende/">Hugo</a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://www.linkedin.com/in/emily-gomes-miranda/">Emily</a>
          </div>
          <div>
            <img src="./img/Github.svg" alt="Github" />
            <a href="https://github.com/Gabriel-Farias-06">Gabriel</a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://github.com/Hugo-Oliveira-RD11">Hugo</a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://github.com/emily/">Emily</a>
          </div>
          <div>
            <img src="./img/Instagram.svg" alt="Instagram" />
            <a href="https://www.instagram.com/gabriel___farias___/">
              Gabriel
            </a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://www.instagram.com/hugo-resende/">Hugo</a>{" "}
            &nbsp;&amp;&nbsp;
            <a href="https://www.instagram.com/emily/">Emily</a>
          </div>
        </article>
      </section>

      <footer className="container">
        <div id="footer">
          <div>
            <h4>Informações</h4>
            <p>Política de privacidade</p>
            <p>Próximos lançamentos</p>
          </div>
          <div>
            <h4>Serviços ao cliente</h4>
            <p>Fale conosco</p>
            <p>FAQ</p>
          </div>
          <div>
            <h4>Minha conta</h4>
            <p>Meu perfil</p>
            <p>Lista de desejos</p>
          </div>
          <div>
            <h4>Formas de pagamento</h4>
            <p>Política de privacidade</p>
            <p>Próximos lançamentos</p>
          </div>
        </div>
        <p id="copyrigth">WeBook ©. Alguns direitos reservados</p>
      </footer>
    </div>
  );
}

export default App;
