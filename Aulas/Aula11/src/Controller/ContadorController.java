package Controller;

import Model.Contador;
import View.ContadorView;

public class ContadorController {
    private Contador model1;
    private ContadorView view1;

    public ContadorController(Contador model, ContadorView view) {
        this.model1 = model;
        this.view1 = view;
        this.view1.addIncrementarListener(e -> {
            model1.incrementar(); // Lógica no Model
            view1.setValor(model1.getValor()); // Atualiza a View
        });
        view.setValor(model1.getValor());
    }
    public void iniciarCont() { view1.setVisible(true); }
}