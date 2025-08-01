package View;

import Controller.DinossauroController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Dinossauro;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private DinossauroController dinossauroController; // Nova instância do DinossauroController

    public MainFrame() {
        super("Sistema de Gerenciamento de Dinossauros");
        this.dinossauroController = new DinossauroController(); // Instancia o controller de dinossauros

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // --- Menu Dinossauros (usará dinossauroController) ---
        JMenu menuDinossauros = new JMenu("Dinossauros");
        JMenuItem itemCadastrarDinossauro = new JMenuItem("Cadastrar Dinossauro");
        JMenuItem itemListarDinossauros = new JMenuItem("Listar Dinossauros");
        JMenuItem itemSwing = new JMenuItem("teste");

        itemCadastrarDinossauro.addActionListener(e -> openDinossauroForm(null));
        itemListarDinossauros.addActionListener(e -> openListaDinossaurosPanel());
        itemSwing.addActionListener(e -> openPanelTeste());

        menuDinossauros.add(itemCadastrarDinossauro);
        menuDinossauros.add(itemListarDinossauros);
        menuDinossauros.add(itemSwing);

        menuBar.add(menuDinossauros);

        // --- Menu Sair (Existente) ---
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }

    // --- Métodos para abrir janelas de Dinossauros (passando dinossauroController) ---
    private void openDinossauroForm(Integer idDinossauro) {
        DinossauroForm dinossauroForm = new DinossauroForm(dinossauroController, idDinossauro); // Passa o dinossauroController
        desktopPane.add(dinossauroForm);
        dinossauroForm.setVisible(true);
        dinossauroForm.toFront();
    }

    private void openListaDinossaurosPanel() {
        ListaDinossaurosPanel listaDinossauros = new ListaDinossaurosPanel(dinossauroController); // Passa o dinossauroController
        desktopPane.add(listaDinossauros);
        listaDinossauros.setVisible(true);
        listaDinossauros.toFront();
    }

    private void openPanelTeste() {
        SwingComponentes testeS = new SwingComponentes(); // Passa o dinossauroController
        desktopPane.add(testeS);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}