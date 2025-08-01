import Controller.ContadorController;
import Model.Contador;
import View.ContadorView;

/*
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        // --- Testando Inserção ---
        System.out.println("\n--- Testando Inserção ---");
        Aluno novoAluno1 = new Aluno("Joana", 20, "9111-2222");
        alunoDAO.setAluno(novoAluno1);
        Aluno novoAluno2 = new Aluno("Roberto", 25, "9333-4444");
        alunoDAO.setAluno(novoAluno2);

        // --- Testando Listagem ---
        System.out.println("\n--- Testando Listagem ---");
        List<Aluno> listaAlunos = alunoDAO.getAluno();
        if (!listaAlunos.isEmpty()) {
            for (Aluno a : listaAlunos) {
                System.out.println(a.getNome());
            }
        } else {
            System.out.println("Nenhum aluno na lista.");
        }

        // --- Testando Atualização ---
        System.out.println("\n--- Testando Atualização ---");
        Aluno alunoParaAtualizar = new Aluno(1, "Joana Silva", 21, "9111-0000");
        alunoDAO.atualizar(alunoParaAtualizar);
        System.out.println("\n--- Listagem após Atualização ---");
        alunoDAO.getAluno();

        // --- Testando Remoção ---
        System.out.println("\n--- Testando Remoção ---");
        alunoDAO.removerAluno(2);
        System.out.println("\n--- Listagem após Remoção ---");
        alunoDAO.getAluno();
}}
*/

public class Main {
    public static void main(String[] args) {
        Contador model = new Contador();
        ContadorView view = new ContadorView();
        ContadorController controller = new ContadorController(model, view);
        controller.iniciarCont();
    }
}