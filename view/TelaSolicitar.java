package view;

import view.guiElements.Botao;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.Vector;

import materia.Materia;


public class TelaSolicitar extends Tela{

  // panel para organização de botões
  private JPanel botoes;

  // botao para voltar para tela inicial
  private Botao bMenu;
  private Botao bEstadoMaterias;

  private static TelaSolicitar instancia = null;

  private Font fonte;

  // referente a grade de materias ofertadas por periodo que ainda nao foram cursadas
  private Vector<Vector<Materia>> materiasNaoCursadasOfertadas;


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
    this.setTitle("Solicitar Matérias");
    this.setMinimumSize(new Dimension(500,300));
  }

  private void fazBotoes(){
    bMenu = new Botao("Menu Inicial", fonte, this);
    bEstadoMaterias = new Botao("Estado das matérias", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bEstadoMaterias);
    botoes.add(bMenu);
  }

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    JFrame proxTela;
    if(source == bMenu){
      proxTela = TelaInicial.getInstance();
    } else if(source == bEstadoMaterias){
      proxTela = TelaEstado.getInstance();
    } else {
      return;
    }
    proxTela.setLocationRelativeTo(this);
    proxTela.setBounds(this.getBounds());
    this.setVisible(false);
    proxTela.setVisible(true);
  }



  // referente a grade de materias ofertadas por periodo que ainda nao foram cursadas
  public Vector<Vector<Materia>> getMateriasNaoCursadasOfertadas() {
    return materiasNaoCursadasOfertadas;
  }
  public void setMateriasNaoCursadasOfertadas(Vector<Vector<Materia>> materiasNaoCursadasOfertadas) {
    this.materiasNaoCursadasOfertadas = materiasNaoCursadasOfertadas;
  }
}
