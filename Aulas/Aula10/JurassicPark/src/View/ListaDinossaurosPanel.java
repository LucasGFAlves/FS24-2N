package View;

// import controller.BibliotecaController; // REMOVA esta linha
import Controller.DinossauroController; // Importe o novo DinossauroController
import Model.Dinossauro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaDinossaurosPanel extends JInternalFrame {

    private DinossauroController controller; // Altere para DinossauroController
    private JTable tabelaDinossauros;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar, btnRemover, btnBuscar;
    private JTextField txtBuscaNome;

    public ListaDinossaurosPanel(DinossauroController controller) { // Altere o tipo do parâmetro
        super("Lista de Dinossauros", true, true, true, true);
        this.controller = controller; // Atribui o novo controller

        setSize(850, 500);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Espécie", "Peso (kg)", "Altura (m)", "Data Descoberta"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaDinossauros = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaDinossauros);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscaNome = new JTextField(20);
        btnBuscar = new JButton("Buscar por Nome");
        btnAtualizar = new JButton("Atualizar Tabela");
        btnRemover = new JButton("Remover Selecionado");

        panelAcoes.add(new JLabel("Nome:"));
        panelAcoes.add(txtBuscaNome);
        panelAcoes.add(btnBuscar);
        panelAcoes.add(btnAtualizar);
        panelAcoes.add(btnRemover);
        add(panelAcoes, BorderLayout.NORTH);

        //btnAtualizar.addActionListener(e -> carregarDinossaurosNaTabela());
        //btnRemover.addActionListener(e -> removerDinossauroSelecionado());
        //btnBuscar.addActionListener(e -> buscarDinossaurosPorNome());

        //carregarDinossaurosNaTabela();
    }

    // ... (restante dos métodos internos, como carregarDinossaurosNaTabela, removerDinossauroSelecionado, buscarDinossaurosPorNome) ...
}