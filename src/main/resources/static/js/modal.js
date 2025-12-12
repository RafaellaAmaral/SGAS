const modal = document.getElementById("modal-adotar");
  const closeBtn = document.querySelector(".close");
  const botoesAbrir = document.querySelectorAll(".abrir-modal");

  botoesAbrir.forEach(botao => {
    botao.addEventListener("click", () => {
      modal.style.display = "flex";
    });
  });

  closeBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  window.addEventListener("click", (e) => {
    if (e.target === modal) {
      modal.style.display = "none";
    }
  });

  document.getElementById("form-adocao").addEventListener("submit", (e) => {
    e.preventDefault();
    alert("Formulário enviado com sucesso!");
    modal.style.display = "none";
  });