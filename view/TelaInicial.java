package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiElements.Botao;

public class TelaInicial extends Tela{

  private JPanel botoes;

  private Botao bEstadoMaterias;

  private Botao bSolicitarMaterias;

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
    this.setMinimumSize(new Dimension(1200,300));
    this.setVisible(true);
  }

  private void fazBotoes(){
    bEstadoMaterias = new Botao("Estado das matérias", fonte, this);

    bSolicitarMaterias = new Botao("Solicitar matérias", fonte, this);

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
    JFrame proxTela;
    if(source == bEstadoMaterias){
      proxTela = TelaEstado.getInstance();
    } else if(source == bSolicitarMaterias){
      proxTela = TelaSolicitar.getInstance();
    } else {
      return;
    }
    proxTela.setLocationRelativeTo(this);
    proxTela.setBounds(this.getBounds());
    this.setVisible(false);
    proxTela.setVisible(true);
  }
}
