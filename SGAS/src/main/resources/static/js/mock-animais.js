const gatos = [
  {
    nome: "Pipa",
    genero: "femea",
    porte: "medio",
    descricao: "Muito carinhosa e adora brincar.",
    imagem: "/SGAS/src/main/resources/static/images/thor-mock.jpg"
  },
  {
    nome: "Luna",
    genero: "femea",
    porte: "medio",
    descricao: "Cheia de energia e curiosporte.",
    imagem: "/SGAS/src/main/resources/static/images/luna-mock.webp"
  },
  {
    nome: "Thor",
    genero: "macho",
    porte: "grande",
    descricao: "Calmo, dócil e muito companheiro.",
    imagem: "/SGAS/src/main/resources/static/images/pipa-mock.jpg"
  }
]

const caes = [
  {
    nome: "Rex",
    genero: "macho",
    tamanho: "grande",
    descricao: "Muito protetor e leal.",
    imagem: "/SGAS/src/main/resources/static/images/rex-mock.webp"
  },
  {
    nome: "Mel",
    genero: "femea",
    tamanho: "medio",
    descricao: "Dócil, brincalhona e carinhosa.",
    imagem: "/SGAS/src/main/resources/static/images/mel-mock.webp"
  },
  {
    nome: "Tobi Uchiha",
    genero: "macho",
    tamanho: "pequeno",
    descricao: "Companheiro, alegre e esperto.",
    imagem:  "/SGAS/src/main/resources/static/images/tobi-mock.webp"
  }
]

function renderizarAnimais(lista, containerId) {
  const container = document.getElementById(containerId)
  container.innerHTML = ""
  lista.forEach(animal => {
    const card = document.createElement("div")
    card.className = "card-animal"
    const tags = []
    if (animal.genero) tags.push(`<span class="tag ${animal.genero}">${animal.genero}</span>`)
    if (animal.porte) tags.push(`<span class="tag ${animal.porte}">${animal.porte}</span>`)
    if (animal.tamanho) tags.push(`<span class="tag ${animal.tamanho}">${animal.tamanho}</span>`)
    card.innerHTML = `
      <div class="card-header">
        <img src="${animal.imagem}" alt="${animal.nome}">
        <button class="favorito">❤</button>
      </div>
      <div class="card-body">
        <h3 class="nome">${animal.nome}</h3>
        <div class="tags">${tags.join("")}</div>
        <p class="descricao">${animal.descricao}</p>
      </div>
    `
    container.appendChild(card)
  })
}

document.getElementById("form-gatos").addEventListener("submit", function (e) {
  e.preventDefault()
  const porte = document.getElementById("porte-gatos").value
  const genero = document.getElementById("genero-gatos").value
  let filtrados = gatos
  if (porte) filtrados = filtrados.filter(g => g.porte === porte)
  if (genero) filtrados = filtrados.filter(g => g.genero === genero)
  renderizarAnimais(filtrados, "lista-gatos")
})

document.getElementById("form-caes").addEventListener("submit", function (e) {
  e.preventDefault()
  const tamanho = document.getElementById("tamanho-caes").value
  const genero = document.getElementById("genero-caes").value
  let filtrados = caes
  if (tamanho) filtrados = filtrados.filter(c => c.tamanho === tamanho)
  if (genero) filtrados = filtrados.filter(c => c.genero === genero)
  renderizarAnimais(filtrados, "lista-caes")
})

renderizarAnimais(gatos, "lista-gatos")
renderizarAnimais(caes, "lista-caes")
