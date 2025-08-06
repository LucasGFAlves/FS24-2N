package Controller;


import Model.DAO.DinossauroDAO;
import Model.Dinossauro;

import java.time.LocalDate;
import java.util.List;

public class DinossauroController {
    private DinossauroDAO dinossauroDAO;

    public DinossauroController() {
        this.dinossauroDAO = new DinossauroDAO();
    }

    public void cadastrarDinossauro(String nome, String especie, double peso, double altura, LocalDate dataDescoberta) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome do dinossauro é obrigatório.");
        }
        if (especie == null || especie.trim().isEmpty()) {
            throw new Exception("A espécie do dinossauro é obrigatória.");
        }
        if (peso <= 0 || altura <= 0) {
            throw new Exception("Peso e altura devem ser maiores que zero.");
        }
        if (dataDescoberta == null) {
            throw new Exception("A data de descoberta é obrigatória.");
        }

        Dinossauro dinossauro = new Dinossauro(nome, especie, peso, altura, dataDescoberta);
        dinossauroDAO.inserir(dinossauro);
    }

    public List<Dinossauro> listarTodosDinossauros() {
        return dinossauroDAO.listarTodos();
    }

    public Dinossauro buscarDinossauroPorId(int id) {
        return dinossauroDAO.buscarPorId(id);
    }

    public void atualizarDinossauro(int id, String nome, String especie, double peso, double altura, LocalDate dataDescoberta) throws Exception {
        if (nome == null || nome.trim().isEmpty() || especie == null || especie.trim().isEmpty() || peso <= 0 || altura <= 0 || dataDescoberta == null) {
            throw new Exception("Todos os campos do dinossauro são obrigatórios e devem ser válidos.");
        }
        Dinossauro dinossauro = new Dinossauro(id, nome, especie, peso, altura, dataDescoberta);
        dinossauroDAO.atualizar(dinossauro);
    }

    public void removerDinossauro(int id) {
        dinossauroDAO.remover(id);
    }

    public List<Dinossauro> buscarDinossauroPorNome(String nome) {
        return dinossauroDAO.buscarPorNome(nome);
    }
}