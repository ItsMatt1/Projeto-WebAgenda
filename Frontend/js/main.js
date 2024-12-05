document.addEventListener("DOMContentLoaded", () => {
  // Elementos de formulário
  const usuarioForm = document.getElementById("usuarioForm");
  const alergiaForm = document.getElementById("alergiaForm");
  const vacinaForm = document.getElementById("vacinaForm");
  const agendaForm = document.getElementById("agendaForm");

  // Listas para armazenamento
  let usuarios = [];
  let alergias = [];
  let vacinas = [];
  let agendas = [];

  // Elementos de tabela
  const usuarioTableBody = document.getElementById("usuarioTableBody");
  const alergiaTableBody = document.getElementById("alergiaTableBody");
  const vacinaTableBody = document.getElementById("vacinaTableBody");
  const agendaTableBody = document.getElementById("agendaTableBody");

  // Popula comboboxes
  const usuarioAlergias = document.getElementById("usuarioAlergias");
  const agendaUsuario = document.getElementById("agendaUsuario");
  const agendaVacina = document.getElementById("agendaVacina");

  // Funções auxiliares
  const renderizarLista = (lista, tbody, renderizador) => {
    tbody.innerHTML = "";
    lista.forEach((item, index) => {
      tbody.innerHTML += renderizador(item, index);
    });
  };

  const atualizarCombobox = (lista, combobox) => {
    console.log(lista);
    combobox.innerHTML = "";
    lista.forEach((item) => {
      combobox.innerHTML += `<option value="${item.id}">${
        item.nome || item.titulo
      }</option>`;
    });
  };

  // Renderizadores de tabela
  const renderUsuario = (usuario, index) => `
      <tr>
          <td>${usuario.nome}</td>
          <td>${usuario.sexo}</td>
          <td>${usuario.uf}</td>
          <td>${usuario.dataNascimento?.split("-").reverse().join("/")}</td>
          <td>${usuario.alergias?.map((alergia) => alergia.nome).join(", ")}</td>
          <td>
              <button class="btn btn-danger btn-sm" onclick="excluirUsuario(${index})">Excluir</button>
          </td>
      </tr>
  `;

  const renderAlergia = (alergia, index) => `
      <tr>
          <td>${alergia.nome}</td>
          <td>
              <button class="btn btn-danger btn-sm" onclick="excluirAlergia(${index})">Excluir</button>
          </td>
      </tr>
  `;

  const renderVacina = (vacina, index) => {
    const periodicidade = { 1: "Dias", 2: "Semanas", 3: "Meses", 4: "Anos" };

    return `
      <tr>
          <td>${vacina.titulo}</td>
          <td>${vacina.doses || 1}</td>
          <td>${vacina.intervalo}</td>
          <td>${
            vacina.doses > 1
              ? periodicidade[vacina.periodicidade]
              : "Dose única"
          }</td>
          <td>
              <button class="btn btn-danger btn-sm" onclick="excluirVacina(${index})">Excluir</button>
          </td>
      </tr>
  `;
  };

  const renderAgenda = (agenda, index) => `
      <tr>
          <td>${agenda?.usuario?.nome}</td>
          <td>${agenda?.vacina?.titulo}</td>
          <td>${agenda?.data}</td>
          <td>${agenda?.situacao}</td>
          <td>
              <button class="btn btn-success btn-sm" onclick="darBaixaAgenda(${agenda?.id}, 2)">Realizar</button>
              <button class="btn btn-warning btn-sm" onclick="darBaixaAgenda(${agenda?.id}, 1)">Cancelar</button>
              <button class="btn btn-danger btn-sm" onclick="excluirAgenda(${agenda?.id})">Excluir</button>
          </td>
      </tr>
  `;

  // Função para carregar dados do servidor
  const carregarDados = async () => {
    try {
      const [usuariosRes, alergiasRes, vacinasRes, agendasRes] =
        await Promise.all([
          fetch("http://localhost:8080/usuarios").then((res) => res.json()),
          fetch("http://localhost:8080/alergias").then((res) => res.json()),
          fetch("http://localhost:8080/vacinas").then((res) => res.json()),
          fetch("http://localhost:8080/agendas").then((res) => res.json()),
        ]);

      // Atribuindo os dados à lista
      usuarios = usuariosRes;
      alergias = alergiasRes;
      vacinas = vacinasRes;
      agendas = agendasRes;

      console.log({usuarios});
      // Renderizando as tabelas
      renderizarLista(usuarios, usuarioTableBody, renderUsuario);
      renderizarLista(alergias, alergiaTableBody, renderAlergia);
      renderizarLista(vacinas, vacinaTableBody, renderVacina);
      renderizarLista(agendas, agendaTableBody, renderAgenda);

      // Atualizando comboboxes
      atualizarCombobox(usuarios, agendaUsuario);
      atualizarCombobox(alergias, usuarioAlergias);
      atualizarCombobox(vacinas, agendaVacina);
    } catch (error) {
      console.error("Erro ao carregar os dados:", error);
    }
  };

  // Carrega os dados na inicialização
  carregarDados();

  // Cadastro de usuário
  usuarioForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const nome = document.getElementById("usuarioNome").value;
    const nascimento = document.getElementById("usuarioNascimento").value;
    const sexo = document.getElementById("usuarioSexo").value;
    const uf = document.getElementById("usuarioUF").value;
    const alergiasSelecionadas = Array.from(
      usuarioAlergias.selectedOptions
    ).map((opt) => opt.value);

    console.log(alergias);

    const alergiasObj = alergiasSelecionadas.map((alergia) =>
      alergias.find((a) => a.id === Number(alergia))
    );

    const dataNascimento = new Date(nascimento);
    dataNascimento.setDate(dataNascimento.getDate() + 1);

    const novoUsuario = {
      nome,
      dataNascimento,
      sexo: sexo.toUpperCase(),
      uf,
      alergias: alergiasObj.map(a => ({id: a.id})),
    };

    try {
      const res = await fetch("http://localhost:8080/usuarios", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(novoUsuario),
      });

      if (res.ok) {
        carregarDados(); // Recarrega os dados
      } else {
        console.error("Erro ao salvar usuário");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }

    usuarioForm.reset();
  });

  // Cadastro de alergia
  alergiaForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const nome = document.getElementById("alergiaNome").value;

    try {
      const res = await fetch("http://localhost:8080/alergias", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ nome }),
      });

      if (res.ok) {
        carregarDados(); // Recarrega os dados
      } else {
        console.error("Erro ao salvar alergia");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }

    alergiaForm.reset();
  });

  // Cadastro de vacina
  vacinaForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const nome = document.getElementById("vacinaNome").value;
    const doses = parseInt(document.getElementById("vacinaDoses").value);
    const intervalo = parseInt(
      document.getElementById("vacinaIntervalo").value
    );
    const periodicidade = document.getElementById("vacinaPeriodicidade").value;

    const novaVacina = { titulo: nome, doses, intervalo, periodicidade };

    try {
      const res = await fetch("http://localhost:8080/vacinas", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(novaVacina),
      });

      if (res.ok) {
        carregarDados(); // Recarrega os dados
      } else {
        console.error("Erro ao salvar vacina");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }

    vacinaForm.reset();
  });

  // Cadastro de agenda
  agendaForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const usuario = document.getElementById("agendaUsuario").value;
    const vacina = document.getElementById("agendaVacina").value;
    const data = document.getElementById("agendaData").value;

    const vacinaSelecionada = vacinas.find((v) => v.id === Number(vacina));

    if (vacinaSelecionada) {
      for (let i = 0; i < vacinaSelecionada.doses; i++) {
        const novaData = new Date(data);
        const incremento = vacinaSelecionada.intervalo * i;

        if (Number(vacinaSelecionada.periodicidade) === 1) {
          novaData.setDate(novaData.getDate() + incremento);
        } else if (Number(vacinaSelecionada.periodicidade) === 2) {
          novaData.setDate(novaData.getDate() + incremento * 7);
        } else if (Number(vacinaSelecionada.periodicidade) === 3) {
          novaData.setMonth(novaData.getMonth() + incremento);
        } else if (Number(vacinaSelecionada.periodicidade) === 4) {
          novaData.setFullYear(novaData.getFullYear() + incremento);
        }

        const novaAgenda = {
          usuario_id: Number(usuario),
          vacina_id: Number(vacina),
          data: novaData.toISOString().split("T")[0],
          situacao: 0,
        };

        try {
          const res = await fetch("http://localhost:8080/agendas", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(novaAgenda),
          });

          if (res.ok) {
            carregarDados(); // Recarrega os dados
          } else {
            console.error("Erro ao salvar agenda");
          }
        } catch (error) {
          console.error("Erro de conexão:", error);
        }
      }
    }

    agendaForm.reset();
  });

  // Função de exclusão
  window.excluirUsuario = async (index) => {
    try {
      const usuarioId = usuarios[index].id;
      const res = await fetch(`http://localhost:8080/usuarios/${usuarioId}`, {
        method: "DELETE",
      });

      if (res.ok) {
        usuarios.splice(index, 1); // Atualiza a lista local
        renderizarLista(usuarios, usuarioTableBody, renderUsuario);
        carregarDados();
      } else {
        console.error("Erro ao excluir usuário");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };

  window.excluirAlergia = async (index) => {
    try {
      const alergia = alergias[index];
      const res = await fetch(`http://localhost:8080/alergias/${alergia.id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        alergias.splice(index, 1); // Atualiza a lista local
        renderizarLista(alergias, alergiaTableBody, renderAlergia);
        carregarDados();
      } else {
        console.error("Erro ao excluir alergia");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };

  window.excluirVacina = async (index) => {
    try {
      const vacina = vacinas[index];
      const res = await fetch(`http://localhost:8080/vacinas/${vacina.nome}`, {
        method: "DELETE",
      });

      if (res.ok) {
        vacinas.splice(index, 1); // Atualiza a lista local
        renderizarLista(vacinas, vacinaTableBody, renderVacina);
        carregarDados();
      } else {
        console.error("Erro ao excluir vacina");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };

  window.excluirAgenda = async (index) => {
    try {
      const agendaId = index;
      const res = await fetch(`http://localhost:8080/agendas/${agendaId}`, {
        method: "DELETE",
      });

      if (res.ok) {
        agendas.splice(index, 1); // Atualiza a lista local
        renderizarLista(agendas, agendaTableBody, renderAgenda);
        carregarDados();
      } else {
        console.error("Erro ao excluir agenda");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };

  // Função para dar baixa em agenda (realizado ou cancelado)
  window.darBaixaAgenda = async (index, situacao) => {
    try {
      const agendaId = index;
      const res = await fetch(`http://localhost:8080/agendas/${agendaId}/baixa?situacao=${situacao}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (res.ok) {
        agendas[index].situacao = situacao; // Atualiza a situação da agenda localmente
        renderizarLista(agendas, agendaTableBody, renderAgenda);
        carregarDados();
      } else {
        console.error("Erro ao dar baixa na agenda");
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
    }
  };
});
