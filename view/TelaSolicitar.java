package view;

import view.guiElements.Botao;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaSolicitar extends JFrame implements ActionListener{

  private JPanel botoes;

  private Botao bMenu;
  private TelaInicial inicio;

  private static TelaSolicitar instancia = null;

  private Font fonte;

  public static synchronized TelaSolicitar getInstance(){
    if (instancia == null)
      instancia = new TelaSolicitar();
    return instancia;
  }

  private TelaSolicitar(){
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Selecionar matérias para solicitar");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);

    JLabel meio = new JLabel("PlaceHolder");
    meio.setHorizontalAlignment(SwingConstants.CENTER);
    meio.setFont(fonte);

    fazBotoes();

    this.add(botoes, BorderLayout.PAGE_END);
    this.add(titulo, BorderLayout.PAGE_START);
    this.add(meio, BorderLayout.CENTER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Estado das matérias");
    this.setMinimumSize(new Dimension(200,200));
  }

  private void fazBotoes(){
    bMenu = new Botao("Voltar", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bMenu);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == bMenu){
      inicio = TelaInicial.getInstance();
      inicio.setLocationRelativeTo(this);
      inicio.setBounds(this.getBounds());
      this.setVisible(false);
      inicio.setVisible(true);
    }
  }
}
