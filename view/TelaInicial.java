package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame implements ActionListener{

  private JPanel botoes;

  private JButton bEstadoMaterias;
  private TelaEstado estadoMaterias;

  private JButton bSolicitarMaterias;
  private TelaSolicitar solicitarMaterias;

  private Font fonte;

  private static TelaInicial instancia = null;

  public static synchronized TelaInicial getInstance(){
    if (instancia == null)
      instancia = new TelaInicial();
    return instancia;
  }

  private TelaInicial(){
    this.setLayout(new BorderLayout(10, 10));
    fonte = new Font("Hack", Font.BOLD, 16);
    fazBotoes();

    JLabel titulo = new JLabel("Quebra de Barreiras");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);

    JLabel meio = new JLabel("PlaceHolder");
    meio.setHorizontalAlignment(SwingConstants.CENTER);
    meio.setFont(fonte);

    this.add(botoes, BorderLayout.PAGE_END);
    this.add(titulo, BorderLayout.PAGE_START);
    this.add(meio, BorderLayout.CENTER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Quebra de barreiras");
    this.pack();
    this.setVisible(true);
  }

  private void fazBotoes(){
    bEstadoMaterias = new JButton("Estado das matérias");
    bEstadoMaterias.addActionListener(this);
    bEstadoMaterias.setFont(fonte);
    bEstadoMaterias.setFocusable(false);
    bEstadoMaterias.setBackground(new Color(0x44475a));
    bEstadoMaterias.setForeground(new Color(0xf8f8f2));

    bSolicitarMaterias = new JButton("Solicitar matérias");
    bSolicitarMaterias.addActionListener(this);
    bSolicitarMaterias.setFont(fonte);
    bSolicitarMaterias.setFocusable(false);
    bSolicitarMaterias.setBackground(new Color(0x44475a));
    bSolicitarMaterias.setForeground(new Color(0xf8f8f2));

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bEstadoMaterias);
    botoes.add(bSolicitarMaterias);
  }

  public static void main(String[] args){
    TelaInicial.getInstance();
    return;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    this.setVisible(false);
    if(source == bEstadoMaterias){
      estadoMaterias = TelaEstado.getInstance();
      estadoMaterias.setVisible(true);
    } else if(source == bSolicitarMaterias){
      solicitarMaterias = TelaSolicitar.getInstance();
      solicitarMaterias.setVisible(true);
    }
  }
}
