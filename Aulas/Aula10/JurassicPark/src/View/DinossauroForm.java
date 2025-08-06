package View;

import Controller.DinossauroController; // Importe o DinossauroController
import Model.Dinossauro;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DinossauroForm extends JInternalFrame {

    private DinossauroController controller;
    private JTextField txtId, txtNome, txtEspecie, txtPeso, txtAltura, txtDataDescoberta;
    private JButton btnSalvar, btnBuscar;
    private Integer dinossauroIdParaEdicao; // Usado para saber se é uma edição ou novo cadastro

    /**
     * Construtor para o formulário de Dinossauro.
     * Pode ser usado para cadastrar um novo dinossauro (dinossauroId = null)
     * ou para editar um existente (passando o ID do dinossauro).
     *
     * @param controller   O DinossauroController que gerenciará as operações de negócio.
     * @param dinossauroId O ID do dinossauro a ser editado, ou null para um novo cadastro.
     */
    public DinossauroForm(DinossauroController controller, Integer dinossauroId) {
        super("Cadastro de Dinossauro", true, true, true, true); // Título, redimensionável, fechável, maximizável, iconificável
        this.controller = controller;
        this.dinossauroIdParaEdicao = dinossauroId;

        setSize(800, 600); // Tamanho da janela interna
        setLayout(new GridBagLayout()); // Layout para organizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 80, 5, 80); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche o espaço horizontalmente

        int row = 0; // Contador de linhas para o layout

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        txtId = new JTextField(10);
        txtId.setEditable(false); // ID não pode ser editado diretamente, apenas buscado
        add(txtId, gbc);
        gbc.gridx = 2;
        gbc.gridy = row;
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarDinossauro()); // Adiciona ação ao botão Buscar
        add(btnBuscar, gbc);
        row++;

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        txtNome = new JTextField(20);
        add(txtNome, gbc);
        row++;

        // Campo Espécie
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Espécie:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtEspecie = new JTextField(20);
        add(txtEspecie, gbc);
        row++;

        // Campo Peso
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Peso (kg):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtPeso = new JTextField(20);
        add(txtPeso, gbc);
        row++;

        // Campo Altura
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Altura (m):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtAltura = new JTextField(20);
        add(txtAltura, gbc);
        row++;

        // Campo Data Descoberta
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Data Descoberta (AAAA-MM-DD):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtDataDescoberta = new JTextField(LocalDate.now().toString()); // Preenche com a data atual por padrão
        add(txtDataDescoberta, gbc);
        row++;

        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3; // Ocupa 3 colunas
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarDinossauro()); // Adiciona ação ao botão Salvar
        add(btnSalvar, gbc);

        // Se um ID foi passado, carrega o dinossauro para edição
        if (dinossauroIdParaEdicao != null) {
            carregarDinossauroParaEdicao(dinossauroIdParaEdicao);
            txtId.setText(String.valueOf(dinossauroIdParaEdicao));
            btnBuscar.setEnabled(false); // Desabilita o botão buscar se já está editando
        }
    }

    private void buscarDinossauro() {
        controller.listarTodosDinossauros();
    }

    private void carregarDinossauroParaEdicao(Integer dinossauroIdParaEdicao) {
    }

    private void salvarDinossauro() {
        try {
            String nome = txtNome.getText().trim();
            String especie = txtEspecie.getText().trim();
            Double peso = Double.parseDouble(txtPeso.getText().trim());
            Double altura  = Double.parseDouble(txtAltura.getText().trim());
            LocalDate data = LocalDate.parse(txtDataDescoberta.getText().trim());

            controller.cadastrarDinossauro(nome,especie,peso,altura,data);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
/**
 * Abre uma caixa de diálogo para o usuário digitar um ID e busca o dinossauro correspondente.

 private void buscarDinossauro() {
 String idStr = JOptionPane.showInputDialog(this, "Digite o ID do dinossauro para buscar:");
 if (idStr != null && !idStr.trim().isEmpty()) {
 try {
 int id = Integer.parseInt(idStr);
 car
 */