const loginBox = document.getElementById("login-box");
    const cadastroBox = document.getElementById("cadastro-box");
    const mostrarCadastro = document.getElementById("mostrar-cadastro");
    const mostrarLogin = document.getElementById("mostrar-login");

    mostrarCadastro.addEventListener("click", (e) => {
      e.preventDefault();
      loginBox.classList.add("hidden");
      cadastroBox.classList.remove("hidden");
    });

    mostrarLogin.addEventListener("click", (e) => {
      e.preventDefault();
      cadastroBox.classList.add("hidden");
      loginBox.classList.remove("hidden");
    });