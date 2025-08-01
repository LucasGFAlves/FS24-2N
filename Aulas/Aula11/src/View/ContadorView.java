package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class ContadorView extends JFrame {
    private JLabel lblContador;
    private JButton btnIncrementar;
    public ContadorView() {
        setTitle("Contador MVC");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Layout simples
        lblContador = new JLabel("0");
        lblContador.setFont(new Font("Arial", Font.BOLD, 48));
        btnIncrementar = new JButton("Incrementar");
        btnIncrementar.setFont(new Font("Arial", Font.PLAIN, 18));
        add(lblContador); add(btnIncrementar);
    }
    public void setValor(int valor) { lblContador.setText(String.valueOf(valor)); }
    public void addIncrementarListener(ActionListener listener) { btnIncrementar.addActionListener(listener);}
}
