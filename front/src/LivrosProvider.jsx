import React, { createContext, useContext, useState } from "react";

const LivrosContext = createContext();

export function LivrosProvider({ children }) {
  // async function getLivros() {
  //   const response = await fetch(
  //     "webook/api/livros"
  //   );

  //   return await response.json();
  // }

  // const livros = getLivros();

  const [Livros, setLivros] = useState([
    {
      livro_id: "1a2b3c4d-0001-aaaa-bbbb-111122223333",
      isbn: "978-3-16-148410-0",
      titulo: "Orgulho e preconceito",
      sinopse:
        "Orgulho e preconceito é o livro mais famoso de Jane Austen e possui uma série de personagens inesquecíveis e um enredo memorável. Austen nos apresenta Elizabeth Bennet como heroína irresistível e seu pretendente aristocrático, o sr. Darcy. Nesse livro, aspectos diferentes são abordados: orgulho encontra preconceito, ascendência social confronta desprezo social, equívocos e julgamentos antecipados conduzem alguns personagens ao sofrimento e ao escândalo.",
      numeroPaginas: 320,
      preco: 49.9,
      caminhoLivro: "/livros/misterio-casa-abandonada.png",
      classificacaoIndicativa: "12+",
      autor: { nome: "Lucas Silva", nacionalidade: "Brasileiro" },
      editora: { nome: "Sombras", cnpj: "12.345.678/0001-90" },
      usuarios: [
        { nome: "Joana", email: "joana@email.com", nota: 5 },
        { nome: "Carlos", email: "carlos@email.com", nota: 4 },
      ],
      generos: ["1001-aaaa", "1002-bbbb"], // Suspense, Mistério
      idadeLeitura: 15,
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
      editora: { nome: "Fagulha", cnpj: "98.765.432/0001-12" },
      usuarios: [
        { nome: "Marcos", email: "marcos@email.com", nota: 4 },
        { nome: "Beatriz", email: "beatriz@email.com", nota: 5 },
      ],
      generos: ["1003-cccc", "1004-dddd"], // Fantasia, Aventura
      idadeLeitura: 15,
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
      editora: { nome: "Saber", cnpj: "11.222.333/0001-44" },
      usuarios: [
        { nome: "Luciana", email: "luciana@email.com", nota: 5 },
        { nome: "Pedro", email: "pedro@email.com", nota: 3 },
      ],
      generos: ["1005-eeee", "1001-aaaa"], // Romance, Suspense
      idadeLeitura: 15,
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
      editora: { nome: "Saber", cnpj: "11.222.333/0001-44" },
      usuarios: [
        { nome: "Luciana", email: "luciana@email.com", nota: 5 },
        { nome: "Pedro", email: "pedro@email.com", nota: 3 },
      ],
      generos: ["1006-ffff", "1007-gggg"], // Psicologia, Religião
      idadeLeitura: 15,
    },
    {
      livro_id: "1a2b3c4d-0005-4346-wwww-123456789000",
      isbn: "978-1-23-456789-0",
      titulo: "Orgulho e Preconceito",
      sinopse:
        "Orgulho e preconceito é o livro mais famoso de Jane Austen e possui uma série de personagens inesquecíveis e um enredo memorável. Austen nos apresenta Elizabeth Bennet como heroína irresistível e seu pretendente aristocrático, o sr. Darcy. Nesse livro, aspectos diferentes são abordados: orgulho encontra preconceito, ascendência social confronta desprezo social, equívocos e julgamentos antecipados conduzem alguns personagens ao sofrimento e ao escândalo.",
      numeroPaginas: 412,
      preco: 39.5,
      caminhoLivro: "/livros/misterio-casa-abandonada.png",
      classificacaoIndicativa: "Livre",
      autor: { nome: "Renato Costa", nacionalidade: "Brasileiro" },
      editora: { nome: "Saber", cnpj: "11.222.333/0001-44" },
      usuarios: [
        { nome: "Luciana", email: "luciana@email.com", nota: 5 },
        { nome: "Pedro", email: "pedro@email.com", nota: 3 },
      ],
      generos: ["1006-ffff", "1008-hhhh"], // Psicologia, Ciência
      idadeLeitura: 15,
    },
  ]);

  return (
    <LivrosContext.Provider value={[Livros, setLivros]}>
      {children}
    </LivrosContext.Provider>
  );
}

export function useLivros() {
  return useContext(LivrosContext);
}
