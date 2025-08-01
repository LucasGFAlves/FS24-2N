package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingComponentes extends JInternalFrame {
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JCheckBox chkEstudante;
    private JTextArea txtaObservacoes;
    private JButton btnRegistrar;
    private JButton btnLimpar;

    public SwingComponentes(){
        super("Teste ", true, true, true,true);
        setSize(600,400);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o componente preencher o espaço horizontal

        int row = 0; // Controle de linha para o GridBagLayout

        // Componente: Nome
        gbc.gridx = 0; gbc.gridy = row;
        panelPrincipal.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; gbc.gridwidth = 2; // Ocupa 2 colunas para o campo
        txtNome = new JTextField(25);
        panelPrincipal.add(txtNome, gbc);
        row++;

        // Componente: Email
        gbc.gridx = 0; gbc.gridy = row;
        panelPrincipal.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; gbc.gridwidth = 2;
        txtEmail = new JTextField(25);
        panelPrincipal.add(txtEmail, gbc);
        row++;

        // Componente: Email
        gbc.gridx = 0; gbc.gridy = row;
        panelPrincipal.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; gbc.gridy = row; gbc.gridwidth = 2;
        txtSenha = new JPasswordField(25);
        panelPrincipal.add(txtSenha, gbc);
        row++;

        // Componente: Estudante
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3; // Ocupa 3 colunas para o checkbox
        chkEstudante = new JCheckBox("Sou estudante");
        panelPrincipal.add(chkEstudante, gbc);
        row++;

        // Componente: Observações (com JScrollPane)
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3;
        panelPrincipal.add(new JLabel("Observações:"), gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3; gbc.weighty = 1.0; // Pede mais espaço vertical
        txtaObservacoes = new JTextArea(5, 25); // 5 linhas visíveis, 25 colunas
        txtaObservacoes.setLineWrap(true); // Quebra de linha automática
        txtaObservacoes.setWrapStyleWord(true); // Quebra de linha por palavra
        JScrollPane scrollPane = new JScrollPane(txtaObservacoes); // Adiciona barra de rolagem
        panelPrincipal.add(scrollPane, gbc);
        row++;

        // Painel para os Botões (para organizá-los lado a lado)
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centraliza com espaçamento
        btnRegistrar = new JButton("Registrar");
        btnLimpar = new JButton("Limpar");
        panelBotoes.add(btnRegistrar);
        panelBotoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3; gbc.weighty = 0; // Reseta weighty
        panelPrincipal.add(panelBotoes, gbc);

        // Adiciona o painel principal à janela interna
        add(panelPrincipal);

        // Adicione os ActionListeners aos botões
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarParticipante();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
            }
        });
    }



    // Método que será chamado ao clicar no botão "Registrar"
    private void registrarParticipante() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        boolean estudante = chkEstudante.isSelected();
        String observacoes = txtaObservacoes.getText().trim();

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nome e Email são obrigatórios!",
                    "Erro de Validação",
                    JOptionPane.WARNING_MESSAGE);
            return; // Interrompe a execução
        }

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("--- Dados do Registro ---\n");
        mensagem.append("Nome: ").append(nome).append("\n");
        mensagem.append("Email: ").append(email).append("\n");
        mensagem.append("Estudante: ").append(estudante ? "Sim" : "Não").append("\n");
        if (!observacoes.isEmpty()) {
            mensagem.append("Observações:\n").append(observacoes).append("\n");
        } else {
            mensagem.append("Observações: Nenhuma\n");
        }

        JOptionPane.showMessageDialog(this,
                mensagem.toString(),
                "Registro Confirmado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Método que será chamado ao clicar no botão "Limpar"
    private void limparFormulario() {
        txtNome.setText("");
        txtEmail.setText("");
        chkEstudante.setSelected(false);
        txtaObservacoes.setText("");
        txtNome.requestFocusInWindow(); // Coloca o foco no campo Nome
    }
}
